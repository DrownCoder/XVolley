package com.study.xuan.xvolleyutil.Factory;


import android.content.Context;

import com.android.volley.Request;
import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public abstract class RequestFactory{
    String url;
    Map<String,String> params;
    int type;

    public RequestFactory(String url, Map<String,String> params,int type) {
        this.url = url;
        this.params = params;
        this.type = type;
    }

    public final void execute(Context context,CallBack callBack) {
        Request request = createRequest(context,callBack,type);
        callBack.onBefore();
        XVolley.getInstance().add(request);
    }

    abstract Request createRequest(Context context, CallBack callBack,int type);

}
