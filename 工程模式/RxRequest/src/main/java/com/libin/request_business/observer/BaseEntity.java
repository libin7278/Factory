package com.libin.request_business.observer;

/**
 * Created by doudou on 2017/3/10.
 * 封装 处理服务器返回数据，管理 RxJava生命周期
 * 对返回结果进行处理 改为相应数据结构
 */

public class BaseEntity<T> {
    public boolean error;

    public T results;

}
