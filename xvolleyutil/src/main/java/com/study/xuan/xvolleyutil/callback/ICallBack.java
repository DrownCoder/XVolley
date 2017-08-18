package com.study.xuan.xvolleyutil.callback;

import com.android.volley.VolleyError;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public interface ICallBack<T> {
    void onBefore();

    void onSuccess(T response);

    void onError(VolleyError error);

    void onAfter();
}
