package com.libin.factory.reflex_factory;

import android.util.Log;

/**
 * Created by doudou on 2017/3/6.
 */

public class JILiDiHao extends JiLiCar{
    @Override
    public void driver() {
        Log.e("TAG" , "吉利帝豪启动啦");
    }

    @Override
    public void selgVavigator() {
        Log.e("TAG" , "吉利帝豪 >>> 开启自动巡航啦");
    }
}
