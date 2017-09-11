package com.study.xuan.xvolleyutil.utils;

import android.util.Log;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class LogUtil {
    public static final String BASE_LOG = "XVolley-";

    public static void log(String type, String log) {
        Log.i(BASE_LOG + type, log);
    }

    public static void error(String type, String log) {
        Log.e(BASE_LOG + type, log);
    }
}
