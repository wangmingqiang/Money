package com.wangmingqiang.money.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangmingqiang.money.R;
import com.wangmingqiang.money.command.AppNetConfig;
import com.wangmingqiang.money.utils.LoadNet;
import com.wangmingqiang.money.utils.LoadNetHttp;

import java.util.HashMap;

import butterknife.InjectView;

/**
 * Created by wangmingqiang on 2017/3/15.
 */
public class RegesterActivity extends BaseActivity {
    @InjectView(R.id.base_title)
    TextView baseTitle;
    @InjectView(R.id.base_back)
    ImageView baseBack;
    @InjectView(R.id.base_setting)
    ImageView baseSetting;
    @InjectView(R.id.et_register_number)
    EditText etRegisterNumber;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void initListener() {

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校驗
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();

                /**判断两个密码是否一致  判断密码的长度  判断是否注册过
                 * 还有很多的逻辑判断我们可以去完善
                 */

                if(TextUtils.isEmpty(name)|| TextUtils.isEmpty(number) ||TextUtils.isEmpty(pwd) || TextUtils.isEmpty(pwdAgain)) {
                        showToast("兄弟不能為空能仔細點不傻逼");
                    return;
                }

                //请求服务器
                HashMap<String, String> map = new HashMap<>();
                map.put("name",name);
                map.put("phone",number);
                map.put("password",pwd);

                LoadNet.getDataPost(AppNetConfig.REGISTER, map, new LoadNetHttp() {
                    @Override
                    public void success(String context) {

                        JSONObject jsonObject = JSON.parseObject(context);
                        Boolean isExist = jsonObject.getBoolean("isExist");
                        
                        if(isExist) {
                            showToast("账号已经存在");
                        }else {
                            showToast("注册成功");
                            finish();
                        }
                    }

                    @Override
                    public void failure(String error) {

                    }
                });

            }
        });
    }




    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        baseTitle.setText("注册");
        baseSetting.setVisibility(View.INVISIBLE);
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_regester;
    }


}
