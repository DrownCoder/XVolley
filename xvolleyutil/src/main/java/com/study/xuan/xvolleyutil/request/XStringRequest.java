package com.study.xuan.xvolleyutil.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/9/18.
 * Description :input the description of this file.
 */

public class XStringRequest extends StringRequest {
    private Map<String, String> header;

    public XStringRequest(String url, Map<String, String> header, Response.Listener<String>
            listener, Response.ErrorListener
            errorListener) {
        super(url, listener, errorListener);
        this.header = header;
    }

    public XStringRequest(int method, String url, Map<String, String> header, Response
            .Listener<String> listener, Response
            .ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.header = header;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (header != null && header.size() > 0) {
            return header;
        }
        return super.getHeaders();
    }
}
