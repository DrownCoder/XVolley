package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.Factory.GetRequestFactory;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public abstract class RequestBuilder<T extends RequestBuilder> {
    protected  int type;
    protected String url;
    protected Map<String,String> params;

    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    public abstract GetRequestFactory build();
}
