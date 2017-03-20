package com.libin.request_business;

import android.app.Application;
import android.content.Context;

/**
 * Created by doudou on 2017/3/9.
 */

public class RxApplication extends Application {
    private static RxApplication mInstance ;

    /**
     *
     * @return
     */
    public static Context getContext(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
