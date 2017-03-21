package com.libin.factory.ndk;

import java.util.ArrayList;

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
     * 通过JNI简单进行字符串拼接操作
     * @param s
     * @return
     */
    static public native String addString(String s);

    /**
     * 创建Student信息
     * @return
     */
    public static native Student getStudentInfo();

    /**
     * 更新Student信息
     * @param student
     * @return
     */
    public static native Student updateStudentInfo(Student student);

    /**
     *从Java传递复杂对象集合List<Student>到Native,解析后赋值到另一个复杂对象集合List<People>并返回
     * @param students
     * @return
     */
    public static native ArrayList<People> getPeopleInfo(ArrayList<Student> students);

}
