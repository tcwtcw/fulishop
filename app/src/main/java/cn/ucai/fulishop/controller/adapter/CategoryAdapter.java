package cn.ucai.fulishop.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.bean.CategoryChildBean;
import cn.ucai.fulishop.bean.CategoryGroupBean;
import cn.ucai.fulishop.model.utils.ImageLoader;
import cn.ucai.fulishop.view.MFGT;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<CategoryGroupBean> mGroupBean;
    ArrayList<ArrayList<CategoryChildBean>> mChildBean;

    public CategoryAdapter(Context context, ArrayList<CategoryGroupBean> groupBean, ArrayList<ArrayList<CategoryChildBean>> childBean) {
        this.context = context;
        mGroupBean = new ArrayList<>();
        mGroupBean.addAll(groupBean);
        mChildBean = new ArrayList<>();
        mChildBean.addAll(childBean);
    }

    @Override
    public int getGroupCount() {
        return mGroupBean != null ? mGroupBean.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildBean != null && mChildBean.get(groupPosition) != null ? mChildBean.get(groupPosition).size() : 0;
    }

    @Override
    public CategoryGroupBean getGroup(int groupPosition) {
        return mGroupBean.get(groupPosition);
    }

    @Override
    public CategoryChildBean getChild(int groupPosition, int childPosition) {
        if (mChildBean != null && mChildBean.get(groupPosition) != null) {
            return mChildBean.get(groupPosition).get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        GroupViewHolder vh=null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_category_group, null);
            vh = new GroupViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (GroupViewHolder) view.getTag();
        }
        ImageLoader.downloadImg(context,vh.ivGroupThumb,mGroupBean.get(groupPosition).getImageUrl());
        vh.tvGroupName.setText(mGroupBean.get(groupPosition).getName());
        vh.ivIndicator.setImageResource(isExpanded?R.mipmap.expand_off:R.mipmap.expand_on);
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isExpanded, View view, ViewGroup parent) {
        ChildViewHolder vh = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_category_child, null);
            vh = new ChildViewHolder(view);
            view.setTag(vh);
        } else {
            vh = (ChildViewHolder) view.getTag();
        }
        ImageLoader.downloadImg(context,vh.ivCategoryChildThumb,getChild(groupPosition,childPosition).getImageUrl());
        vh.tvCategoryChildName.setText(getChild(groupPosition,childPosition).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MFGT.gotoCategoryChild(context,mChildBean.get(groupPosition).get(childPosition).getId());
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void initData(ArrayList<CategoryGroupBean> groupBean,
                         ArrayList<ArrayList<CategoryChildBean>> childBean) {
        mGroupBean.clear();
        mGroupBean.addAll(groupBean);
        mChildBean.clear();
        mChildBean.addAll(childBean);
        notifyDataSetChanged();

    }

    static class GroupViewHolder {
        @BindView(R.id.iv_Group_Thumb)
        ImageView ivGroupThumb;
        @BindView(R.id.tv_group_name)
        TextView tvGroupName;
        @BindView(R.id.iv_indicator)
        ImageView ivIndicator;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.iv_category_child_thumb)
        ImageView ivCategoryChildThumb;
        @BindView(R.id.tv_category_child_name)
        TextView tvCategoryChildName;
        @BindView(R.id.layout_category_child)
        RelativeLayout layoutCategoryChild;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
