package com.study.xuan.xvolleyutil.build;

import android.net.Uri;

import com.study.xuan.xvolleyutil.base.Config;
import com.study.xuan.xvolleyutil.factory.GetRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;
import com.study.xuan.xvolleyutil.utils.Exceptions;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :request by GET method.
 */

public class GetBuilder extends RequestBuilder<GetBuilder> implements ContainParams{

    public GetBuilder(Config config) {
        super(config);
    }

    /**
     * 追加params到url中
     */
    protected String appendParams(String url, Map<String, String> params)
    {
        if (url == null || params == null || params.isEmpty())
        {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
    @Override
    public RequestFactory build() {
        if (params != null) {
            config.url = appendParams(config.url, params);
        }
        return new GetRequestFactory(config, params, type, mClass);
    }

    @Override
    protected int requestType() {
        return METHOD_GET_STRING;
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        if (params == null) {
            Exceptions.illegalArgument("the parasm can't be null");
            this.params = new LinkedHashMap<>();
        }else{
            this.params = params;
        }
        return this;
    }

    @Override
    public RequestBuilder addParam(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public RequestBuilder addParams(Map<String, String> params) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.putAll(params);
        return null;
    }
}
