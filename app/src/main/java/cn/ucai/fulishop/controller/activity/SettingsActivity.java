package cn.ucai.fulishop.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.FuLiShopApplication;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.Result;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.model.net.IModelUser;
import cn.ucai.fulishop.model.net.ModelUser;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.net.SharePrefrenceUtils;
import cn.ucai.fulishop.model.utils.CommonUtils;
import cn.ucai.fulishop.model.utils.ImageLoader;
import cn.ucai.fulishop.model.utils.OnSetAvatarListener;
import cn.ucai.fulishop.model.utils.ResultUtils;
import cn.ucai.fulishop.view.DisplayUtils;
import cn.ucai.fulishop.view.MFGT;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.iv_user_profile_avatar)
    ImageView mIvUserProfileAvatar;
    @BindView(R.id.tv_user_profile_name)
    TextView mTvUserProfileName;
    @BindView(R.id.tv_user_profile_nick)
    TextView mTvUserProfileNick;

    IModelUser model;
    OnSetAvatarListener mOnSetAvatarListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this, "设置");
        initData();
    }

    private void initData() {
        User user = FuLiShopApplication.getUser();
        if (user != null) {
            loadUserInfo(user);
        } else {
            MFGT.gotoLogin(this);
        }
    }

    private void loadUserInfo(User user) {
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), this, mIvUserProfileAvatar);
        mTvUserProfileName.setText(user.getMuserName());
        mTvUserProfileNick.setText(user.getMuserNick());
    }

    @OnClick(R.id.btn_logout)
    public void logout() {
        FuLiShopApplication.setUser(null);
        SharePrefrenceUtils.getInstance(this).removeUser();
        MFGT.gotoLogin(this);
        finish();
    }

    @OnClick(R.id.layout_user_profile_nickname)
    public void updateNick() {
        MFGT.gotoUpDataNick(this);
    }

    @OnClick(R.id.layout_user_profile_username)
    public void onClickUserName() {
        CommonUtils.showLongToast(R.string.username_connot_be_modify);
    }

    @OnClick(R.id.layout_user_profile_avatar)
    public void onClickAvatar() {
        mOnSetAvatarListener = new OnSetAvatarListener(this,
                R.id.layout_user_profile_avatar,
                FuLiShopApplication.getUser().getMuserName(),
                I.AVATAR_TYPE_USER_PATH);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == I.REQUEST_CODE_NICK) {
            mTvUserProfileNick.setText(FuLiShopApplication.getUser().getMuserNick());
        } else if (requestCode == OnSetAvatarListener.REQUEST_CROP_PHOTO) {
            uploadAvatar();
        } else {
            mOnSetAvatarListener.setAvatar(requestCode, data, mIvUserProfileAvatar);
        }
    }

    private void uploadAvatar() {
        model = new ModelUser();
        User user = FuLiShopApplication.getUser();
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.update_user_avatar));
        dialog.show();
        File file = null;
        file = new File(String.valueOf(OnSetAvatarListener.getAvatarFile(this,
               I.AVATAR_TYPE_USER_PATH+"/"+
                user.getMuserName()+user.getMavatarSuffix())));
        model.uploadAvatar(this,
                user.getMuserName(),
                file, new OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        int msg = R.string.update_user_avatar_fail;
                        if (s != null) {
                            Result result = ResultUtils.getResultFromJson(s, User.class);
                            if (result != null) {
                                if (result.isRetMsg()) {
                                   msg=R.string.update_user_avatar_success;
                                }
                            }
                        }
                        CommonUtils.showLongToast(msg);
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(String error) {
                        CommonUtils.showLongToast(error);
                        dialog.dismiss();
                    }
                });
    }
}

