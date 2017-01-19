package cn.ucai.fulishop.model.net;

import android.content.Context;

import java.io.File;

import cn.ucai.fulishop.bean.GoodsDetailsBean;
import cn.ucai.fulishop.bean.MessageBean;
import cn.ucai.fulishop.bean.User;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelUser {
    void login(Context context, String username, String password, OnCompleteListener<String> listener);
    void register(Context context, String username, String usernick, String password, OnCompleteListener<String> listener);
    void updataNick(Context context, String username, String usernick, OnCompleteListener<String> listener);
    void uploadAvatar(Context context, String username, File file, OnCompleteListener<String> listener);
    void collecCount(Context context,String username, OnCompleteListener<MessageBean> listener);
}
