package com.example.desktop.activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.desktop.R;
import com.example.desktop.tools.StringTool;

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

    }
}