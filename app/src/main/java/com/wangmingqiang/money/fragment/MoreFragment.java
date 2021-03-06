package com.wangmingqiang.money.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.wangmingqiang.money.R;
import com.wangmingqiang.money.activity.GestureEditActivity;
import com.wangmingqiang.money.activity.GuiguActivity;
import com.wangmingqiang.money.activity.RegesterActivity;
import com.wangmingqiang.money.command.AppNetConfig;
import com.wangmingqiang.money.utils.LoadNet;
import com.wangmingqiang.money.utils.LoadNetHttp;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;
import cn.sharesdk.onekeyshare.OnekeyShare;

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

        //设置以后每次退出登录进来才会保持之前的状态
        toggleMore.setChecked(getState());
        //手势密码
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
        tvMoreAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GuiguActivity.class));
            }
        });

        //重设手势密码
        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getState()) {

                    startActivity(new Intent(getActivity(),GestureEditActivity.class));
                    toggleMore.setChecked(true);
                }
            }
        });

        //用户注册
        tvMoreRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getState()) {

                    startActivity(new Intent(getActivity(),RegesterActivity.class));
                }
                toggleMore.setChecked(true);
            }
        });

        //联系客服
        rlMoreContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "haha ", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:010-56253825");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        //反馈用户
        tvMoreFankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "hahaha", Toast.LENGTH_SHORT).show();
                View view = View.inflate(getActivity(),R.layout.dialog_fankui,null);
                new AlertDialog.Builder(getActivity())
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Map<String,String> map=new HashMap<String, String>();
                                map.put("department","");
                                map.put("content","");
                                LoadNet.getDataPost(AppNetConfig.FEEDBACK, map, new LoadNetHttp() {
                                    @Override
                                    public void success(String context) {
                                        Toast.makeText(getActivity(), "反馈成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void failure(String error) {
                                        Toast.makeText(getActivity(), "反馈失败", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });
        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
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

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("来自强哥哥的分享");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://atguigu.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("大王派我来巡山");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://atguigu.com/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("尚硅谷it教育好");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("尚硅谷it教育");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://atguigu.com/");

// 启动分享GUI
        oks.show(getActivity());
    }

}
