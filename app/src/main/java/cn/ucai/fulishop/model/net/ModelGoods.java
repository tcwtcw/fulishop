package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.GoodsDetailsBean;
import cn.ucai.fulishop.bean.MessageBean;
import cn.ucai.fulishop.bean.NewGoodsBean;
import cn.ucai.fulishop.model.utils.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class ModelGoods implements IModelGoods {
    @Override
    public void downData(Context context, int goodsId, OnCompleteListener<GoodsDetailsBean> listener) {
        OkHttpUtils<GoodsDetailsBean> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)
                .addParam(I.Goods.KEY_GOODS_ID, String.valueOf(goodsId))
                .targetClass(GoodsDetailsBean.class)
                .execute(listener);
    }

    @Override
    public void isCollect(Context context, int goodsId, String username, OnCompleteListener<MessageBean> listener) {
        OkHttpUtils<MessageBean> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_IS_COLLECT)
                .addParam(I.Goods.KEY_GOODS_ID, String.valueOf(goodsId))
                .addParam(I.Collect.USER_NAME, username)
                .targetClass(MessageBean.class)
                .execute(listener);
    }

    @Override
    public void setCollect(Context context, int goodsId, String username, int action, OnCompleteListener<MessageBean> listener) {
        OkHttpUtils<MessageBean> utils = new OkHttpUtils<>(context);
        String url = I.REQUEST_ADD_COLLECT;
        if (action == I.ACTION_DELETE_COLLECT) {
            url = I.REQUEST_DELETE_COLLECT;
        }
        utils.setRequestUrl(url)
                .addParam(I.Goods.KEY_GOODS_ID, String.valueOf(goodsId))
                .addParam(I.Collect.USER_NAME, username)
                .targetClass(MessageBean.class)
                .execute(listener);
    }
}
