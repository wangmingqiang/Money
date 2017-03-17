package com.wangmingqiang.money.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangmingqiang.money.R;
import com.wangmingqiang.money.utils.pay.AliPayUtils;
import com.wangmingqiang.money.utils.pay.PayKeys;

import butterknife.InjectView;

public class ReChargeActivity extends BaseActivity {

    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.chongzhi_text)
    TextView chongzhiText;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.et_chongzhi)
    EditText etChongzhi;
    @InjectView(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @InjectView(R.id.yue_tv)
    TextView yueTv;
    @InjectView(R.id.btn_chongzhi)
    Button btnChongzhi;



    //商户PID
    public static final String PARTNER = PayKeys.PRIVATE;
    //商户收款账号
    public static final String SELLER = PayKeys.DEFAULT_SELLER;
    //商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = PayKeys.PRIVATE;
    //支付宝公钥
    public static final String RSA_PUBLIC = PayKeys.PUBLIC;


    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;





    @Override
    protected void initListener() {

        etChongzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString().trim();
                if(TextUtils.isEmpty(money)) {
                    //设置不可点击
                    btnChongzhi.setClickable(false);
                    //设置充值的背景颜色
                    btnChongzhi.setBackgroundResource(R.drawable.btn_02);
                }else {
                    btnChongzhi.setClickable(true);
                    btnChongzhi.setBackgroundResource(R.drawable.btn_01);
                }

            }
        });


        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AliPayUtils.getInstance().pay(ReChargeActivity.this,"1");
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_re_charge;
    }






}
