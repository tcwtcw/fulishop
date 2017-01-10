package cn.ucai.fulishop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton rbNewGoods, rbBoutique, rbCategory, rbCart, rbPersonal;
    RadioButton[] rbs = new RadioButton[5];
    int index, currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rbNewGoods = (RadioButton) findViewById(R.id.rb_NewsGood);
        rbBoutique = (RadioButton) findViewById(R.id.rb_Boutique);
        rbCategory = (RadioButton) findViewById(R.id.rb_Category);
        rbCart = (RadioButton) findViewById(R.id.rb_Cart);
        rbPersonal = (RadioButton) findViewById(R.id.rb_Personal);

        rbs[0] = rbNewGoods;
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
        for (int i=0;i<rbs.length;i++) {
            if (index != i) {
                rbs[i].setChecked(false);
            } else {
                rbs[i].setChecked(true);
            }
        }
        currentIndex = index;
    }
}
