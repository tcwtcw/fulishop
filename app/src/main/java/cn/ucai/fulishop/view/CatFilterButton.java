package cn.ucai.fulishop.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.bean.CategoryChildBean;
import cn.ucai.fulishop.controller.activity.CategoryChildActivity;
import cn.ucai.fulishop.model.utils.ImageLoader;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class CatFilterButton extends Button {
    boolean isExpan;
    PopupWindow mPopupWindow;
    Context mContext;
    CatFilterAdapter adapter;
    GridView mGridView;
    String groupName;

    public CatFilterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public void initCatFileterButton(String groupName, ArrayList<CategoryChildBean> list) {
        this.groupName = groupName;
        this.setText(groupName);
        setCatFilterButtonListener();
        adapter = new CatFilterAdapter(mContext, list);
        initGridView();
    }

    private void initGridView() {
        mGridView = new GridView(mContext);
        mGridView.setVerticalSpacing(10);
        mGridView.setHorizontalSpacing(10);
        mGridView.setNumColumns(GridView.AUTO_FIT);
        mGridView.setAdapter(adapter);
    }

    private void setCatFilterButtonListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExpan) {
                    initPopup();
                } else {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                }
                setArrow();
            }
        });
    }

    private void initPopup() {
        mPopupWindow = new PopupWindow();
        mPopupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xbb000000));
        mPopupWindow.setContentView(mGridView);
        mPopupWindow.showAsDropDown(this);
    }

    private void setArrow() {
        Drawable right;
        if (isExpan) {
            right = getResources().getDrawable(R.mipmap.arrow2_up);
        } else {
            right = getResources().getDrawable(R.mipmap.arrow2_down);
        }
        right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, right, null);
        isExpan = !isExpan;
    }

    class CatFilterAdapter extends BaseAdapter {
        Context context;
        ArrayList<CategoryChildBean> list;

        public CatFilterAdapter(Context context, ArrayList<CategoryChildBean> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CategoryChildBean getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CatFilterViewHolder vh = null;
            if (view == null) {
                view=View.inflate(context, R.layout.item_cat_filter, null);
                vh = new CatFilterViewHolder(view);
                view.setTag(vh);
            } else {
                vh = (CatFilterViewHolder) view.getTag();
            }
            vh.bind(i);
            return view;
        }

        class CatFilterViewHolder {
            @BindView(R.id.iv_category_child_thumb)
            ImageView mIvCategoryChildThumb;
            @BindView(R.id.tv_category_child_name)
            TextView mTvCategoryChildName;
            @BindView(R.id.layout_category_child)
            RelativeLayout mLayoutCategoryChild;

            CatFilterViewHolder(View view) {
                ButterKnife.bind(this, view);
            }

            public void bind(final int position) {
                ImageLoader.downloadImg(context,mIvCategoryChildThumb,list.get(position).getImageUrl());
                mTvCategoryChildName.setText(list.get(position).getName());
                mLayoutCategoryChild.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MFGT.gotoCategoryChild(mContext,
                                list.get(position).getId(),
                                groupName,
                                list);
                        MFGT.finish((CategoryChildActivity)mContext);
                    }
                });
            }
        }
    }
}
