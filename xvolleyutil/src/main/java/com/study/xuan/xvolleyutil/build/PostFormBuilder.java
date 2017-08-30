package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.factory.PostRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;
import com.study.xuan.xvolleyutil.utils.LogUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :build the form post
 */

public class PostFormBuilder extends RequestBuilder<PostFormBuilder> implements ContainParams {
    public PostFormBuilder(int mMethodType, Class c) {
        super(mMethodType, c);
    }

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
    public RequestFactory build() {
        LogUtil.log("url", url);
        return new PostRequestFactory(url, params, type, mClass);
    }
}
