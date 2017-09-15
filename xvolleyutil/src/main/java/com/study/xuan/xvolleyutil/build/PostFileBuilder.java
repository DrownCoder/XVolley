package com.study.xuan.xvolleyutil.build;

import com.study.xuan.xvolleyutil.base.Config;
import com.study.xuan.xvolleyutil.factory.PostRequestFactory;
import com.study.xuan.xvolleyutil.factory.RequestFactory;
import com.study.xuan.xvolleyutil.model.FormFile;
import com.study.xuan.xvolleyutil.utils.Exceptions;
import com.study.xuan.xvolleyutil.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : xuan.
 * Data : 2017/8/30.
 * Description :input the description of this file.
 */

public class PostFileBuilder extends RequestBuilder<PostFileBuilder> implements ContainParams{
    private String IMAGE_TYPE = "image/png";
    private String FILE_TYPE = "\"multipart/form-data";
    private byte[] multipartBody;
    // 数据分隔线
    private String BOUNDARY = "----XVolleyUploadFile";
    private final String mimeType = "multipart/form-data;boundary=" + BOUNDARY;

    List<FormFile> mFiles;
    public PostFileBuilder(Config config) {
        super(config);
        mFiles = new ArrayList<>();
    }

    @Override
    public RequestFactory build() {
        multipartBody = buildPart();
        return new PostRequestFactory(config, params, type, mClass, multipartBody, mimeType);
    }

    @Override
    protected int requestType() {
        return METHOD_POST_FILE;
    }

    public PostFileBuilder addFile(String name ,String fileName, String path) {
        FormFile file = new FormFile(name, fileName, path, FILE_TYPE);
        mFiles.add(file);
        return this;
    }

    public PostFileBuilder addFormFiles(List<FormFile> datas) {
        if (datas != null && datas.size() > 0) {
            mFiles.addAll(datas);
        }else{
            LogUtil.error("uploadfiles","uploads the empty files");
        }
        return this;
    }

    private byte[] buildPart() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        StringBuffer sb = new StringBuffer();
        if (mFiles.size() == 0 && params.size() == 0) {
            return null;
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            /* 第一行 */
            // `"--" + BOUNDARY + "\r\n"`
            sb.append("--" + BOUNDARY + "\r\n");

            /* 第二行 */
            // Content-Disposition: form-data; name="参数的名称"; filename="上传的文件名" +
            // "\r\n"
            sb.append("Content-Disposition: form-data;");
            sb.append(" name=\"");
            sb.append(entry.getKey());
            sb.append("\r\n");

            /* 第三行 */
            // Content-Type: 文件的 mime 类型 + "\r\n"
            sb.append("Content-Type: text/plain; charset=UTF-8");
            sb.append("\r\n");

            /* 第四行 */
            // "\r\n" 空白的一行
            sb.append("\r\n");

            sb.append(entry.getValue());
            sb.append("\r\n");
        }
        for (FormFile file : mFiles) {
            /* 第一行 */
            // `"--" + BOUNDARY + "\r\n"`
            sb.append("--" + BOUNDARY + "\r\n");

            /* 第二行 */
            // Content-Disposition: form-data; name="参数的名称"; filename="上传的文件名" +
            // "\r\n"
            sb.append("Content-Disposition: form-data;");
            sb.append(" name=\"");
            sb.append(file.getName());
            sb.append("\"");
            sb.append("; filename=\"");
            sb.append(file.getFileName());
            sb.append("\"");
            sb.append("\r\n");

            /* 第三行 */
            // Content-Type: 文件的 mime 类型 + "\r\n"
            sb.append("Content-Type: ");
            sb.append(file.getMime());
            sb.append("\r\n");

            /* 第四行 */
            // "\r\n" 空白的一行
            sb.append("\r\n");

            try {
                bos.write(sb.toString().getBytes("utf-8"));
                /* 第五行 */
                // 文件的二进制数据 + "\r\n"
                bos.write(file.getValue());
                bos.write("\r\n".getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.setLength(0);
        }
        /* 结尾行 */
        // `"--" + BOUNDARY + "--" + "\r\n"`
        String endLine = "--" + BOUNDARY + "--" + "\r\n";
        try {
            bos.write(endLine.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    @Override
    public PostFileBuilder params(Map<String, String> params) {
        if (params == null) {
            Exceptions.illegalArgument("the parasm can't be null");
            this.params = new LinkedHashMap<>();
        }else{
            this.params = params;
        }
        return this;
    }

    @Override
    public PostFileBuilder addParam(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }

    @Override
    public PostFileBuilder addParams(Map<String, String> params) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.putAll(params);
        return null;
    }
}
