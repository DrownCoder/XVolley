package com.study.xuan.xvolleyutil.factory;

import android.content.Context;

import com.android.volley.Request;
import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.ICallBack;
import com.study.xuan.xvolleyutil.callback.OnErrorListener;
import com.study.xuan.xvolleyutil.callback.OnSuccessListener;
import com.study.xuan.xvolleyutil.request.JsonPostRequest;
import com.study.xuan.xvolleyutil.request.PostRequest;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :the post request factory
 */

public class PostRequestFactory extends RequestFactory {
    private String content;
    public PostRequestFactory(String url, Map<String, String> params, int type, Class c) {
        super(url, params, type, c);
    }

    /**
     * post string in body need content
     *
     * @param content postStringRequest
     */
    public PostRequestFactory(String url, Map<String, String> params, int type, Class c, String
            content) {
        super(url, params, type, c);
        this.content = content;
    }

    @Override
    Request createRequest(Context context, final ICallBack callBack, int type) {
        Request request = null;
        switch (type) {
            case XVolley.METHOD_POST_STRING:
                request = new PostRequest(url, params
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                }
                        , new OnErrorListener(context, callBack));
                break;
            case XVolley.METHOD_POST_GSON:
                request = new PostRequest(url, params
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(transformResponse(response));
                    }
                }, new OnErrorListener(context, callBack));
                break;
            case XVolley.METHOD_STRING_POST:
                request = new JsonPostRequest(Request.Method.POST, url
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(response);
                    }
                }, new OnErrorListener(context, callBack), content);
                break;
            case XVolley.METHOD_STRING_POST_GSON:
                request = new JsonPostRequest(Request.Method.POST, url
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onResponse(String response) {
                        callBack.onSuccess(transformResponse(response));
                    }
                }, new OnErrorListener(context, callBack), content);
                break;

        }
        return request;
    }
}
