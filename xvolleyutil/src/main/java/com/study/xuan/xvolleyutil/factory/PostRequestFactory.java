package com.study.xuan.xvolleyutil.factory;

import android.content.Context;

import com.android.volley.Request;
import com.study.xuan.xvolleyutil.build.PostFileBuilder;
import com.study.xuan.xvolleyutil.build.PostStringBuilder;
import com.study.xuan.xvolleyutil.build.RequestBuilder;
import com.study.xuan.xvolleyutil.callback.ICallBack;
import com.study.xuan.xvolleyutil.callback.OnErrorListener;
import com.study.xuan.xvolleyutil.callback.OnSuccessListener;
import com.study.xuan.xvolleyutil.request.JsonPostRequest;
import com.study.xuan.xvolleyutil.request.MultiPartRequest;
import com.study.xuan.xvolleyutil.request.PostRequest;


/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :the post request factory
 */

public class PostRequestFactory extends RequestFactory {
    private String content;
    private byte[] multipartBody;
    private String mimeType;

    public PostRequestFactory(RequestBuilder builder) {
        super(builder);
    }

    /**
     * post string in body need content
     *
     */
    public PostRequestFactory(PostStringBuilder builder) {
        super(builder);
        this.content = builder.getContent();
    }
    /**
     * post file in body need byte[]
     *
     */
    public PostRequestFactory(PostFileBuilder builder) {
        super(builder);
        this.multipartBody = builder.getMultipartBody();
        this.mimeType = builder.getMimeType();
    }

    @Override
    Request createRequest(Context context, final ICallBack callBack, int type) {
        Request request = null;
        switch (type) {
            case RequestBuilder.METHOD_POST_STRING:
                request = new PostRequest(url, config.header, params
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onSuccess(Context wContext, String response) {
                        callBack.onSuccess(wContext, response);
                    }
                }
                        , new OnErrorListener(context, callBack));
                break;
            case RequestBuilder.METHOD_POST_GSON:
                request = new PostRequest(url, config.header, params
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onSuccess(Context wContext, String response) {
                        callBack.onSuccess(wContext, transformResponse(response));
                    }
                }, new OnErrorListener(context, callBack));
                break;
            case RequestBuilder.METHOD_STRING_POST:
                request = new JsonPostRequest(Request.Method.POST, url, config.header
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onSuccess(Context wContext, String response) {
                        callBack.onSuccess(wContext, response);
                    }
                }, new OnErrorListener(context, callBack), content);
                break;
            case RequestBuilder.METHOD_STRING_POST_GSON:
                request = new JsonPostRequest(Request.Method.POST, url, config.header
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onSuccess(Context wContext, String response) {
                        callBack.onSuccess(wContext, transformResponse(response));
                    }
                }, new OnErrorListener(context, callBack), content);
                break;
            case RequestBuilder.METHOD_POST_FILE:
                request = new MultiPartRequest(url, config.header, params, mimeType,
                        multipartBody, new OnSuccessListener(context, callBack) {
                            @Override
                            public void onSuccess(Context wContext, String response) {
                                callBack.onSuccess(wContext, response);
                            }
                        }, new OnErrorListener(context, callBack));
                break;
            case RequestBuilder.METHOD_POST_FILE_GOSN:
                request = new MultiPartRequest(url, config.header, params, mimeType,
                        multipartBody, new OnSuccessListener(context, callBack) {
                            @Override
                            public void onSuccess(Context wContext, String response) {
                                callBack.onSuccess(wContext, transformResponse(response));
                            }
                        }, new OnErrorListener(context, callBack));
                break;

        }
        return request;
    }
}
