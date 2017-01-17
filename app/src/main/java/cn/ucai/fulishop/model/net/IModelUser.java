package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.bean.GoodsDetailsBean;
import cn.ucai.fulishop.bean.User;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelUser {
    void login(Context context, String username, String password, OnCompleteListener<String> listener);
    void register(Context context, String username, String usernick, String password, OnCompleteListener<String> listener);
    void updataNick(Context context, String username, String usernick, OnCompleteListener<String> listener);
}
