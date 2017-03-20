package com.libin.factory.ndk;

import com.libin.factory.entity.Userj;

/**
 * Created by doudou on 2017/3/17.
 */

public class NDK  {
    // 1.加载实现了native函数的动态库，只需要写动态库的名字
    static {
        System.loadLibrary("MyJni");
    }

    // 2.声明这是一个native函数，由本地代码实现 test
    public static native String getStringFromNative();
    public static native String getString_From_c();
    public static native int getint();

    //sample demo

    /**
     *  通过JNI简单输出字符串
     * @return
     */
    static public native String printStr();

    /**
     * 通过JNI简单进行整形加法操作
     * @param a
     * @param b
     * @return
     */
    static public native int addInt(int a, int b);

    /**
     * 通过JNI输入JAVA对象信息
     * @param user
     * @return
     */
    static public native String printUser(Userj user);

    /**
     * 通过JNI创建java对象
     * @param name
     * @param age
     * @param sex
     * @return
     */
    static public native Userj newUser(String name, int age, String sex);

    /**
     * 通过JNI调用无参构造函数创建java对象并且设置相应属性值
     * @param name
     * @param age
     * @param sex
     * @return
     */
    static public native Userj newUserNoArgs(String name, int age, String sex);

}
