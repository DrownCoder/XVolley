package com.study.xuan.xvolleyutil.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/29.
 * Description :post json
 */

public class JsonPostRequest extends XStringRequest {
    private String requestBody;

    public JsonPostRequest(int method, String url, Map<String, String> header, Response
            .Listener<String> listener, Response
            .ErrorListener errorListener, String requestBody) {
        super(method, url, header, listener, errorListener);
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
