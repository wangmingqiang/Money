package com.wangmingqiang.money.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wangmingqiang.money.viewholder.BaseHolder;

import java.util.List;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter3<T> extends BaseAdapter {

    public final List<T> list;

    public BaseInvestAllAdapter3(List<T> list){
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

        BaseHolder baseHolder;

        if(convertView==null) {
            baseHolder=getHolder();

        }else{
            baseHolder= (BaseHolder) convertView.getTag();
    }

         /**
        * 第二层抽出
        * viewHolder 为了减少findviewbyID
        * */

        T t = list.get(position);

        baseHolder.setData(t);

        return baseHolder.getView();

    }

    protected abstract BaseHolder getHolder();

}
