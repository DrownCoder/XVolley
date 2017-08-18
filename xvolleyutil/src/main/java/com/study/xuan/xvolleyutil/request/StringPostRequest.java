package com.study.xuan.xvolleyutil.request;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class StringPostRequest extends StringRequest {
    private Map<String,String> mParams;
    public StringPostRequest(String url, Map<String,String> params, Response.Listener<String> listener,
                             Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        this.mParams = params;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
