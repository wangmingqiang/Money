package com.wangmingqiang.money.adapter;

import com.wangmingqiang.money.bean.InvestAllBean;
import com.wangmingqiang.money.viewholder.BaseHolder;
import com.wangmingqiang.money.viewholder.InvestHolder;

import java.util.List;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public class InvestAllAdapter3 extends BaseInvestAllAdapter3<InvestAllBean.DataBean> {


    public InvestAllAdapter3(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    protected BaseHolder getHolder() {
        return new InvestHolder();
    }
}
