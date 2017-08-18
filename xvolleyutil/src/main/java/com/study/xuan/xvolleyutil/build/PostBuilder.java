package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.Factory.GetRequestFactory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class PostBuilder extends RequestBuilder implements ContainParams {
    @Override
    public RequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public GetRequestFactory build() {
        return null;
    }
}
