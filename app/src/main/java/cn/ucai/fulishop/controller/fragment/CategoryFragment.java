package cn.ucai.fulishop.controller.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.bean.CategoryChildBean;
import cn.ucai.fulishop.bean.CategoryGroupBean;
import cn.ucai.fulishop.controller.adapter.CategoryAdapter;
import cn.ucai.fulishop.model.net.IModelNewCategory;
import cn.ucai.fulishop.model.net.ModelCategory;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.utils.ConvertUtils;
import cn.ucai.fulishop.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    Context mContext;

    @BindView(R.id.elv_category)
    ExpandableListView mElvCategory;
    @BindView(R.id.tv_nomore)
    TextView mTvNomore;

    IModelNewCategory model;
    CategoryAdapter mAdapter;

    ArrayList<CategoryGroupBean> mGroupBeen = new ArrayList<>();
    ArrayList<ArrayList<CategoryChildBean>> mChildBean = new ArrayList<>();

    int groupCount;
    public CategoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout= inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, layout);

        mAdapter = new CategoryAdapter(getContext(), mGroupBeen, mChildBean);
        mElvCategory.setAdapter(mAdapter);
        mElvCategory.setGroupIndicator(null);
        initView(false);
        initData();
        return layout;
    }

    private void initData() {
        model = new ModelCategory();
        model.downData(getContext(), new OnCompleteListener<CategoryGroupBean[]>() {
            @Override
            public void onSuccess(CategoryGroupBean[] result) {
                if (result != null) {
                    initView(true);
                    ArrayList<CategoryGroupBean> list = ConvertUtils.array2List(result);
                    mGroupBeen.addAll(list);
                    for (int i=0;i<list.size();i++) {
                        mChildBean.add(new ArrayList<CategoryChildBean>());
                        downloadChildData(list.get(i).getId(),i);
                    }
                } else {
                    initView(false);
                }
            }

            @Override
            public void onError(String error) {
                initView(false);
            }
        });
    }

    private void downloadChildData(int id, final int index) {
        model.downData(getContext(), id, new OnCompleteListener<CategoryChildBean[]>() {
            @Override
            public void onSuccess(CategoryChildBean[] result) {
                groupCount++;
                if (result != null) {
                    ArrayList<CategoryChildBean> list = ConvertUtils.array2List(result);
                    mChildBean.set(index, list);
                }
                if (groupCount == mGroupBeen.size()) {
                    mAdapter.initData(mGroupBeen, mChildBean);
                }
            }

            @Override
            public void onError(String error) {
                groupCount++;
            }
        });
    }

    private void initView(boolean hasData) {
        mTvNomore.setVisibility(hasData?View.GONE:View.VISIBLE);
        mElvCategory.setVisibility(hasData?View.VISIBLE:View.GONE);

    }

}
