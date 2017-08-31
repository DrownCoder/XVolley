package com.study.xuan.xvolleyutil.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * Author : xuan.
 * Data : 2017/8/29.
 * Description :post json
 */

public class JsonPostRequest extends StringRequest {
    private String requestBody;

    public JsonPostRequest(int method, String url, Response.Listener<String> listener, Response
            .ErrorListener errorListener, String requestBody) {
        super(method, url, listener, errorListener);
        this.requestBody = requestBody;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return requestBody == null ? null : requestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    requestBody, "utf-8");
            return null;
        }
    }
}
