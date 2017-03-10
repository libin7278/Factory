package com.libin.request_business.observer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by doudou on 2017/3/10.
 */

public abstract class BaseObserver<T>  implements Observer<BaseEntity<T>> {
    private Context mContext;
    private ProgressDialog mDialog;
    private Disposable mDisposable;
    private final int SUCCESS_CODE = 0;

    public BaseObserver() {
    }

    public BaseObserver(Context mContext) {
        this.mContext = mContext;
    }

    public BaseObserver(Context context, ProgressDialog dialog) {
        mContext = context;
        mDialog = dialog;

        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mDisposable.dispose();
            }
        });
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(BaseEntity<T> tBaseEntity) {
        if (!tBaseEntity.error)
            _onSuccess(tBaseEntity.results);
        else
            _onError(new Throwable("error=true"));
    }

    @Override
    public void onError(Throwable e) {
        Log.d("gesanri", "error:" + e.toString());

        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }

        Toast.makeText(mContext, "网络异常，请稍后再试", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onComplete() {
        Log.d("gesanri", "onComplete");

        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

    public abstract void _onSuccess(T t);

    public abstract void _onError(Throwable e);

}
