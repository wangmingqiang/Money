package com.wangmingqiang.money.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangmingqiang.money.R;
import com.wangmingqiang.money.adapter.InvesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by wangmingqiang on 2017/3/10.
 */

public class InvestFragment extends BaseFragment {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.invest_vp)
    ViewPager investVp;
    @InjectView(R.id.tv_invest_all)
    TextView tvInvestAll;
    @InjectView(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @InjectView(R.id.tv_invest_hot)
    TextView tvInvestHot;
    private List<BaseFragment> fragments;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initListener() {
        investVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectText(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

    }

    @Override
    protected void initData(String json) {

        initListener();

        //设置标题
        initTitle();
        //初始化fragment
        initFragment();
        //初始化viewPager
        initViewPager();
        //设置默认选中的tab
        initTab();


    }

    private void initTab() {
        selectText(0);
    }

    private void selectText(int id) {
        //把所有的背景色还原成默认值
        hiddenTextViewState();
        switch (id) {
            case 0:
                //改变当前的背景色
                tvInvestAll.setBackgroundColor(Color.GRAY);
                break;
            case 1:
                //改变当前的背景色
                tvInvestRecommend.setBackgroundColor(Color.GRAY);
                break;
            case 2:
                //改变当前的背景色
                tvInvestHot.setBackgroundColor(Color.GRAY);
                break;

        }

    }

    private void hiddenTextViewState() {

        tvInvestRecommend.setBackgroundColor(Color.WHITE);
        tvInvestHot.setBackgroundColor(Color.WHITE);
        tvInvestAll.setBackgroundColor(Color.WHITE);
    }

    private void initViewPager() {
        investVp.setAdapter(new InvesAdapter(getChildFragmentManager(), fragments));
    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new InvestAllFragmen());
        fragments.add(new InvestRecommendFragment());
        fragments.add(new InvestHotFragmen());
    }

    private void initTitle() {
        baseTitle.setText("投资");
        baseBack.setVisibility(View.GONE);
        baseSetting.setVisibility(View.GONE);
    }

    @Override
    public String getChildUrl() {
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_invest_all, R.id.tv_invest_recommend, R.id.tv_invest_hot})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_invest_all:
                investVp.setCurrentItem(0);
                break;
            case R.id.tv_invest_recommend:
                investVp.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot:
                investVp.setCurrentItem(2);
                break;
        }
    }
}
