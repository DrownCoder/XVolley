package com.study.xuan.xvolleyutil.request;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class GsonPostRequest<T> extends GsonRequest<T> {
    private Map<String,String> mParams;
    public GsonPostRequest(String url,Map<String,String> params, Class<T> clazz, Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
        super(Method.POST, url, clazz, listener, errorListener);
        this.mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
