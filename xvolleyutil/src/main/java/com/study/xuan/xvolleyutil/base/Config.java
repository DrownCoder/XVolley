package com.study.xuan.xvolleyutil.base;

import com.study.xuan.xvolleyutil.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/9/15.
 * Description :input the description of this file.
 */

public class Config {
    public List<Interceptor> baseIntercepts;
    public String url;
    public Map<String, String> header;

    public Config(BaseConfigBuilder baseConfigBuilder) {
        this.url = baseConfigBuilder.getBaseUrl();
        this.baseIntercepts = baseConfigBuilder.getBaseIntercepts();
        this.header = baseConfigBuilder.getBaseHeader();
    }

    public Config() {
    }
}
