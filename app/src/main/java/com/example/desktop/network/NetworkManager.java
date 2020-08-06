package com.example.desktop.network;

import android.os.Message;
import android.util.Log;

import com.example.desktop.config.AppConfig;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkManager {


    private static String requestUrl;
    private static HashMap<String, Object> parameter;
    private static OkHttpClient client;
    public NetworkManager() {}

    // 单例模式
    public static  NetworkManager networkManager = new NetworkManager();

    public static NetworkManager networkBase(String url, HashMap<String, Object>parameters) {
        requestUrl = url;
        parameter = parameters;
        client = new OkHttpClient.Builder().build();
        return networkManager;
    }

    public void postRequest(final ResponseCallBack callBack) {

        // 2序列化请求参数
        JSONObject jsonObject = new JSONObject(parameter);
        String jsonStr = jsonObject.toString();
        RequestBody requestBody =RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonStr);

        // 3 创建请求
        Request request = new Request.Builder()
                .url(ApiConfig.BASE_URL + requestUrl)
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
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("1","登录成功");
                String string = response.body().string();
                callBack.onSuccess(string);
            }
        });
    }
}
