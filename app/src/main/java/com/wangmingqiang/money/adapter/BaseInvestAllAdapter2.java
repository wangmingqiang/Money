package com.wangmingqiang.money.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter2<T> extends BaseAdapter {

    public final List<T> list;

    public BaseInvestAllAdapter2(List<T> list){
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
        ViewHolder viewHolder;
        if(convertView==null) {
            convertView=initView();//由子类去实现
            viewHolder=new ViewHolder(convertView);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

         /**
        * 第二层抽出
        * viewHolder 为了减少findviewbyID
        * */

        T t = list.get(position);

        setData(t,convertView);


        return convertView;
    }

    protected abstract void setData(T t, View convertView);

    public abstract View initView();


    class ViewHolder{

        public ViewHolder(View convertView) {
            convertView.setTag(this);
            ButterKnife.inject(BaseInvestAllAdapter2.this,convertView);
        }
    }
}
