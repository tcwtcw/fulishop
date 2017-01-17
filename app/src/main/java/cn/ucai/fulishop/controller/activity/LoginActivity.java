package cn.ucai.fulishop.controller.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.Result;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.model.dao.UserDao;
import cn.ucai.fulishop.model.net.IModelUser;
import cn.ucai.fulishop.model.net.ModelUser;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.net.SharePrefrenceUtils;
import cn.ucai.fulishop.model.utils.CommonUtils;
import cn.ucai.fulishop.model.utils.L;
import cn.ucai.fulishop.model.utils.ResultUtils;
import cn.ucai.fulishop.view.MFGT;

/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;

    IModelUser model;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.backClickArea, R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backClickArea:
                break;
            case R.id.btn_login:
                checkInput();
                break;
            case R.id.btn_register:
                MFGT.gotoRegister(this);
                break;
        }
    }

    private void checkInput() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getString(R.string.user_name_connot_be_empty));
        } else if (TextUtils.isEmpty(password)) {
            mPassword.setError(getString(R.string.password_connot_be_empty));
            mPassword.requestFocus();
        } else {
            login(username,password);
        } 
    }

    private void login(String username, String password) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.logining));
        model = new ModelUser();
        model.login(this, username, password, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    L.e(TAG,"result="+result);
                    if (result != null) {
                        if (result.isRetMsg()) {
                            User user = (User) result.getRetData();
                            boolean saveUser = UserDao.getInstance().savaUser(user);
                            L.e(TAG,"savaUSer="+saveUser);
                            SharePrefrenceUtils.getInstance(LoginActivity.this).saveUser(user.getMuserName());
                            MFGT.finish(LoginActivity.this);
                        } else {
                            if (result.getRetCode() == I.MSG_LOGIN_UNKNOW_USER) {
                                CommonUtils.showLongToast(getString(R.string.login_fail_unknow_user));
                            }
                            if (result.getRetCode() == I.MSG_LOGIN_ERROR_PASSWORD) {
                                CommonUtils.showLongToast(getString(R.string.login_fail_error_password));
                            }
                        }
                    } else {
                        CommonUtils.showLongToast(getString(R.string.login_fail));
                    }
                } else {
                    CommonUtils.showLongToast(getString(R.string.login_fail));
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                L.e(TAG,"error="+error);
                CommonUtils.showLongToast(error);
            }
        });
    }
}
