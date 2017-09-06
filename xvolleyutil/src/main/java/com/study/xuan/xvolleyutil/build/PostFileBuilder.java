package com.study.xuan.xvolleyutil.build;

import android.graphics.Bitmap;

import com.study.xuan.xvolleyutil.factory.PostRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;
import com.study.xuan.xvolleyutil.utils.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/30.
 * Description :input the description of this file.
 */

public class PostFileBuilder extends RequestBuilder<PostFileBuilder> implements ContainParams {
    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    private final String boundary = "apiclient-" + System.currentTimeMillis();
    private final String mimeType = "multipart/form-data;boundary=" + boundary;
    private byte[] multipartBody;
    ByteArrayOutputStream bos;
    DataOutputStream dos;

    public PostFileBuilder() {
        super();
        bos = new ByteArrayOutputStream();
        dos = new DataOutputStream(bos);
    }

    @Override
    public RequestFactory build() {
        // send multipart form data necesssary after file data
        try {
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            // pass to multipart body
            multipartBody = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PostRequestFactory(url, params, type, mClass, multipartBody, mimeType);
    }

    @Override
    protected int setRequestType() {
        return METHOD_POST_FILE;
    }

    public PostFileBuilder addFile(String fileName, File file) {
        try {
            buildPart(dos, ByteUtils.File2byte(file.getAbsolutePath()), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public PostFileBuilder addBitmap(String fileName, Bitmap bitmap) {
        try {
            buildPart(dos, ByteUtils.Bitmap2Byte(bitmap), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName)
            throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"pic\"; " +
                "filename=\""
                + fileName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }

    @Override
    public RequestBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    @Override
    public RequestBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }
}
