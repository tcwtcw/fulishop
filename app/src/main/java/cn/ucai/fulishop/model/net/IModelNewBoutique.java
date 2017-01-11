package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.bean.BoutiqueBean;
import cn.ucai.fulishop.bean.NewGoodsBean;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelNewBoutique {
    public void downData(Context context, OnCompleteListener<BoutiqueBean[]> listener);

}
