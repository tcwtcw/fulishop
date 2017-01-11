package cn.ucai.fulishop.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.R;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvFooter)
        TextView mTvFooter;

       public FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    public void setFooterString(String str){
        mTvFooter.setText(str);
    }
    }


