package com.libin.factory.handler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.libin.factory.activity.MainActivity;
import com.libin.factory.MainApplication;
import com.libin.factory.Utils.CommonUtils;
import com.libin.factory.Utils.PreferencesUtility;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by doudou on 2017/3/7.
 */

public class UnceHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";
    MainApplication application;

    public UnceHandler(MainApplication application) {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);

            Log.e("TAG","系统处理错误");
        } else {
            //对于未捕获的异常 在这里手动处理 清除数据 然后重启APP
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }
            File file = new File(application.getCacheDir().getAbsolutePath() + "playlist");
            if (file.exists()) {
                file.delete();
            }
            Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
            if(System.currentTimeMillis() - PreferencesUtility.getInstance(application.getApplicationContext()).lastExit() < 10000){
                android.os.Process.killProcess(android.os.Process.myPid());
                return;
            }
            PendingIntent restartIntent = PendingIntent.getActivity(
                    application.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //退出程序
            AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 300,
                    restartIntent); // 1秒钟后重启应用
            PreferencesUtility.getInstance(MainApplication.getContext()).setExitTime();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        File file = new File(application.getCacheDir().getAbsolutePath() + "/err/");
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            PrintWriter writer = new PrintWriter(application.getCacheDir().getAbsolutePath() + "/err/" + System.currentTimeMillis() + ".log");
            ex.printStackTrace(writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 2017/3/7 发送错误日志
                Log.e("TAG","errlog from " + CommonUtils.getUniquePsuedoID()+"\n"+CommonUtils.getDeviceInfo() +"\n"+ Log.getStackTraceString(ex));
            }
        }).start();


        return true;
    }
}
