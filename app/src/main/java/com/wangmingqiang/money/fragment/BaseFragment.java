package com.wangmingqiang.money.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangmingqiang.money.ui.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by wangmingqiang on 2017/3/13.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view=View.inflate(getActivity(),getLayoutid(),null);

        loadingPager=new LoadingPager(getActivity()) {
            @Override
            protected void onSuccess(ResultState resultState, View sucessView) {
                ButterKnife.inject(BaseFragment.this,sucessView);
                initData(resultState.getJson());
            }

            @Override
            protected String getUrl() {
                return getChildUrl();
            }

            @Override
            public int getViewId() {
                return getLayoutid();
            }
        };
        return loadingPager;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadingPager.loadData();
    }


    protected abstract int getLayoutid();

    protected abstract void initListener();

    protected abstract void initData(String json);

    //每一个fragment返回的地址
    public abstract String getChildUrl();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
