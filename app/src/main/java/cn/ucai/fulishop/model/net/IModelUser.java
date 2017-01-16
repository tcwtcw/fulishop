package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.bean.GoodsDetailsBean;
import cn.ucai.fulishop.bean.User;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelUser {
    void register(Context context, String username, String nickname, String password, OnCompleteListener<String> listener);
    void login(Context context, String username, String password, OnCompleteListener<String> listener);

}
