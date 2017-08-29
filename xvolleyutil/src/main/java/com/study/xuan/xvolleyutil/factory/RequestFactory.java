package com.study.xuan.xvolleyutil.factory;


import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.ICallBack;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public abstract class RequestFactory{
    Class mClass;
    String url;
    Map<String,String> params;
    int type;

    public RequestFactory(String url, Map<String,String> params,int type ,Class c) {
        this.url = url;
        this.params = params;
        this.type = type;
        this.mClass = c;
    }

    public final void execute(Context context,ICallBack callBack) {
        Request request = createRequest(context,callBack,type);
        callBack.onBefore();
        XVolley.getInstance().add(request);
    }

    abstract Request createRequest(Context context, ICallBack callBack,int type);

    /**
     * transform the respone by GSON
     * @param response the String respone
     */
    protected Object transformResponse(String response) {
        if (!mClass.getClass().equals(String.class)) {
            Gson gson = new Gson();
            return gson.fromJson(response, mClass);
        }
        return response;
    }
}
