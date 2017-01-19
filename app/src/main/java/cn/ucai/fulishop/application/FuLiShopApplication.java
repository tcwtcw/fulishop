package cn.ucai.fulishop.application;

import android.app.Application;

import java.util.HashMap;

import cn.ucai.fulishop.bean.CartBean;
import cn.ucai.fulishop.bean.User;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class FuLiShopApplication extends Application {
    private static FuLiShopApplication instance;
    public static FuLiShopApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        FuLiShopApplication.user = user;
    }
}
