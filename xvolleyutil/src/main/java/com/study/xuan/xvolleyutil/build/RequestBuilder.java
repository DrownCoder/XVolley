package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.base.Config;
import com.study.xuan.xvolleyutil.factory.RequestFactory;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public abstract class RequestBuilder<T extends RequestBuilder> {
    public static final int METHOD_GET_STRING = 0;
    public static final int METHOD_POST_STRING = 1;
    public static final int METHOD_STRING_POST = 2;
    public static final int METHOD_POST_FILE = 3;
    // gson
    public static final int METHOD_GET_GSON = 4;
    public static final int METHOD_POST_GSON = 5;
    public static final int METHOD_STRING_POST_GSON = 6;
    public static final int METHOD_POST_FILE_GOSN = 7;

    protected Class mClass = String.class;
    protected  int type;
    protected Map<String,String> params;
    protected Config config;

    public T url(String url) {
        this.config.url = url;
        return (T) this;
    }
    /**
     * go to parse by Gson
     */
    public T goGson(Class classz) {
        this.mClass = classz;
        switch (type) {
            case METHOD_GET_STRING:
                type = METHOD_GET_GSON;
                break;
            case METHOD_POST_STRING:
                type = METHOD_POST_GSON;
                break;
            case METHOD_STRING_POST:
                type = METHOD_STRING_POST_GSON;
                break;
            case METHOD_POST_FILE:
                type = METHOD_POST_FILE_GOSN;
                break;
        }
        return (T) this;
    }

    public RequestBuilder(Config config) {
        if (config == null) {
            this.config = new Config();
        }else {
            this.config = config;
        }
        type = requestType();
    }

    public abstract RequestFactory build();

    protected abstract int requestType();

}
