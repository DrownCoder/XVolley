package com.study.xuan.xvolleyutil.callback;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Author : xuan.
 * Data : 2017/8/18.
 * Description :input the description of this file.
 */

public abstract class WeakListener{
    final WeakReference<Activity> activityWeakReference;
    final CallBack callBack;
    public WeakListener(Context activity, CallBack callBack) {
        activityWeakReference = new WeakReference<Activity>((Activity) activity);
        this.callBack = callBack;
    }
}
