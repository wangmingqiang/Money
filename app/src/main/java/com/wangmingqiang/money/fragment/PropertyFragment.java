package com.wangmingqiang.money.fragment;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.wangmingqiang.money.MainActivity;
import com.wangmingqiang.money.R;
import com.wangmingqiang.money.bean.UserInfo;
import com.wangmingqiang.money.command.AppNetConfig;
import com.wangmingqiang.money.utils.BitmapUtils;

import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/10.
 */

public class PropertyFragment extends BaseFragment {


    @InjectView(R.id.tv_settings)
    TextView tvSettings;
    @InjectView(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @InjectView(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @InjectView(R.id.tv_me_name)
    TextView tvMeName;
    @InjectView(R.id.rl_me)
    RelativeLayout rlMe;
    @InjectView(R.id.recharge)
    ImageView recharge;
    @InjectView(R.id.withdraw)
    ImageView withdraw;
    @InjectView(R.id.ll_touzi)
    TextView llTouzi;
    @InjectView(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @InjectView(R.id.ll_zichan)
    TextView llZichan;

    @Override
    protected int getLayoutid() {
        return R.layout.fragment_property;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(String json) {

        MainActivity activity= (MainActivity) getActivity();
        UserInfo user = activity.getUser();

        //设置用户的名字
        tvMeName.setText(user.getData().getName());

        //设置头像
        Picasso.with(getActivity())
                .load(AppNetConfig.BASE_URL+"/images/tx.png")
                .transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap bitmap) {

                Bitmap circleBitmap = BitmapUtils.circleBitmap(bitmap);

                bitmap.recycle(); //必须把原来的释放掉
                return circleBitmap;
            }

            @Override
            public String key() {
                return ""; //不能为空否则会报错
            }
        }).into(ivMeIcon);
    }

    @Override
    public String getChildUrl() {
        return null;
    }




}
