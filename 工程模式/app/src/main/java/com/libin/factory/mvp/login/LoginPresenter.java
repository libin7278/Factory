package com.libin.factory.mvp.login;

import android.os.Handler;
import android.os.Message;

import com.libin.factory.Api.GankService;
import com.libin.factory.Entity.GanHuoTitleBean;
import com.libin.factory.activity.MainActivity;
import com.libin.factory.mvp.base.BasePresenterImpl;
import com.libin.request_business.base.RxUtils;
import com.libin.request_business.factory.RxRequest;
import com.libin.request_business.subscriber.HttpResult;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * MVPPlugin
 * ＊ 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter{


    @Override
    public void handleClickLogin() {

    }

    @Override
    public void handleRequest() {
        mView.showLoading();

        RxRequest.getInstance().createService(GankService.class).getTitles()
                .compose(RxUtils.<HttpResult<List<GanHuoTitleBean>>>defaultSchedulers())
                .subscribe(new Observer<HttpResult<List<GanHuoTitleBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<List<GanHuoTitleBean>> listHttpResult) {
                mView.disLoading();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    //静态内部类不持有外部类的引用
    private static final class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity mActivity) {
            this.mActivity = new WeakReference<MainActivity>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
