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
import com.example.desktop.config.AppConfig;
import com.example.desktop.network.NetworkManager;
import com.example.desktop.network.ResponseCallBack;
import com.example.desktop.tools.StringTool;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements CompoundButton.OnClickListener {

    EditText accountText;
    EditText pwsText;

    Button loginButton;

    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null) {
                Log.d("1","响应的数据:" + msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountText = findViewById(R.id.login_account_text);
        pwsText = findViewById(R.id.login_pwd_text);
        loginButton = findViewById(R.id.login_login_button);
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

        NetworkManager.networkBase("api/login",map).postRequest(new ResponseCallBack() {
            @Override
            public void onSuccess(String string) {
                Message message = myHandler.obtainMessage();
                message.obj = string;
                myHandler.sendMessage(message);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1","登录遇到错误");
            }
        });

    }
}