//package com.libin.request_business.subscriber;
//
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//
//import io.reactivex.Observable;
//
///**
// * Created by doudou on 2017/3/9.
// * 封装异常处理
// */
//
//public abstract class HttpResultSubscriber<T> extends Observable<HttpResult<T>> implements Subscriber{
//    private static final String TAG = "HttpResultSubscriber";
//
//    private static final String SOCKETTIMEOUTEXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
//    private static final String CONNECTEXCEPTION = "网络连接异常，请检查您的网络状态";
//    private static final String UNKNOWNHOSTEXCEPTION = "网络异常，请检查您的网络状态";
//
//
//        @Override
//    public void onSubscribe(Subscription s) {
//
//    }
//
//    @Override
//    public void onNext(HttpResult<T> tHttpResult) {
//        if (!tHttpResult.error)
//            _onSuccess(tHttpResult.results);
//        else
//            _onError(new Throwable("error=true"));
//    }
//
//    @Override
//    public void onError(Throwable t) {
//        if (t != null) {
//            t.printStackTrace();
//            if (t.getMessage() == null) {
//                _onError(new Throwable(t.toString()));
//            } else {
//                _onError(new Throwable(t.getMessage()));
//            }
//        } else {
//            _onError(new Exception("null message"));
//        }
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
//
//    public abstract void _onSuccess(T t);
//
//    public abstract void _onError(Throwable e);
//}
