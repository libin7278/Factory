//
// Created by 豆豆 on 2017/3/21.
//

#include <string.h>
#include "util.h"
/**
 * String转换为Char
 * @param env
 * @param jstr
 * @return
 */
char* jstringToChar(JNIEnv* env, jstring jstr) {
    char* rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("utf-8");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr= (jbyteArray)env->CallObjectMethod(jstr, mid, strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);

    if (alen > 0)
    {
        rtn = (char*)malloc(alen + 1);
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }

    env->ReleaseByteArrayElements(barr, ba, 0);
    return rtn;
}

/**
 * Char转换成String
 * @param env
 * @param chr
 * @return
 */
jstring charToString(JNIEnv *env, const char *chr){
    //LOGI("charToString: %s\n", chr);
    jclass strClass = env->FindClass("java/lang/String");
    jmethodID strConstruct = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
    jbyteArray bytes = env->NewByteArray(strlen(chr));
    env->SetByteArrayRegion(bytes, 0, strlen(chr), (jbyte*)chr);
    jstring encoding = env->NewStringUTF("utf-8");

    return (jstring)env->NewObject(strClass, strConstruct, bytes, encoding);
}