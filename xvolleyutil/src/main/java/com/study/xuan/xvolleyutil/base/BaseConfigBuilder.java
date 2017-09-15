package com.study.xuan.xvolleyutil.base;

import android.net.Uri;
import android.text.TextUtils;

import com.study.xuan.xvolleyutil.interceptor.Interceptor;
import com.study.xuan.xvolleyutil.utils.Exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author : xuan.
 * Data : 2017/9/13.
 * Description :build the base url config
 */

public class BaseConfigBuilder {
    private String baseUrl;
    private Map<String, String> baseUrlParams;
    private List<Interceptor> baseIntercepts;

    public BaseConfigBuilder() {
    }

    public BaseConfigBuilder baseUrl(String url) {
        this.baseUrl = url;
        return this;
    }

    public BaseConfigBuilder addParam(String key, String value) {
        if (baseUrlParams == null) {
            baseUrlParams = new HashMap<>();
        }
        baseUrlParams.put(key, value);
        return this;
    }

    public BaseConfigBuilder addParams(Map<String, String> baseUrlParams) {
        if (baseUrlParams == null) {
            baseUrlParams = new HashMap<>();
        }
        this.baseUrlParams.putAll(baseUrlParams);
        return this;
    }

    public BaseConfigBuilder addIntercept(Interceptor interceptor) {
        if (baseIntercepts == null) {
            baseIntercepts = new ArrayList<>();
        }
        baseIntercepts.add(interceptor);
        return this;
    }

    public BaseConfigBuilder addIntercepts(List<Interceptor> interceptors) {
        if (baseIntercepts == null) {
            baseIntercepts = new ArrayList<>();
        }
        baseIntercepts.addAll(interceptors);
        return this;
    }

    public Config build() {
        if (TextUtils.isEmpty(baseUrl)) {
            Exceptions.illegalArgument("the url can'r be null");
        }
        if (baseUrlParams.size() == 0) {
            return new Config(baseUrl, baseIntercepts);
        }
        Uri.Builder builder = Uri.parse(baseUrl).buildUpon();
        Set<String> keys = baseUrlParams.keySet();
        for (String key : keys) {
            builder.appendQueryParameter(key, baseUrlParams.get(key));
        }
        return new Config(baseUrl, baseIntercepts);
    }
}
