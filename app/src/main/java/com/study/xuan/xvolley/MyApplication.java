package com.study.xuan.xvolley;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.base.BaseConfigBuilder;
import com.study.xuan.xvolleyutil.interceptor.Interceptor;
import com.study.xuan.xvolleyutil.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /*Map<String, String> header = new HashMap<>();
        header.put("user-token", "AEUHY98QIASUDH");
        BaseConfigBuilder config = new BaseConfigBuilder();
        config.baseUrl("http://192.168.117.102/get.php")
                .addParam("client", "Android")
                .addParam("position", "北京")
                .addHeaders(header)
                .addIntercept(new Interceptor() {
                    @Override
                    public Request intercept(Chain chain) {
                        Request request = chain.request();
                        LogUtil.log("intercept","baseIntercept:"+request.getUrl());
                        return request;
                    }
                });*/
        XVolley.create(getApplicationContext());
                //.init(config.build());
    }
}
