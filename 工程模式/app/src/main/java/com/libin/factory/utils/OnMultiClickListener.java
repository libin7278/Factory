package com.libin.factory.utils;

import android.view.View;

import java.util.Calendar;

/**
 * Created by doudou on 2017/4/6.
 */

public class NoDoubleClickListener implements View.OnClickListener{

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public abstract void onMultiClick(View v);
}
