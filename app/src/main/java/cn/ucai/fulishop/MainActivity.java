package cn.ucai.fulishop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulishop.controller.fragment.BoutiqueFragment;
import cn.ucai.fulishop.controller.fragment.CategoryFragment;
import cn.ucai.fulishop.controller.fragment.NewGoodsFragment;

public class MainActivity extends AppCompatActivity {
    int index, currentIndex;
    RadioButton[] rbs = new RadioButton[5];
    @BindView(R.id.layout_new_good)
    RadioButton mLayoutNewGood;
    @BindView(R.id.layout_Boutique)
    RadioButton mLayoutBoutique;
    @BindView(R.id.layout_Category)
    RadioButton mLayoutCategory;
    @BindView(R.id.layout_Cart)
    RadioButton mLayoutCart;
    @BindView(R.id.layout_Personal)
    RadioButton mLayoutPersonal;

    Fragment[] mFragments = new Fragment[5];
    NewGoodsFragment mNewGoodsFragment;
    BoutiqueFragment mBoutiqueFragment;
    CategoryFragment mCategoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rbs[0] = mLayoutNewGood;
        rbs[1] = mLayoutBoutique;
        rbs[2] = mLayoutCategory;
        rbs[3] = mLayoutCart;
        rbs[4] = mLayoutPersonal;

        mNewGoodsFragment = new NewGoodsFragment();
        mBoutiqueFragment = new BoutiqueFragment();
        mCategoryFragment = new CategoryFragment();
        mFragments[0] = mNewGoodsFragment;
        mFragments[1] = mBoutiqueFragment;
        mFragments[2] = mCategoryFragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mNewGoodsFragment)
                .add(R.id.fragment_container, mBoutiqueFragment)
                .add(R.id.fragment_container, mCategoryFragment)
                .show(mNewGoodsFragment)
                .hide(mBoutiqueFragment)
                .hide(mCategoryFragment)
                .commit();

    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.layout_new_good:
                index=0;
                break;
            case R.id.layout_Boutique:
                index=1;
                break;
            case R.id.layout_Category:
                index=2;
                break;
            case R.id.layout_Cart:
                index=3;
                break;
            case R.id.layout_Personal:
                index=4;
                break;
        }
        setFragmentListener();
        if (index != currentIndex) {
            setRadioStatus();
        }

    }

    private void setFragmentListener() {
        getSupportFragmentManager().beginTransaction().show(mFragments[index])
                .hide(mFragments[currentIndex]).commit();
    }

    private void setRadioStatus() {
        for(int i=0;i<rbs.length;i++) {
            if (index != i) {
                rbs[i].setChecked(false);
            } else {
                rbs[i].setChecked(true);
            }
        }
        currentIndex = index;
    }
}