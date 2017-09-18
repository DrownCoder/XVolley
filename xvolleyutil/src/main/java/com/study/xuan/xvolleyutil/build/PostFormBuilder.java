package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.base.Config;
import com.study.xuan.xvolleyutil.factory.PostRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;
import com.study.xuan.xvolleyutil.utils.Exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :build the form post
 */

public class PostFormBuilder extends RequestBuilder<PostFormBuilder> implements ContainParams{
    public PostFormBuilder(Config baseUrl) {
        super(baseUrl);
    }

    @Override
    public RequestFactory build() {
        return new PostRequestFactory(this);
    }

    @Override
    protected int requestType() {
        return METHOD_POST_STRING;
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        if (params == null) {
            Exceptions.illegalArgument("the parasm can't be null");
            this.mParams = new LinkedHashMap<>();
        }else{
            this.mParams = params;
        }
        return this;
    }

    @Override
    public RequestBuilder addParam(String key, String val) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return this;
    }

    @Override
    public RequestBuilder addParams(Map<String, String> params) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.putAll(params);
        return null;
    }
}
