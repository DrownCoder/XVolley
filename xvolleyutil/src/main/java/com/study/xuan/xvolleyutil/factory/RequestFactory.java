package com.study.xuan.xvolleyutil.factory;


import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.study.xuan.xvolleyutil.base.Config;
import com.study.xuan.xvolleyutil.base.XVolley;
import com.study.xuan.xvolleyutil.build.RequestBuilder;
import com.study.xuan.xvolleyutil.callback.ICallBack;
import com.study.xuan.xvolleyutil.interceptor.Interceptor;
import com.study.xuan.xvolleyutil.utils.Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :base factory
 */

public abstract class RequestFactory{
    private Class mClass;
    protected String url;
    protected Map params;
    protected int type;
    protected Config config;

    private List<Interceptor> interceptors;
    private int index = 0;

    public RequestFactory(RequestBuilder builder) {
        this.config = builder.getConfig();
        this.params = builder.getmParams();
        this.type = builder.getType();
        this.mClass = builder.getmClass();
    }

    public final void execute(Context context, ICallBack callBack) {
        if (TextUtils.isEmpty(config.url)) {
            Exceptions.illegalArgument("the url can't be null");
        }else {
            url = config.url;
        }
        if (config.baseIntercepts != null && config.baseIntercepts.size() > 0) {
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            interceptors.addAll(config.baseIntercepts);
        }
        Request request = createRequest(context,callBack,type);
        doRequestWithIntercept(request, callBack);
    }

    /**
     * intercept the request by chain
     */
    private void doRequestWithIntercept(Request request, ICallBack callBack) {
        callBack.onBefore();
        if (interceptors.size() > 0) {
            Interceptor.Chain chain = new ApplicationInterceptorChain(index + 1, request);
            XVolley.getInstance().add(interceptors.get(index).intercept(chain));
        } else {
            XVolley.getInstance().add(request);
        }
    }

    public RequestFactory addInterceptor(Interceptor intercepter) {
        if (interceptors == null) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(intercepter);
        return this;
    }

    private class ApplicationInterceptorChain implements Interceptor.Chain {
        private final int index;
        private final Request request;

        ApplicationInterceptorChain(int index, Request request) {
            this.index = index;
            this.request = request;
        }

        @Override
        public Request request() {
            if (index < interceptors.size()) {
                Interceptor.Chain chain = new ApplicationInterceptorChain(index + 1, request);
                Interceptor interceptor = interceptors.get(index);
                return interceptor.intercept(chain);
            }
            return request;
        }
    }


    abstract Request createRequest(Context context, ICallBack callBack,int type);

    /**
     * transform the respone by GSON
     * @param response the String respone
     */
    protected final Object transformResponse(String response) {
        if (!mClass.getClass().equals(String.class)) {
            Gson gson = new Gson();
            return gson.fromJson(response, mClass);
        }
        return response;
    }
}
