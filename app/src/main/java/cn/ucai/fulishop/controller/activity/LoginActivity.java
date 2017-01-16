package cn.ucai.fulishop.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.view.MFGT;

/**
 * Created by Administrator on 2017/1/16 0016.
 */
public class LoginActivity extends AppCompatActivity {
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
                break;
            case R.id.btn_register:
                MFGT.gotoRegister(this);
                break;
        }
    }
}
