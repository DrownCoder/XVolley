package com.study.xuan.xvolleyutil.callback;

import android.content.Context;

import com.android.volley.Response;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class OnSuccessListener<T> extends WeakListener implements Response.Listener<T> {

    public OnSuccessListener(Context activity, CallBack<T> callBack) {
        super(activity, callBack);
    }

    @Override
    public void onResponse(T response) {
        callBack.onSuccess(response);
    }
}
