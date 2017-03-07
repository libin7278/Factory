package com.libin.factory;

import android.app.Application;
import android.content.Context;

import com.libin.factory.handler.UnceHandler;

/**
 * Created by doudou on 2017/3/7.
 */

public class MainApplication extends Application {

    private static MainApplication mainApplication ;

    /**
     *
     * @return
     */
    public static Context getContext(){
        return mainApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mainApplication = this ;

        initCatchException();
    }

    //捕获全局Exception 重启界面
    public void initCatchException() {
        //设置该CrashHandler为程序的默认处理器
        UnceHandler catchExcep = new UnceHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }
}
