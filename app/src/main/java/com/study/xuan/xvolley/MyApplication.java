package com.study.xuan.xvolley;

import android.app.Application;

import com.study.xuan.xvolleyutil.XVolley;


/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XVolley.create(getApplicationContext());
    }
}
