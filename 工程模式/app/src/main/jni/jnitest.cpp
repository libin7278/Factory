//
// Created by libin on 2017/3/17.
//

#include "com_libin_factory_ndk_NDK.h"
#include "jni.h"

// 引入log头文件
#include <android/log.h>
// log标签
#define TAG "jnitest"
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,VA_ARGS)
// 定义debug信息
#define LOGI(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, VA_ARGS)
// 定义error信息
#define LOGI(...) __android_log_print(ANDROID_LOG_ERROR,TAG,VA_ARGS)


JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_getStringFromNative(JNIEnv *env, jclass) {
    return (*env).NewStringUTF("ndk");
}

JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_getString_1From_1c(JNIEnv *env, jclass) {

    return env->NewStringUTF("NDK 来自于C文件");
}

JNIEXPORT jint JNICALL Java_com_libin_factory_ndk_NDK_getint(JNIEnv *env, jclass type) {

    return 0;
}

JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_printStr
        (JNIEnv *env, jclass type){

    return env->NewStringUTF("通过JNI简单输出字符串");

}

JNIEXPORT jint JNICALL Java_com_libin_factory_ndk_NDK_addInt
        (JNIEnv *env, jclass type, jint a, jint b){

    return a+b;
}

JNIEXPORT jstring JNICALL
Java_com_libin_factory_ndk_NDK_printUser
        (JNIEnv *env, jclass type, jobject user){

    //LOGI("add from jni–打印用户信息–");

}

