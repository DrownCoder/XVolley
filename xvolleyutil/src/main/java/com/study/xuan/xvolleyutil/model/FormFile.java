package com.study.xuan.xvolleyutil.model;

import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;

import com.study.xuan.xvolleyutil.utils.ByteUtils;

/**
 * Author : xuan.
 * Data : 2017/9/11.
 * Description :the upload entity
 */
public class FormFile {
    //the name
    private String mName;
    //the file's name
    private String mFileName;
    //the file's path
    private String mPath;
    //the file's mime
    private String mMime;

    public FormFile(String name,String fileName,String path, String mimeType) {
        this.mName = name;
        this.mFileName = fileName;
        this.mPath = path;
        this.mMime = mimeType;
    }

    public FormFile(String name,String fileName,String path) {
        this.mName = name;
        this.mFileName = fileName;
        this.mPath = path;
    }

    public String getName() {
        return mName;
    }

    public String getFileName() {
        return mFileName;
    }

    // 对图片进行二进制转换
    public byte[] getValue() {
        return ByteUtils.File2byte(mPath);
    }

    public String getMime() {
        return mMime;
    }

    public void setmMime(String mMime) {
        this.mMime = mMime;
    }
}