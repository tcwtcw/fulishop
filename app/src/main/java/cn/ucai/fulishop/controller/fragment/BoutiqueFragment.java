package cn.ucai.fulishop.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.BoutiqueBean;
import cn.ucai.fulishop.bean.NewGoodsBean;
import cn.ucai.fulishop.controller.adapter.BoutiqueAdapter;
import cn.ucai.fulishop.model.net.IModelNewBoutique;
import cn.ucai.fulishop.model.net.ModelBoutique;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.utils.CommonUtils;
import cn.ucai.fulishop.model.utils.ConvertUtils;
import cn.ucai.fulishop.view.SpaceItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoutiqueFragment extends Fragment {

    LinearLayoutManager gm;

    BoutiqueAdapter mAdapter;
    ArrayList<NewGoodsBean> mList = new ArrayList<>();
    IModelNewBoutique mModel;
    @BindView(R.id.tvRefresh)
    TextView tvRefresh;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.tv)
    TextView tv;

    public BoutiqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_boutique, container, false);
        ButterKnife.bind(this, layout);
        initView();
        mModel = new ModelBoutique();
        //initData(I.ACTION_DOWNLOAD);
        setListener();
        return layout;
    }

    private void setListener() {
        setPullDownListener();
    }

    private void setPullDownListener() {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                tvRefresh.setVisibility(View.VISIBLE);
                initData(I.ACTION_PULL_DOWN);
            }
        });
    }


    private void initData(final int action) {
        mModel.downData(getContext(), new OnCompleteListener<BoutiqueBean[]>() {
            @Override
            public void onSuccess(BoutiqueBean[] result) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                srl.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                if (result != null && result.length > 0) {
                    ArrayList<BoutiqueBean> list = ConvertUtils.array2List(result);
                    if (action == I.ACTION_DOWNLOAD || action == I.ACTION_PULL_DOWN) {
                        mAdapter.initData(list);
                    } else {
                        mAdapter.addData(list);
                    }
                } else {
                    tv.setVisibility(View.VISIBLE);
                    srl.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String error) {
                srl.setRefreshing(false);
                tvRefresh.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
                CommonUtils.showShortToast(error);
            }
        });
    }

    private void initView() {
        srl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_yellow),
                getResources().getColor(R.color.google_red));
        gm = new LinearLayoutManager(getContext());
        rv.addItemDecoration(new SpaceItemDecoration(12));
        rv.setLayoutManager(gm);
        rv.setHasFixedSize(true);
        mAdapter = new BoutiqueAdapter(getContext(), null);
        rv.setAdapter(mAdapter);
        srl.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv)
    public void onClick() {
        initData(I.ACTION_DOWNLOAD);
    }
}
