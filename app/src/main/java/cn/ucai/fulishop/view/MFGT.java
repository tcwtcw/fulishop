package cn.ucai.fulishop.view;

import android.app.Activity;
import android.content.Intent;

import cn.ucai.fulishop.R;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class MFGT {
    public  static void startActivity(Activity context,Class<?> clz) {
        context.startActivity(new Intent(context,clz));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
}
