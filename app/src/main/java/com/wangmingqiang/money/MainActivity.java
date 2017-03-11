package com.wangmingqiang.money;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wangmingqiang.money.fragment.HomeFragment;
import com.wangmingqiang.money.fragment.InvestFragment;
import com.wangmingqiang.money.fragment.MoreFragment;
import com.wangmingqiang.money.fragment.PropertyFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_fl)
    FrameLayout mainFl;
    @InjectView(R.id.main_rg)
    RadioGroup mainRg;
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        switchFragment(R.id.rb_main);
        initlistener();
    }



    private void initlistener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        mainRg.check(R.id.rb_main);
    }

    private void switchFragment(int checkedId) {
        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hiddenFragment(ft);
        switch (checkedId){
            case R.id.rb_main:
                if(homeFragment==null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_fl,homeFragment);
                }
                ft.show(homeFragment);
                break;
            case R.id.rb_invest:
                if(investFragment==null) {
                    investFragment = new InvestFragment();
                    ft.add(R.id.main_fl,investFragment);
                }
                ft.show(investFragment);
                break;
            case R.id.rb_more:
                if(moreFragment==null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.main_fl,moreFragment);
                }
                ft.show(moreFragment);
                break;
            case R.id.rb_propert:
                if(propertyFragment==null) {
                    propertyFragment = new PropertyFragment();
                    ft.add(R.id.main_fl,propertyFragment);
                }
                ft.show(propertyFragment);
                break;
        }
        //提交事务
        ft.commit();
    }

    private void hiddenFragment(FragmentTransaction ft) {
        if(homeFragment!=null) {
            ft.hide(homeFragment);
        }
        if(propertyFragment!=null) {
            ft.hide(propertyFragment);
        }
        if(moreFragment!=null) {
            ft.hide(moreFragment);
        }
        if(investFragment!=null) {
            ft.hide(investFragment);
        }
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
