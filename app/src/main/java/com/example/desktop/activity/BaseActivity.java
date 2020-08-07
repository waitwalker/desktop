package com.example.desktop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.example.desktop.MainActivity;
import com.example.desktop.R;

public abstract class BaseActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        context = this;
        initView();
        initData();
    }

    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract  void initData();

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
    /// @name showToastSync
    /// @description 子线程处理Toast
    /// @parameters
    /// @return
    /// @author liuca
    /// @date 2020/8/6
    ///
    public void showToastSync(String message) {
        Looper.prepare();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        Looper.loop();
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

    ///
    /// @name sharedPreferencesPut
    /// @description 
    /// @parameters 
    /// @return 
    /// @author liuca
    /// @date 2020/8/6
    ///
    public void sharedPreferencesPut(String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences("desktop",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
    }

    ///
    /// @name sharedPreferencesGet
    /// @description 
    /// @parameters 
    /// @return 
    /// @author liuca
    /// @date 2020/8/6
    ///
    public String sharedPreferencesGet(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("desktop",MODE_PRIVATE);
        return sharedPreferences.getString(key,"无");
    }
}