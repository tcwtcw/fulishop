package cn.ucai.fulishop.controller.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.FuLiShopApplication;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.Result;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.model.dao.UserDao;
import cn.ucai.fulishop.model.net.IModelUser;
import cn.ucai.fulishop.model.net.ModelUser;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.utils.CommonUtils;
import cn.ucai.fulishop.model.utils.ResultUtils;
import cn.ucai.fulishop.view.DisplayUtils;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class UpdataNickActivity extends AppCompatActivity {
    @BindView(R.id.et_update_user_name)
    EditText mEtUpdateUserName;

    User user;
    IModelUser model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this,"修改昵称");
        initData();
    }

    private void initData() {
        user = FuLiShopApplication.getUser();
        if (user == null) {
            finish();
        } else {
            mEtUpdateUserName.setText(user.getMuserNick());
        }
    }

    @OnClick(R.id.btn_save)
    public void checkInput() {
        String nick = mEtUpdateUserName.getText().toString().trim();
        if (TextUtils.isEmpty(nick)) {
            CommonUtils.showLongToast(R.string.nick_name_connot_be_empty);
        } else if (nick.equals(user.getMuserNick())) {
            CommonUtils.showLongToast(R.string.update_nick_fail_unmodify);
        } else {
            updateNick(nick);
        }
    }

    private void updateNick(String nick) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.update_user_nick));
        dialog.show();
        model = new ModelUser();
        model.updataNick(this, user.getMuserName(), nick, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                int msg = R.string.update_fail;
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if (result != null) {
                        if (result.isRetMsg()) {
                            msg = R.string.update_user_nick_success;
                            User user =(User) result.getRetData();
                            saveNewUser(user);
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            if (result.getRetCode() == I.MSG_USER_SAME_NICK||
                                    result.getRetCode()==I.MSG_USER_UPDATE_NICK_FAIL) {
                                msg = R.string.update_nick_fail_unmodify;
                            }
                        }
                    }
                }
                CommonUtils.showLongToast(msg);
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                CommonUtils.showLongToast(R.string.update_fail);
                dialog.dismiss();
            }
        });
    }

    private void saveNewUser(User user) {
        FuLiShopApplication.setUser(user);
        UserDao.getInstance().savaUser(user);
    }


}
