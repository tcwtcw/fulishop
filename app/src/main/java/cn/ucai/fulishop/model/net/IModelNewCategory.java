package cn.ucai.fulishop.model.net;

import android.content.Context;

import cn.ucai.fulishop.bean.CategoryChildBean;
import cn.ucai.fulishop.bean.CategoryGroupBean;
import cn.ucai.fulishop.bean.GoodsDetailsBean;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public interface IModelNewCategory {
    void downData(Context context, OnCompleteListener<CategoryGroupBean[]> listener);

    void downData(Context context, int parentId, OnCompleteListener<CategoryChildBean[]> listener);

}
