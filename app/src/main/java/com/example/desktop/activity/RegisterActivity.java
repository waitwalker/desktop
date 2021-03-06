package com.example.desktop.activity;
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
import com.example.desktop.network.ApiConfig;
import com.example.desktop.network.NetworkManager;
import com.example.desktop.network.ResponseCallBack;
import com.example.desktop.tools.StringTool;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements CompoundButton.OnClickListener {

    EditText accountText;
    EditText pwsText;
    Button registerButton;

    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        accountText = findViewById(R.id.register_account);
        pwsText = findViewById(R.id.register_pwd);
        registerButton = findViewById(R.id.register_page_button);
    }

    @Override
    protected void initData() {
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_page_button:
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

        NetworkManager.networkBase(ApiConfig.kRegister, map).postRequest(new ResponseCallBack() {
            @Override
            public void onSuccess(final String string) {
                showToastSync(string);
//                Message message = myHandler.obtainMessage();
//                message.obj = string;
//                myHandler.sendMessage(message);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1","注册遇到错误");
                showToastSync("注册遇到错误");
            }
        });

    }
}