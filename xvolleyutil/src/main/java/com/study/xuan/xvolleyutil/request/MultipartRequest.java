package com.study.xuan.xvolleyutil.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.Map;

public class MultipartRequest extends PostRequest {
    private final Map<String, String> mHeaders;
    private final String mMimeType;
    private final byte[] mMultipartBody;

    public MultipartRequest(String url, Map<String, String> params, Map<String, String> headers,
                            String mimeType, byte[] multipartBody, Response.Listener<String>
                                    listener, Response.ErrorListener errorListener) {
        super(url, params, listener, errorListener);
        this.mHeaders = headers;
        this.mMimeType = mimeType;
        this.mMultipartBody = multipartBody;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return (mHeaders != null) ? mHeaders : super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        return mMimeType;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return mMultipartBody;
    }
}