package cn.ucai.fulishop.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

import cn.ucai.fulishop.R;
import cn.ucai.fulishop.bean.CategoryChildBean;
import cn.ucai.fulishop.bean.CategoryGroupBean;

/**
 * Created by Administrator on 2017/1/13 0013.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {
    Context mContext;
    ArrayList<CategoryGroupBean> mGroupBeen;
    ArrayList<ArrayList<CategoryChildBean>> mChildBean;

    public CategoryAdapter(Context context, ArrayList<CategoryGroupBean> groupBeen,
                           ArrayList<ArrayList<CategoryChildBean>> childBean) {
        mContext = context;
        mGroupBeen = groupBeen;
        mChildBean = childBean;
        mChildBean = new ArrayList<>();
        mChildBean.addAll(childBean);
    }

    @Override
    public int getGroupCount() {
        return mGroupBeen!=null?mGroupBeen.size():0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildBean!=null&&mChildBean.get(groupPosition)!=null?
                mChildBean.get(groupPosition).size():0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupBeen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (mChildBean != null && mChildBean.get(groupPosition) != null) {
            return mChildBean.get(groupPosition).get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_category_group, null);
        return inflate;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_category_child, null);
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
