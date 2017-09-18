package com.study.xuan.xvolleyutil.request;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.study.xuan.xvolleyutil.callback.OnErrorListener;
import com.study.xuan.xvolleyutil.callback.OnSuccessListener;
import com.study.xuan.xvolleyutil.model.FormFile;

import java.util.Map;

public class MultiPartRequest extends PostRequest {
    // 请求 数据通过参数的形式传入
    private String content;
    private FormFile mImage;

    private String mimeType;
    private byte[] body;

    public MultiPartRequest(String url, Map<String, String> header, Map<String, String> params,
                            String mimeType, byte[] multipartBody, OnSuccessListener onSuccessListener,
                            OnErrorListener onErrorListener) {
        super(url, header, params, onSuccessListener, onErrorListener);
        this.mimeType = mimeType;
        this.body = multipartBody;
        setShouldCache(false);
        // 设置请求的响应事件，因为文件上传需要较长的时间，所以在这里加大了，设为5秒
        setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return body;
    }

    // Content-Type: multipart/form-data; boundary=----------8888888888888
    @Override
    public String getBodyContentType() {
        return mimeType;
    }
}