//
// Created by 豆豆 on 2017/3/21.
//

#ifndef UTIL_H
#define UTIL_H

#include "jni.h"
#include "signal.h"
/**
 * String转换为Char
 * @param env
 * @param jstr
 * @return
 */
char* jstringToChar(JNIEnv *env, jstring jstr);

jstring charToString(JNIEnv *env, const char *chr);

#endif //工程模式_UTIL_H
