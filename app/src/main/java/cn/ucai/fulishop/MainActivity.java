package cn.ucai.fulishop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.controller.fragment.NewGoodsFragment;


public class MainActivity extends AppCompatActivity {
    RadioButton[] rbs = new RadioButton[5];
    int index, currentIndex;
    @BindView(R.id.rb_NewsGood)
    RadioButton rbNewsGood;
    @BindView(R.id.rb_Boutique)
    RadioButton rbBoutique;
    @BindView(R.id.rb_Category)
    RadioButton rbCategory;
    @BindView(R.id.rb_Cart)
    RadioButton rbCart;
    @BindView(R.id.rb_Personal)
    RadioButton rbPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new NewGoodsFragment())
                .commit();
    }

    private void initView() {

        ButterKnife.bind(this);
        rbs[0] = rbNewsGood;
        rbs[1] = rbBoutique;
        rbs[2] = rbCategory;
        rbs[3] = rbCart;
        rbs[4] = rbPersonal;

    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.rb_NewsGood:
                index = 0;
                break;
            case R.id.rb_Boutique:
                index = 1;
                break;
            case R.id.rb_Category:
                index = 2;
                break;
            case R.id.rb_Cart:
                index = 3;
                break;
            case R.id.rb_Personal:
                index = 4;
                break;
        }
        if (index != currentIndex) {
            setRadioStatus();
        }
    }

    private void setRadioStatus() {
        for (int i = 0; i < rbs.length; i++) {
            if (index != i) {
                rbs[i].setChecked(false);
            } else {
                rbs[i].setChecked(true);
            }
        }
        currentIndex = index;
    }
}
