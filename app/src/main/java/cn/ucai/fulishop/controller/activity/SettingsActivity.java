package cn.ucai.fulishop.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.FuLiShopApplication;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.model.net.SharePrefrenceUtils;
import cn.ucai.fulishop.model.utils.ImageLoader;
import cn.ucai.fulishop.view.DisplayUtils;
import cn.ucai.fulishop.view.MFGT;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.iv_user_profile_avatar)
    ImageView mIvUserProfileAvatar;
    @BindView(R.id.tv_user_profile_name)
    TextView mTvUserProfileName;
    @BindView(R.id.tv_user_profile_nick)
    TextView mTvUserProfileNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this,"设置");
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
    @OnClick (R.id.layout_user_profile_nickname)
    public void updateNick(){
        MFGT.gotoUpDataNick(this);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == I.REQUEST_CODE_NICK) {
            mTvUserProfileNick.setText(FuLiShopApplication.getUser().getMuserNick());
        }
    }
}

