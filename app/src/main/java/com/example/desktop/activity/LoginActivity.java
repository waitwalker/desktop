package com.example.desktop.activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;


import androidx.annotation.NonNull;

import com.example.desktop.R;
import com.example.desktop.entity.LoginEntity;
import com.example.desktop.network.ApiConfig;
import com.example.desktop.network.NetworkManager;
import com.example.desktop.network.ResponseCallBack;
import com.example.desktop.tools.StringTool;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements CompoundButton.OnClickListener {

    EditText accountText;
    EditText pwsText;
    Button loginButton;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        accountText = findViewById(R.id.login_account_text);
        pwsText = findViewById(R.id.login_pwd_text);
        loginButton = findViewById(R.id.login_login_button);
    }

    @Override
    protected void initData() {
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login_button:
                String account = accountText.getText().toString().trim();
                String pwd = pwsText.getText().toString().trim();
                loginAction(account,pwd);
                break;
            default:
                break;
        }
    }

    private void loginAction(String account, String pwd) {
        if (StringTool.isEmpty(account)) {
            showToast("请输入账号");
            return;
        }

        if (pwd == null || pwd.length() == 0) {
            showToast("请输入密码");
            return;
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("account",account);
        map.put("password",pwd);

        NetworkManager.networkBase(ApiConfig.kLogin, map).postRequest(new ResponseCallBack() {
            @Override
            public void onSuccess(final String string) {
                Gson gson = new Gson();
                LoginEntity loginEntity = gson.fromJson(string, LoginEntity.class);
                if (loginEntity.getCode() == 1) {
                    LoginEntity.DataBean dataBean = loginEntity.getData();
                    String token = dataBean.getToken();
                    sharedPreferencesPut("token", token);
                    navigateTo(HomeActivity.class);
                    showToastSync(loginEntity.getMsg());
                } else {
                    showToastSync("登录失败");
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1","登录遇到错误");
                showToastSync("登录遇到错误");
            }
        });

    }
}