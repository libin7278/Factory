/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_libin_factory_ndk_NDK */

#ifndef _Included_com_libin_factory_ndk_NDK
#define _Included_com_libin_factory_ndk_NDK


#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_getStringFromNative
  (JNIEnv *, jclass);

JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_getString_1From_1c
  (JNIEnv *, jclass);

JNIEXPORT jint JNICALL Java_com_libin_factory_ndk_NDK_getint
        (JNIEnv *env, jclass type);

JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_printStr
        (JNIEnv *env, jclass type);

JNIEXPORT jint JNICALL Java_com_libin_factory_ndk_NDK_addInt
        (JNIEnv *env, jclass type, jint a, jint b);

JNIEXPORT jstring JNICALL
Java_com_libin_factory_ndk_NDK_addString
        (JNIEnv *env, jclass type, jstring s_);

JNIEXPORT jobject JNICALL
Java_com_libin_factory_ndk_NDK_getStudentInfo(JNIEnv *env, jclass type);

JNIEXPORT jobject JNICALL
Java_com_libin_factory_ndk_NDK_updateStudentInfo(JNIEnv *env, jclass type, jobject student);

JNIEXPORT jobject JNICALL
        Java_com_libin_factory_ndk_NDK_getPeopleInfo();

#ifdef __cplusplus
}
#endif
#endif
