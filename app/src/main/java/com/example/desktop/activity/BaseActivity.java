package com.example.desktop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.desktop.MainActivity;
import com.example.desktop.R;

public class BaseActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context = this;
    }

    ///
    /// @name showToast
    /// @description 显示Toast
    /// @parameters
    /// @return
    /// @author liuca
    /// @date 2020/8/6
    ///
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    ///
    /// @name navigateTo
    /// @description 跳转到某页
    /// @parameters
    /// @return
    /// @author liuca
    /// @date 2020/8/6
    ///
    public void navigateTo(Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(intent);
    }
}