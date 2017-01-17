package cn.ucai.fulishop.controller.activity;

import android.nfc.Tag;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import cn.ucai.fulishop.MainActivity;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.FuLiShopApplication;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.model.dao.UserDao;
import cn.ucai.fulishop.model.net.SharePrefrenceUtils;
import cn.ucai.fulishop.model.utils.L;
import cn.ucai.fulishop.view.MFGT;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username = SharePrefrenceUtils.getInstance(SplashActivity.this).getUser();
                if (username != null) {
                   User user= UserDao.getInstance().getUser(username);
                    L.e(TAG,"user="+user);
                    if (user != null) {
                        FuLiShopApplication.setUser(user);
                    }
                }
                MFGT.startActivity(SplashActivity.this, MainActivity.class);
                MFGT.finish(SplashActivity.this);
            }
        }, 2000);
    }
}
