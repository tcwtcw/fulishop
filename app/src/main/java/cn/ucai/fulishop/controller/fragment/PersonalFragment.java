package cn.ucai.fulishop.controller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.FuLiShopApplication;
import cn.ucai.fulishop.bean.MessageBean;
import cn.ucai.fulishop.bean.User;
import cn.ucai.fulishop.model.net.IModelUser;
import cn.ucai.fulishop.model.net.ModelUser;
import cn.ucai.fulishop.model.net.OnCompleteListener;
import cn.ucai.fulishop.model.utils.ImageLoader;
import cn.ucai.fulishop.model.utils.L;
import cn.ucai.fulishop.view.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {
    private static final String TAG = PersonalFragment.class.getSimpleName();
    @BindView(R.id.iv_user_avatar)
    ImageView mIvUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView mTvUserName;
    @BindView(R.id.center_user_order_lis)
    GridView mCenterUserOrderLis;

    IModelUser model;
    @BindView(R.id.tv_collect_count)
    TextView mTvCollectCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_personal_center, container, false);
        ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    private void initData() {
        User user = FuLiShopApplication.getUser();
        if (user != null) {
            loadUserInfo(user);
            getCollectCount();
        } else {
            L.e(TAG, "initData,null user");
//            MFGT.gotoLogin(getActivity());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void loadUserInfo(User user) {
//        ImageLoader.downloadImg(getContext(),mIvUserAvatar,user.getAvatarPath());
        ImageLoader.setAvatar(ImageLoader.getAvatarUrl(user), getContext(), mIvUserAvatar);
        mTvUserName.setText(user.getMuserNick());
        loadCollectCount("0");
    }

    private void getCollectCount(){
        model = new ModelUser();
        model.collecCount(getContext(), FuLiShopApplication.getUser().getMuserName(),
                new OnCompleteListener<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean result) {
                        L.e(TAG,"result="+result);
                        if(result!=null && result.isSuccess()){
                            loadCollectCount(result.getMsg());
                        }else{
                            loadCollectCount("0");
                        }
                    }

                    @Override
                    public void onError(String error) {
                        L.e(TAG,"error="+error);
                        loadCollectCount("0");
                    }
                });
    }

    private void loadCollectCount(String count) {
        mTvCollectCount.setText(String.valueOf(count));
    }

    @OnClick({R.id.tv_center_settings, R.id.center_user_info})
    public void settings() {
        MFGT.gotoSettings(getActivity());
    }



}
