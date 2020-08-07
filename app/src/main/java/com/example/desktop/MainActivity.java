package com.example.desktop;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.desktop.activity.BaseActivity;
import com.example.desktop.activity.LoginActivity;
import com.example.desktop.activity.RegisterActivity;

public class MainActivity extends BaseActivity implements CompoundButton.OnClickListener {

    Button loginButton;
    Button registerButton;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
    }

    @Override
    protected void initData() {
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                navigateTo(LoginActivity.class);
                break;
            case R.id.register_button:
                navigateTo(RegisterActivity.class);
                break;
        }
    }
}