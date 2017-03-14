package com.wangmingqiang.money.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangmingqiang.money.R;
import com.wangmingqiang.money.bean.InvestAllBean;
import com.wangmingqiang.money.ui.MyProgress;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public class InvestAllAdapter extends BaseAdapter {


    private final List<InvestAllBean.DataBean> data;

    public InvestAllAdapter(List<InvestAllBean.DataBean> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_invest_all, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }


        viewHolder.pName.setText(data.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
