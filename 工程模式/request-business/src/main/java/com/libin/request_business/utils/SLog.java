package com.libin.request_business.utils;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by doudou on 2017/3/9.
 */

public class SLog {
    private static boolean mDebug = true;

    private SLog() {
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(Object object, String msg) {
        String tagName = getTagName(object);
        if (mDebug) {
            Log.i(tagName, msg);
        }
    }

    @NonNull
    private static String getTagName(Object object) {
        String tagName = object.getClass().getSimpleName();
        if (TextUtils.isEmpty(tagName)) tagName = "AnonymityClass";
        return tagName;
    }

    /**
     * 打印info级别的log
     *
     * @param msg
     */
    public static void i(String tag,String msg) {
        if (mDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(Object object, String msg) {
        String tagName = getTagName(object);
        if (mDebug) {
            Log.e(tagName, msg);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param msg
     */
    public static void e(String msg) {
        if (mDebug) {
            Log.e("LogError", msg);
        }
    }
}
