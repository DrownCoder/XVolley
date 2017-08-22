package com.study.xuan.xvolleyutil.factory;

import android.content.Context;

import com.android.volley.Request;
import com.study.xuan.xvolleyutil.XVolley;
import com.study.xuan.xvolleyutil.callback.ICallBack;
import com.study.xuan.xvolleyutil.callback.OnErrorListener;
import com.study.xuan.xvolleyutil.callback.OnSuccessListener;
import com.study.xuan.xvolleyutil.request.StringPostRequest;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class PostRequestFactory extends RequestFactory {
    public PostRequestFactory(String url, Map<String, String> params, int type, Class c) {
        super(url, params, type, c);
    }

    @Override
    Request createRequest(Context context, ICallBack callBack, int type) {
        switch (type) {
            case XVolley.METHOD_POST_STRING:
                new StringPostRequest(url,params
                        , new OnSuccessListener(context, callBack)
                        , new OnErrorListener<String>(context, callBack));
                break;
        }
        return null;
    }
}
