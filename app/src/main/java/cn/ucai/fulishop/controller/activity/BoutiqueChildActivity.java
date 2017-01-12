package cn.ucai.fulishop.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulishop.R;
import cn.ucai.fulishop.application.I;
import cn.ucai.fulishop.controller.fragment.NewGoodsFragment;
import cn.ucai.fulishop.view.MFGT;

public class BoutiqueChildActivity extends AppCompatActivity {

    @BindView(R.id.tv_common_title)
    TextView mTvCommonTitle;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_child);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new NewGoodsFragment())
                .commit();
        mTvCommonTitle.setText(getIntent().getStringExtra(I.Boutique.NAME));
    }

    @OnClick(R.id.back)
    public void onClick() {
        MFGT.finish(this);
    }
}
