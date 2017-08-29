package com.study.xuan.xvolleyutil;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.study.xuan.xvolleyutil.build.GetBuilder;
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
    public static final int METHOD_GET_STRING = 0;
    public static final int METHOD_GET_GSON = 1;
    public static final int METHOD_POST_STRING = 2;
    public static final int METHOD_POST_GSON = 3;
    public static final int METHOD_STRING_POST = 4;
    public static final int METHOD_STRING_POST_GSON = 5;
    private static volatile XVolley instance = null;
    private RequestQueue mRequsetQueue;
    private String mBaseUrl;
    private Class mClassz = String.class;
    private boolean isGson = false;
    private int mMethodType;

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
    public RequestBuilder doGet() {
        mMethodType = METHOD_GET_STRING;
        if (isGson) {
            mMethodType = METHOD_GET_GSON;
        }
        return new GetBuilder(mMethodType,mClassz);
    }

    /**
     * do post request
     */
    public RequestBuilder doPost() {
        mMethodType = METHOD_POST_STRING;
        if (isGson) {
            mMethodType = METHOD_POST_GSON;
        }
        return new PostFormBuilder(mMethodType,mClassz);
    }

    /**
     * post with string body
     */
    public RequestBuilder doPostString() {
        mMethodType = METHOD_STRING_POST;
        if (isGson) {
            mMethodType = METHOD_STRING_POST_GSON;
        }
        return new PostStringBuilder(mMethodType, mClassz);
    }

    /**
     * add a request into the queue
     */
    public XVolley add(Request request) {
        mRequsetQueue.add(request);
        return this;
    }

    /**
     * go to parse by Gson
     */
    public XVolley goGson(Class c) {
        mClassz = c;
        isGson = true;
        return this;
    }
}
