package com.wangmingqiang.money.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangmingqiang.money.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GuiguActivity extends AppCompatActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guigu);
        ButterKnife.inject(this);
        baseTitle.setText("尚硅谷");
        baseBack.setVisibility(View.GONE);
        baseSetting.setVisibility(View.GONE);
    }
}
