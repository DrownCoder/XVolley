package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.factory.GetRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public abstract class RequestBuilder<T extends RequestBuilder> {
    protected Class mClass;
    protected  int type;
    protected String url;
    protected Map<String,String> params;

    public T url(String url) {
        this.url = url;
        return (T) this;
    }
    public RequestBuilder(int mMethodType,Class c) {
        this.type = mMethodType;
        this.mClass = c;
    }

    public abstract RequestFactory build();
}
