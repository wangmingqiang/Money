package com.wangmingqiang.money.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wangmingqiang.money.fragment.BaseFragment;

import java.util.List;

/**
 * Created by wangmingqiang on 2017/3/14.
 */

public class InvesAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public InvesAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if(fragments.size()>0&&fragments!=null) {
            this.fragments=fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
