package com.libin.factory.ndk;

/**
 * Created by doudou on 2017/3/17.
 */

public class NDK  {
    // 1.加载实现了native函数的动态库，只需要写动态库的名字
    static {
        System.loadLibrary("MyJni");
    }

    // 2.声明这是一个native函数，由本地代码实现
    public static native String getStringFromNative();
    public static native String getString_From_c();
    public static native int getint();

}
