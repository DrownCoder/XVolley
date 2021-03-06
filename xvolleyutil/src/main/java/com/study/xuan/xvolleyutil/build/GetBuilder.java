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
        if (mParams != null) {
            config.url = appendParams(config.url, mParams);
        }
        return new GetRequestFactory(this);
    }

    @Override
    protected int requestType() {
        return METHOD_GET_STRING;
    }

    @Override
    public GetBuilder params(Map<String, String> params) {
        if (params == null) {
            Exceptions.illegalArgument("the parasm can't be null");
            this.mParams = new LinkedHashMap<>();
        }else{
            this.mParams = params;
        }
        return this;
    }

    @Override
    public GetBuilder addParam(String key, String val) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(key, val);
        return this;
    }

    @Override
    public GetBuilder addParams(Map<String, String> params) {
        if (this.mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.putAll(params);
        return null;
    }
}
