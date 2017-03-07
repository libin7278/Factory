package com.libin.factory.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by doudou on 2017/3/7.
 * 主要用于处理延迟任务 避免内存泄漏
 */

public class HandlerUtil extends Handler{
    private static HandlerUtil instance = null;
    WeakReference<Context> mActivityReference;

    HandlerUtil(Context context) {
        mActivityReference = new WeakReference<>(context);
    }

    public static HandlerUtil getInstance(Context context) {
        if (instance == null) {
            //获取Application的context 避免内存泄漏
            instance = new HandlerUtil(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        if(msg.what == 1){
            Log.e("TAG", (String) msg.obj);
        }
    }

}
