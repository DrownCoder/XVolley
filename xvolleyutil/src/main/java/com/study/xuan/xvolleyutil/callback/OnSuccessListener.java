package com.study.xuan.xvolleyutil.callback;

import android.content.Context;

import com.android.volley.Response;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public abstract class OnSuccessListener extends WeakListener implements Response.Listener<String> {

    public OnSuccessListener(Context activity, ICallBack callBack) {
        super(activity, callBack);
    }

    @Override
    public void onResponse(String response) {
        if (activityWeakReference.get() == null) {
            callBack.onFinish();
        } else {
            onSuccess(activityWeakReference.get(), response);
        }
    }

    public abstract void onSuccess(Context wContext, String response);
}
