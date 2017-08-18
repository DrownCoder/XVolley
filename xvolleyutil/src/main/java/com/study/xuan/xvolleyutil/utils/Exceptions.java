package com.study.xuan.xvolleyutil.utils;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
