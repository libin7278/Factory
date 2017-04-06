package com.libin.core;

import android.app.Application;
import android.content.Context;

/**
 * Created by doudou on 2017/3/14.
 */

public class CoreApplication extends Application {
    private static CoreApplication coreApplication ;

    public static Context getContext(){
        return coreApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(coreApplication == null){
            coreApplication = this ;
        }


    }
}
