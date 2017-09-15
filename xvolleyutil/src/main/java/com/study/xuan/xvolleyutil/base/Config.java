package com.study.xuan.xvolleyutil.base;

import com.study.xuan.xvolleyutil.interceptor.Interceptor;

import java.util.List;

/**
 * Author : xuan.
 * Data : 2017/9/15.
 * Description :input the description of this file.
 */

public class Config {
    public List<Interceptor> baseIntercepts;
    public String url;

    public Config(String baseUrl, List<Interceptor> baseIntercepts) {
        this.url = baseUrl;
        this.baseIntercepts = baseIntercepts;
    }

    public Config() {

    }
}
