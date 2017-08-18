package com.study.xuan.xvolleyutil.Factory;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.CallBack;
import com.study.xuan.xvolleyutil.callback.OnErrorListener;
import com.study.xuan.xvolleyutil.callback.OnSuccessListener;
import com.study.xuan.xvolleyutil.request.GsonRequest;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class GetRequestFactory<T> extends RequestFactory {
    public GetRequestFactory(String url, Map<String, String> params,int type) {
        super(url, params,type);
    }

    @Override
    Request createRequest(Context context, CallBack callBack,int type) {
        Request request = null;
        switch (type) {
            case XVolley.METHOD_GET_STRING:
                request = new StringRequest(url
                        , new OnSuccessListener<String>(context, callBack)
                        , new OnErrorListener<String>(context, callBack));
                break;
            case XVolley.METHOD_GET_GSON:

                break;
        }
        return request;
    }
}
