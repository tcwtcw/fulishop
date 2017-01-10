package cn.ucai.fulishop.application;

import android.app.Application;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class FuLiShopApplication extends Application {
    private static FuLiShopApplication instance;

    public FuLiShopApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
