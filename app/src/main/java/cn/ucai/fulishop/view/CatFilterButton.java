package cn.ucai.fulishop.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cn.ucai.fulishop.R;
import cn.ucai.fulishop.bean.CategoryChildBean;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class CatFilterButton extends Button{
    boolean isExpan;
    public CatFilterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initCatFileterButton(String groupName, ArrayList<CategoryChildBean> list) {
        this.setText(groupName);
        setCatFilterButtonListener();
    }

    private void setCatFilterButtonListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExpan) {

                } else {

                }
                setArrow();
            }
        });
    }

    private void setArrow() {
        Drawable right;
        if (isExpan) {
            right = getResources().getDrawable(R.mipmap.arrow2_up);
        } else {
            right = getResources().getDrawable(R.mipmap.arrow2_down);
        }
        right.setBounds(0,0,right.getIntrinsicWidth(),right.getIntrinsicHeight());
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,right,null );
        isExpan = !isExpan;
    }
}
