package com.libin.factory.reflex_factory;

import android.util.Log;

/**
 * Created by doudou on 2017/3/6.
 */

public class JiLIBoYue extends JiLiCar {
    @Override
    public void driver() {
        Log.e("TAG" , "吉利博越启动啦");
    }

    @Override
    public void selgVavigator() {
        Log.e("TAG" , "吉利博越 >>> 开启自动巡航啦");
    }
}
