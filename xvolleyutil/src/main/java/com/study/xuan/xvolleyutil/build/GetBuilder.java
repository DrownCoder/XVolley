package com.study.xuan.xvolleyutil.build;

import android.net.Uri;

import com.study.xuan.xvolleyutil.factory.GetRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;
import com.study.xuan.xvolleyutil.utils.LogUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :request by GET method.
 */

public class GetBuilder extends RequestBuilder<GetBuilder> implements ContainParams {

    public GetBuilder() {
        super();
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
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
            url = appendParams(url, params);
        }
        LogUtil.log("url", url);
        return new GetRequestFactory(url, params, type, mClass);
    }

    @Override
    protected int setRequestType() {
        return METHOD_GET_STRING;
    }
}
