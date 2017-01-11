package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.bean.NewGoodsBean;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelNewGoods {
    public void downData(Context context, int catId, int pageId, OnCompleteListener<NewGoodsBean[]> listener);

}
