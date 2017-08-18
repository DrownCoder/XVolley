package com.study.xuan.xvolleyutil.callback;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class OnErrorListener<T> extends WeakListener implements Response.ErrorListener {

    public OnErrorListener(Context activity, CallBack<T> callBack) {
        super(activity, callBack);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callBack.onError(error);
        callBack.onAfter();
    }
}
