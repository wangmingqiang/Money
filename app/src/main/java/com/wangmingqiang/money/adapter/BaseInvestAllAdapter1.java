package com.wangmingqiang.money.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter1<T> extends BaseAdapter {


    public final List<T> list;

    public BaseInvestAllAdapter1(List<T> list){
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=getChildView(position,convertView,parent);
        return view;
    }

    public abstract View getChildView(int position, View convertView, ViewGroup parent) ;

}
