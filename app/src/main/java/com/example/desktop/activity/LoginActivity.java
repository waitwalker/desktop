package com.example.desktop.activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.desktop.R;
import com.example.desktop.tools.StringTool;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.json.JSONStringer;

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

        // 1创建Okhttp client
        OkHttpClient client = new OkHttpClient.Builder().build();
        Map map = new HashMap();
        map.put("account",account);
        map.put("password",pwd);

        // 2序列化请求参数
        JSONObject jsonObject = new JSONObject(map);
        String jsonStr = jsonObject.toString();
        RequestBody requestBody =RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonStr);

        // 3 创建请求
        Request request = new Request.Builder()
                .url("http://0.0.0.0:7300/mock/5ea0f178bf88582b7376c7a3/api/login")
                .addHeader("contentType","application/json;chatset=UTF-8")
                .post(requestBody)
                .build();

        // 4 创建call
        final Call call = client.newCall(request);

        // 5 添加到请求队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("1","登录遇到错误");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("1","登录成功");
            }
        });

    }
}