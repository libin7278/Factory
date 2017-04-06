package com.libin.factory;

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
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

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
     * @return DaoMaster
     */
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(getContext(),"shopDbb",null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * @return DaoSession
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

//    /**
//     *  配置数据库
//     */
//    private void setupDatabase() {
//        //创建数据库shop.db"
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "shop.db", null);
//        //获取可写数据库
//        SQLiteDatabase db = helper.getWritableDatabase();
//        //获取数据库对象
//        DaoMaster daoMaster = new DaoMaster(db);
//        //获取Dao对象管理者
//        daoSession = daoMaster.newSession();
//    }
//
//    public static DaoSession getDaoInstant() {
//        return daoSession;
//    }

}
