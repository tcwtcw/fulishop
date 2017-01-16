package cn.ucai.fulishop.application;

import android.app.Application;

import cn.ucai.fulishop.bean.User;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class FuLiCenterApplication extends Application {

    private static FuLiCenterApplication instance;
    public static FuLiCenterApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        FuLiCenterApplication.user = user;
    }

    private static User user;
}
