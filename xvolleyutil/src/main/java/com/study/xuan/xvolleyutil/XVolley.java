package com.study.xuan.xvolleyutil;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.study.xuan.xvolleyutil.build.GetBuilder;
import com.study.xuan.xvolleyutil.build.PostFileBuilder;
import com.study.xuan.xvolleyutil.build.PostFormBuilder;
import com.study.xuan.xvolleyutil.build.PostStringBuilder;
import com.study.xuan.xvolleyutil.build.RequestBuilder;
import com.study.xuan.xvolleyutil.utils.Exceptions;

/**
 * Author : xuan.
 * Data : 2017/8/15.
 * Description :input the description of this file.
 */

public final class XVolley {
    private static volatile XVolley instance = null;
    private RequestQueue mRequsetQueue;
    private String mBaseUrl;

    private XVolley(Context context) {
        if (context == null) {
            Exceptions.illegalArgument("context can't be null,have you initialized the XVolley " +
                    "first?");
        }
        mRequsetQueue = Volley.newRequestQueue(context);
    }

    /**
     * The singleton pattern to ensure global only a request queue
     */
    public static XVolley create(Context context) {
        if (instance == null) {
            synchronized (XVolley.class) {
                if (instance == null) {
                    instance = new XVolley(context);
                }
            }
        }
        return instance;
    }

    public static XVolley getInstance() {
        return create(null);
    }

    /**
     * set the base url in request
     */
    public XVolley baseUrl(String url) {
        this.mBaseUrl = url;
        return this;
    }

    /**
     * do get request
     */
    public GetBuilder doGet() {
        return new GetBuilder();
    }

    /**
     * do post request
     */
    public PostFormBuilder doPost() {
        return new PostFormBuilder();
    }

    /**
     * post with string body
     */
    public PostStringBuilder doPostString() {
        return new PostStringBuilder();
    }

    /**
     * post file
     */
    public PostFileBuilder doPostFile() {
        return new PostFileBuilder();
    }

    /**
     * add a request into the queue
     */
    public XVolley add(Request request) {
        mRequsetQueue.add(request);
        return this;
    }

}
