package com.study.xuan.xvolleyutil.callback;

import android.content.Context;

import com.android.volley.VolleyError;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public abstract class CallBack<T> implements ICallBack<T> {

    @Override
    public void onBefore() {

    }

    @Override
    public void onSuccess(Context context, T response) {

    }
    
    @Override
    public void onError(VolleyError error) {

    }

    @Override
    public void onAfter() {

    }

    @Override
    public void onFinish() {

    }
}
