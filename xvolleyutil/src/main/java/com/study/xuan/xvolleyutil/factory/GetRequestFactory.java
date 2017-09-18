package com.study.xuan.xvolleyutil.factory;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.study.xuan.xvolleyutil.base.Config;
import com.study.xuan.xvolleyutil.build.RequestBuilder;
import com.study.xuan.xvolleyutil.callback.ICallBack;
import com.study.xuan.xvolleyutil.callback.OnErrorListener;
import com.study.xuan.xvolleyutil.callback.OnSuccessListener;
import com.study.xuan.xvolleyutil.request.XStringRequest;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class GetRequestFactory extends RequestFactory {
    public GetRequestFactory(RequestBuilder builder) {
        super(builder);
    }

    @Override
    Request createRequest(Context context, final ICallBack callBack, int type) {
        Request request = null;
        switch (type) {
            case RequestBuilder.METHOD_GET_STRING:
                request = new XStringRequest(url,config.header
                        , new OnSuccessListener(context, callBack) {
                    @Override
                    public void onSuccess(Context wContext, String response) {
                        callBack.onSuccess(wContext, response);
                    }
                }, new OnErrorListener(context, callBack));
                break;
            case RequestBuilder.METHOD_GET_GSON:
                request = new XStringRequest(url,config.header
                        , new OnSuccessListener(context, callBack) {
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
