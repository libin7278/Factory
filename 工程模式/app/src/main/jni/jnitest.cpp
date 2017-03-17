//
// Created by libin on 2017/3/17.
//

#include "com_libin_factory_ndk_NDK.h"
#include "jni.h"

/*
 * Class:     com_libin_factory_ndk_NDK
 * Method:    getStringFromNative
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_getStringFromNative
        (JNIEnv * env, jclass){
    return (*env).NewStringUTF("ndk");
}

/*
 * Class:     com_libin_factory_ndk_NDK
 * Method:    getString_From_c
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_getString_1From_1c
        (JNIEnv * env, jclass){

    return  env->NewStringUTF("NDK 来自于C文件");
}

JNIEXPORT jint JNICALL
Java_com_libin_factory_ndk_NDK_getint(JNIEnv *env, jclass type){

    return 0;
}


