//
// Created by libin on 2017/3/17.
//

#include "com_libin_factory_ndk_NDK.h"
#include "jni.h"
#include "jniutils/util.h"
#include <string.h>

// 引入log头文件
#include <android/log.h>
// log标签
#define TAG "jnitest"
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)
// 定义WARN类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__)
// 定义FATAL类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__)


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
        (JNIEnv *env, jclass type) {

    LOGD("通过JNI简单输出字符串");
    LOGI("通过JNI简单输出字符串");
    LOGF("通过JNI简单输出字符串");
    LOGW("通过JNI简单输出字符串");
    LOGE("通过JNI简单输出字符串");
    return env->NewStringUTF("通过JNI简单输出字符串");

}

/**
* 整形加法操作
* @param a
* @param b
* @return
*/
JNIEXPORT jint JNICALL Java_com_libin_factory_ndk_NDK_addInt
        (JNIEnv *env, jclass type, jint a, jint b) {

    return a + b;
}


/**
 * 通过JNI简单进行字符串拼接操作
 * @param s
 * @return
 */
JNIEXPORT jstring JNICALL Java_com_libin_factory_ndk_NDK_addString
        (JNIEnv *env, jclass type, jstring s_) {

    char *text = jstringToChar(env, s_);

    char temp[20] = "    /  I am from c";

    strcat(text, temp);

    return charToString(env, text);

}


/**
 * C代码调NDK类中的addInt(int a, int b)方法
 */
JNIEXPORT void JNICALL
Java_com_libin_factory_ndk_NDK_ccallBackAddInt(JNIEnv *env, jclass type) {
    //得到字节码
    jclass jclazz = env->FindClass("com/libin/factory/ndk/Test"); //com.libin.factory.ndk.TestK 点换成下滑线
    //得到方法
    jmethodID jmethodIDS = env->GetMethodID(jclazz,"addInt","(II)I");
    //实例化该类
    jobject jobj = env->AllocObject(jclazz);
    //调用方法
    jint value =env->CallIntMethod(jobj,jmethodIDS,99,1);

    LOGE("value = %d\n" ,value);
}

/**
* C代码调NDK类中的setStrign(String s)方法
*/
JNIEXPORT void JNICALL
Java_com_libin_factory_ndk_NDK_ccallBackGetString(JNIEnv *env, jclass type) {
    //得到字节码
    jclass jclazz = env->FindClass("com/libin/factory/ndk/Test"); //com.libin.factory.ndk.TestK 点换成下滑线
    //得到方法
    jmethodID jmethodIDS = env->GetMethodID(jclazz,"setStrings","(Ljava/lang/String;)V");
    //实例化该类
    jobject jobj = env->AllocObject(jclazz);
    //调用方法
    jstring str = env->NewStringUTF("hello 我是C,我在调用java方法");

    env->CallVoidMethod(jobj,jmethodIDS,str);

}

JNIEXPORT void JNICALL
Java_com_libin_factory_ndk_NDK_ccallBackAddIntS(JNIEnv *env, jclass type) {
    //得到字节码
    jclass jclazz = env->FindClass("com/libin/factory/ndk/Test"); //com.libin.factory.ndk.TestK 点换成下滑线
    //得到方法
    jmethodID jmethodIDS = env->GetStaticMethodID(jclazz,"addIntS","(II)I");
//    //实例化该类
//    jobject jobj = env->AllocObject(jclazz);
    //调用方法
    jint value =env->CallStaticIntMethod(jclazz,jmethodIDS,99,1);
    LOGE("value = %d\n" ,value);
}

JNIEXPORT void JNICALL
Java_com_libin_factory_ndk_NDK_ccallBackGetStringS(JNIEnv *env, jclass type) {
    //得到字节码
    jclass jclazz = env->FindClass("com/libin/factory/ndk/Test"); //com.libin.factory.ndk.TestK 点换成下滑线
    //得到方法
    jmethodID jmethodIDS = env->GetStaticMethodID(jclazz,"setStringsS","(Ljava/lang/String;)V");
//    //实例化该类
//    jobject jobj = env->AllocObject(jclazz);
    //调用方法
    jstring str = env->NewStringUTF("hello 我是C,我在调用java的静态方法方法");
    env->CallStaticVoidMethod(jclazz,jmethodIDS,str);
}

/**
 * 创建Student信息
 */
JNIEXPORT jobject JNICALL
Java_com_libin_factory_ndk_NDK_getStudentInfo(JNIEnv *env, jclass type) {
    //com.libin.factory.ndk.Student 要把 . 替换成 ／
    //关于包描述符，这儿是 com/libin/factory/ndk/Student 要用全类名
    jclass stucls = env->FindClass("com/libin/factory/ndk/Student"); //或得Student类引用

    //获得该类型的构造函数  函数名为 <init> 返回类型必须为 void 即 V
    jmethodID constrocMID = env->GetMethodID(stucls, "<init>", "(Ljava/lang/String;I)V");

    jstring str = env->NewStringUTF("Student named Aly");

    jobject stu_ojb = env->NewObject(stucls, constrocMID, str, 25);  //构造一个对象，调用该类的构造函数，并且传递参数

    return stu_ojb;

}

/**
 * 跟新Student信息
 */
JNIEXPORT jobject JNICALL
Java_com_libin_factory_ndk_NDK_updateStudentInfo
        (JNIEnv *env, jclass type, jobject obj) {

    jclass clazz;
    jfieldID fid;
    jstring j_str;
    jstring j_newStr;

    clazz = env->GetObjectClass(obj);
    if (clazz == NULL) {
        return NULL;
    }
    fid = env->GetFieldID(clazz, "name", "Ljava/lang/String;");
    if (clazz == NULL) {
        return NULL;
    }
    j_str = (jstring) (env)->GetObjectField(obj, fid);
    j_newStr = (env)->NewStringUTF("This is New Name hahah");
    (env)->SetObjectField(obj, fid, j_newStr);
    // 删除局部引用
    (env)->DeleteLocalRef(clazz);
    (env)->DeleteLocalRef(j_str);
    (env)->DeleteLocalRef(j_newStr);
    return obj;

}

/**
* 让C代码给你每个元素加上10
* @param intArray
* @return
*/
JNIEXPORT jintArray JNICALL
Java_com_libin_factory_ndk_NDK_increaseArrayEles(JNIEnv *env, jclass type, jintArray intArray_) {
    //得到数组的长度
    jsize size = env->GetArrayLength(intArray_);
    //得到数组元素
    jint *array = env->GetIntArrayElements(intArray_, JNI_FALSE); //JNI_FALSE同一份 不开辟新空间
    //遍历数组给每个元素加上10
    for (int i = 0; i < size; i++) {
        *(array + i) += 10;
        LOGE("array %d = %d", i, *(array + i));

    }
    env->ReleaseIntArrayElements(intArray_, array, 0);
    //返回结果
    return intArray_;

}

/**
* 应用：检车密码是否正确，正确返回200，错误返回400
* @param pwd
* @return
*/
JNIEXPORT jint JNICALL
Java_com_libin_factory_ndk_NDK_checkPwd(JNIEnv *env, jclass type, jstring pwd_) {
    //假设服务器密码123456
    const char *otigin = "123456"; //char *背后的含义是：给我个字符串，我要修改它。 const char * 这个类型说背后的含义是：给我个字符串，我只要读取它。
    const char *fromUser = jstringToChar(env, pwd_);
    //函数比较字符串是否相同
    int code = strcmp(otigin, fromUser);
    if (code == 0) {
        return 200;
    } else {
        return 400;
    }
}

/**
 * 从Java传递复杂对象集合List<Student>到Native,解析后赋值到另一个复杂对象集合List<People>并返回
 */
JNIEXPORT jobject JNICALL
Java_com_libin_factory_ndk_NDK_getPeopleInfo
        (JNIEnv *env, jclass type, jobject students) {

    // TODO

}
