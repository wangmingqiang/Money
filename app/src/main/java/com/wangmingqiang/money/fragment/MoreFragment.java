package com.wangmingqiang.money.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wangmingqiang.money.R;
import com.wangmingqiang.money.activity.GestureEditActivity;

import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/10.
 */

public class MoreFragment extends BaseFragment {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.tv_more_regist)
    TextView tvMoreRegist;
    @InjectView(R.id.toggle_more)
    ToggleButton toggleMore;
    @InjectView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @InjectView(R.id.tv_more_phone)
    TextView tvMorePhone;
    @InjectView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @InjectView(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @InjectView(R.id.tv_more_share)
    TextView tvMoreShare;
    @InjectView(R.id.tv_more_about)
    TextView tvMoreAbout;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initListener() {

        baseTitle.setText("更多");
        baseBack.setVisibility(View.GONE);
        baseSetting.setVisibility(View.GONE);

        toggleMore.setChecked(getState());

        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    saveState(isChecked);

                    if(!getSetting()) {
                        startActivity(new Intent(getActivity(), GestureEditActivity.class));
                        setSeting(true);

                    }
                }else {

                    saveState(isChecked);

                }
            }
        });

    }

    private void setSeting(boolean setting){
        SharedPreferences sp = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
        sp.edit().putBoolean("setting",setting).commit();

    }

    private boolean getSetting() {

        SharedPreferences sp = getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
        return sp.getBoolean("setting",false);
    }

    private void saveState(boolean isOpen) {
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isOpen",isOpen).commit();
    }

    private Boolean getState(){
        SharedPreferences sp = getActivity().getSharedPreferences("tog_state", Context.MODE_PRIVATE);
        return sp.getBoolean("isOpen",false);
    }

    @Override
    protected void initData(String json) {

        initListener();
    }

    @Override
    public String getChildUrl() {
        return null;
    }

}
