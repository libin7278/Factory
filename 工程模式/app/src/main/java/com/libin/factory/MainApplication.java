package com.libin.factory;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.libin.core.CoreApplication;
import com.libin.factory.green_dao.dao.DaoMaster;
import com.libin.factory.green_dao.dao.DaoSession;
import com.libin.factory.handler.UnceHandler;

import java.io.File;

/**
 * Created by doudou on 2017/3/7.
 */

public class MainApplication extends CoreApplication {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        setupDatabase();

        initCatchException();
    }

    //捕获全局Exception 重启界面
    public void initCatchException() {
        //设置该CrashHandler为程序的默认处理器
        UnceHandler catchExcep = new UnceHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
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

    /**
     *  配置数据库
     */
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

}
