package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.factory.PostRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;

/**
 * Author : xuan.
 * Data : 2017/8/29.
 * Description :the post request with json string body
 */

public class PostStringBuilder extends RequestBuilder<PostStringBuilder> {
    private String content;
    public PostStringBuilder() {
        super();
    }

    public RequestBuilder content(String content) {
        this.content = content;
        return this;
    }

    @Override
    public RequestFactory build() {
        return new PostRequestFactory(url,params,type,mClass,content);
    }

    @Override
    protected int setRequestType() {
        return METHOD_STRING_POST;
    }
}
