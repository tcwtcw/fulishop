package cn.ucai.fulishop.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.BoutiqueBean;
import cn.ucai.fulishop.controller.activity.BoutiqueChildActivity;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {
    public  static  void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
    public  static void startActivity(Activity context,Class<?> clz) {
        context.startActivity(new Intent(context,clz));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    public  static void startActivity(Activity context,Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoBoutiqueChild(Context context, BoutiqueBean boutiqueBean) {
        Intent intent = new Intent(context, BoutiqueChildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID,boutiqueBean.getId() );
        intent.putExtra(I.Boutique.NAME,boutiqueBean.getTitle());
        startActivity((Activity) context,intent);
    }
}
