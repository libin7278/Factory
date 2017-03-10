package com.libin.request_business;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

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

    @Override
    public File getFilesDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }
}
