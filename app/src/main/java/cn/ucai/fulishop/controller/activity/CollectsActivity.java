package cn.ucai.fulishop.controller.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.FuLiShopApplication;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.bean.CollectBean;
import cn.ucai.fulishop.bean.NewGoodsBean;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.controller.adapter.GoodsAdapter;
import cn.ucai.fulishop.model.net.IModelUser;
import cn.ucai.fulishop.model.net.ModelUser;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.utils.ConvertUtils;
import cn.ucai.fulishop.view.DisplayUtils;
import cn.ucai.fulishop.view.SpaceItemDecoration;

public class CollectsActivity extends AppCompatActivity {

    @BindView(R.id.tvRefresh)
    TextView mTvRefresh;
    @BindView(R.id.Recycler)
    RecyclerView mRecycler;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;

    IModelUser model;
    User user;
    int pageId = 1;
    GridLayoutManager gm;
    GoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collects);
        ButterKnife.bind(this);
        DisplayUtils.initBackWithTitle(this, "收藏的宝贝");
        user = FuLiShopApplication.getUser();
        if (user == null) {
            finish();
        } else {
            initData();
            initView();
        }
    }

    private void initData() {
        model = new ModelUser();
        model.getCollects(this, user.getMuserName(), pageId, I.PAGE_SIZE_DEFAULT, new OnCompleteListener<CollectBean[]>() {
            @Override
            public void onSuccess(CollectBean[] result) {
                if (result == null) {

                } else {
                    ArrayList<CollectBean> list = ConvertUtils.array2List(result);
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initView() {
        mSrl.setColorSchemeColors(
                getResources().getColor(R.color.google_blue),
                getResources().getColor(R.color.google_green),
                getResources().getColor(R.color.google_yellow),
                getResources().getColor(R.color.google_red));
        gm = new GridLayoutManager(this, I.COLUM_NUM);
        mRecycler.addItemDecoration(new SpaceItemDecoration(12));
        mRecycler.setLayoutManager(gm);
        mRecycler.setHasFixedSize(true);
        mAdapter = new GoodsAdapter(this, new ArrayList<NewGoodsBean>());
        mRecycler.setAdapter(mAdapter);
    }
}
