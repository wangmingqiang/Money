package com.wangmingqiang.money.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangmingqiang.money.R;

/**
 * Created by wangmingqiang on 2017/3/10.
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = UiUtils.getView(R.layout.fragment_home);
        View view=View.inflate(getActivity(), R.layout.fragment_home,null);
        return view;

    }

}
