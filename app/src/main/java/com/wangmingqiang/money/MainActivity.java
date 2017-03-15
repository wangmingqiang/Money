package com.wangmingqiang.money;

import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wangmingqiang.money.activity.BaseActivity;
import com.wangmingqiang.money.fragment.HomeFragment;
import com.wangmingqiang.money.fragment.InvestFragment;
import com.wangmingqiang.money.fragment.MoreFragment;
import com.wangmingqiang.money.fragment.PropertyFragment;
import com.wangmingqiang.money.utils.AppManager;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.main_fl)
    FrameLayout mainFl;
    @InjectView(R.id.main_rg)
    RadioGroup mainRg;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    public void initListener() {

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        hiddenFragment(transaction);
        switch (checkedId) {
            case R.id.rb_invest:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.main_fl, investFragment);
                }
                transaction.show(investFragment);
                break;
            case R.id.rb_main:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_fl, homeFragment);
                }
                transaction.show(homeFragment);
                break;
            case R.id.rb_more:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_fl, moreFragment);
                }
                transaction.show(moreFragment);
                break;
            case R.id.rb_propert:
                if (propertyFragment == null) {
                    propertyFragment = new PropertyFragment();
                    transaction.add(R.id.main_fl, propertyFragment);
                }
                transaction.show(propertyFragment);
                break;
        }
        transaction.commit();
    }

    //隐藏所有的fragment
    private void hiddenFragment(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (investFragment != null) {
            transaction.hide(investFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
        if (propertyFragment != null) {
            transaction.hide(propertyFragment);
        }
    }

    public void initData() {
        //添加到APPManager
        AppManager.getInstance().addActivity(this);
        //选择默认的fragment
        switchFragment(R.id.rb_main);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_main;
    }




    //双击退出
    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
