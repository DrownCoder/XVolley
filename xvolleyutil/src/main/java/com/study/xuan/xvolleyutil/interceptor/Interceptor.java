package com.study.xuan.xvolleyutil.interceptor;

import com.android.volley.Request;

import java.io.IOException;

/**
 * Author : xuan.
 * Data : 2017/9/13.
 * Description :intercepter
 */

public interface Interceptor {
    Request intercept(Chain chain);

    interface Chain {
        Request request();
    }
}
