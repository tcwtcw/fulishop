package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.BoutiqueBean;
import cn.ucai.fulishop.bean.NewGoodsBean;
import cn.ucai.fulishop.model.utils.OkHttpUtils;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class ModelBoutique implements  IModelNewBoutique{
    @Override
    public void downData(Context context,  OnCompleteListener<BoutiqueBean[]> listener) {
        OkHttpUtils<BoutiqueBean[]> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_BOUTIQUES)
                .targetClass(BoutiqueBean[].class)
                .execute(listener);
    }
}
