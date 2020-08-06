package com.example.desktop.network;

///
/// @name ResponseCallBack
/// @description 响应回调
/// @parameters
/// @return
/// @author liuca
/// @date 2020/8/6
///
public interface ResponseCallBack {
    void onSuccess(String string);
    void onFailure(Exception e);
}
