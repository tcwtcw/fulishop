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
import cn.ucai.fulishop.bean.Result;
import cn.ucai.fulishop.model.net.IModelUser;
import cn.ucai.fulishop.model.net.ModelUser;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.utils.CommonUtils;
import cn.ucai.fulishop.model.utils.L;
import cn.ucai.fulishop.model.utils.ResultUtils;
import cn.ucai.fulishop.view.DisplayUtils;
import cn.ucai.fulishop.view.MFGT;

/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.nick)
    EditText mNick;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirm_password)
    EditText mConfirmPassword;

    IModelUser model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this,"用户注册");
    }


    @OnClick(R.id.btn_register)
    public void checkInput() {
        String username = mUsername.getText().toString().trim();
        String usernick = mNick.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirm = mConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            mUsername.setError(getResources().getString(R.string.user_name_connot_be_empty));
            mUsername.requestFocus();
        } else if (!username.matches("[a-zA-Z]\\w{5,15}")) {
            mUsername.setError(getResources().getString(R.string.illegal_user_name));
            mUsername.requestFocus();
        } else if (TextUtils.isEmpty(usernick)) {
            mNick.setError(getResources().getString(R.string.user_name_connot_be_empty));
            mNick.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            mPassword.setError(getResources().getString(R.string.password_connot_be_empty));
            mPassword.requestFocus();
        } else if (TextUtils.isEmpty(confirm)) {
            mConfirmPassword.setError(getResources().getString(R.string.confirm_password_connot_be_empty));
            mConfirmPassword.requestFocus();
        } else if (!password.equals(confirm)) {
            mConfirmPassword.setError(getResources().getString(R.string.two_input_password));
            mConfirmPassword.requestFocus();
        } else {
            register(username, usernick, password);
        }
    }

    private void register(String username, String usernick, String password) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.registering));
        model = new ModelUser();
        model.register(this, username, usernick, password, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s != null) {
                    Result result = ResultUtils.getResultFromJson(s, Result.class);
                    L.e(TAG, "result=" + result);
                    if (result != null) {
                        if (result.isRetMsg()) {
                            CommonUtils.showLongToast(R.string.register_success);
                            MFGT.finish(RegisterActivity.this);
                        } else {
                            CommonUtils.showLongToast(R.string.register_fail_exists);
                        }
                    } else {
                        CommonUtils.showLongToast(R.string.register_fail);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                CommonUtils.showLongToast(error);
            }
        });
    }
}
