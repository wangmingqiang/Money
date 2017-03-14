package com.wangmingqiang.money.viewholder;

import android.view.View;
import android.widget.TextView;

import com.wangmingqiang.money.R;
import com.wangmingqiang.money.bean.InvestAllBean;
import com.wangmingqiang.money.ui.MyProgress;
import com.wangmingqiang.money.utils.UiUtils;

import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public class InvestHolder extends BaseHolder<InvestAllBean.DataBean> {

    @InjectView(R.id.p_name)
    TextView pName;
    @InjectView(R.id.p_money)
    TextView pMoney;
    @InjectView(R.id.p_yearlv)
    TextView pYearlv;
    @InjectView(R.id.p_suodingdays)
    TextView pSuodingdays;
    @InjectView(R.id.p_minzouzi)
    TextView pMinzouzi;
    @InjectView(R.id.p_minnum)
    TextView pMinnum;
    @InjectView(R.id.p_progresss)
    MyProgress pProgresss;


    @Override
    public View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }


    @Override
    public void setChildData() {


        InvestAllBean.DataBean dataBean = getT();

        pName.setText(dataBean.getName());
        pMoney.setText(dataBean.getMoney());
        pYearlv.setText(dataBean.getYearRate());
        pSuodingdays.setText(dataBean.getSuodingDays());
        pMinzouzi.setText(dataBean.getMinTouMoney());
        pMinnum.setText(dataBean.getMemberNum());


        int parseInt = Integer.parseInt(dataBean.getProgress());
        pProgresss.setPro(parseInt);



    }
}
