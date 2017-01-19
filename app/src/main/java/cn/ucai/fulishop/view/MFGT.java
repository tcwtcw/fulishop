package cn.ucai.fulishop.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.BoutiqueBean;
import cn.ucai.fulishop.bean.CategoryChildBean;
import cn.ucai.fulishop.controller.activity.BoutiqueChildActivity;
import cn.ucai.fulishop.controller.activity.CategoryChildActivity;
import cn.ucai.fulishop.controller.activity.CollectsActivity;
import cn.ucai.fulishop.controller.activity.GoodsDetailsActivity;
import cn.ucai.fulishop.controller.activity.LoginActivity;
import cn.ucai.fulishop.controller.activity.RegisterActivity;
import cn.ucai.fulishop.controller.activity.SettingsActivity;
import cn.ucai.fulishop.controller.activity.UpdataNickActivity;

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
    public static void gotoGoodsDetail(Context context, int goodsId) {
        Intent intent = new Intent(context, GoodsDetailsActivity.class);
        intent.putExtra(I.GoodsDetails.KEY_GOODS_ID,goodsId);
        startActivity((Activity) context,intent);
    }
    public static void gotoCategoryChild(Context context, int catId, String groupName, ArrayList<CategoryChildBean> list) {
        Intent intent = new Intent(context, CategoryChildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID,catId);
        intent.putExtra(I.CategoryGroup.NAME, groupName);
        intent.putExtra(I.CategoryChild.DATA, list);
        startActivity((Activity)context,intent);
}

    public static void gotoLogin(Activity context) {
        context.startActivityForResult(new Intent(context,LoginActivity.class),I.REQUEST_CODE_LOGIN);
    }

    public static void gotoRegister(LoginActivity loginActivity) {
        startActivity(loginActivity,RegisterActivity.class);

    }

    public static void gotoSettings(FragmentActivity activity) {
        startActivity(activity, SettingsActivity.class);
    }

    public static void gotoUpDataNick(Activity activity) {
        activity.startActivityForResult(new Intent(activity,UpdataNickActivity.class),I.REQUEST_CODE_NICK);
    }

    public static void gotoCollects(Activity activity) {
        startActivity(activity, CollectsActivity.class);
    }
}
