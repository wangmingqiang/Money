package com.wangmingqiang.money.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.wangmingqiang.money.R;
import com.wangmingqiang.money.bean.HomeBean;
import com.wangmingqiang.money.command.AppNetConfig;
import com.wangmingqiang.money.ui.MyProgress;
import com.wangmingqiang.money.utils.LoadNet;
import com.wangmingqiang.money.utils.LoadNetHttp;
import com.wangmingqiang.money.utils.ThreadPool;
import com.wangmingqiang.money.utils.UiUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/10.
 */
public class HomeFragment extends Fragment {


    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.tv_home_product)
    TextView tvHomeProduct;
    @InjectView(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @InjectView(R.id.home_progress)
    MyProgress homeProgress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = UiUtils.getView(R.layout.fragment_home);
        // View view=View.inflate(getActivity(), R.layout.fragment_home,null);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intiData();

        initListener();
    }

    private void initListener() {
        //初始化title
        baseTitle.setText("首页");
        baseBack.setVisibility(View.INVISIBLE);
        baseSetting.setVisibility(View.INVISIBLE);
    }

    private void intiData() {
        LoadNet.getDataPost(AppNetConfig.INDEX, new LoadNetHttp() {
            @Override
            public void success(String context) {
                // Toast.makeText(getActivity(), "成功" + context, Toast.LENGTH_SHORT).show();

                HomeBean homeBean = JSON.parseObject(context, HomeBean.class);

                tvHomeProduct.setText(homeBean.getProInfo().getName());
                tvHomeYearrate.setText(homeBean.getProInfo().getYearRate() + "%");

                //注意：展示UI一定要判断是不是主线程
                initProgress(homeBean.getProInfo());
                initBanner(homeBean);
            }

            @Override
            public void failure(String error) {
                Toast.makeText(getActivity(), "失败" + error, Toast.LENGTH_SHORT).show();
                Log.e("TAG", error);
            }
        });
    }

    private void initProgress(final HomeBean.ProInfoBean proInfo) {

        ThreadPool.getInstance().getGlobalThread().execute(new Runnable() {
            @Override
            public void run() {

                int progress = Integer.parseInt(proInfo.getProgress());
                for (int i = 0; i <=progress; i++) {
                    SystemClock.sleep(20);

                    homeProgress.setProgress(i);

                }
            }
        });
    }

    private void initBanner(HomeBean homeBean) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoder());
        //转化成url集合
        List<String> urls = new ArrayList<>();

        for (int i = 0; i < homeBean.getImageArr().size(); i++) {
            urls.add(AppNetConfig.BASE_URL + homeBean.getImageArr().get(i).getIMAURL());

        }
        //设置图片的集合
        banner.setImages(urls);
        //设置banner的样式
        banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
        banner.start();
    }


    public class GlideImageLoder extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
