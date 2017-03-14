package com.wangmingqiang.money.fragment;

import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.wangmingqiang.money.R;
import com.wangmingqiang.money.adapter.InvestAllAdapter;
import com.wangmingqiang.money.bean.InvestAllBean;
import com.wangmingqiang.money.command.AppNetConfig;

import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public class InvestAllFragmen extends BaseFragment {
    @InjectView(R.id.invest_all_lv)
    ListView investAllLv;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        InvestAllBean investAllBean = JSON.parseObject(json, InvestAllBean.class);

        InvestAllAdapter adapter=new InvestAllAdapter(investAllBean.getData());

        investAllLv.setAdapter(adapter);

    }

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }


}
