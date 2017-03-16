package com.libin.factory;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.libin.factory.Api.GankService;
import com.libin.factory.Entity.GanHuoDataBean;
import com.libin.factory.Entity.GanHuoTitleBean;
import com.libin.request_business.base.CommonService;
import com.libin.request_business.base.RxUtils;
import com.libin.request_business.factory.RxRequest;
import com.libin.request_business.subscriber.HttpResult;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ExampleInstrumentedTest {

    String TAG = "AndroidTest";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.libin.factory", appContext.getPackageName());
    }

    @Test
    public void loadData() throws Exception {
        RxRequest.getInstance().createService(GankService.class)
                .getGanHuo("福利", 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(RxUtils.<HttpResult<List<GanHuoDataBean>>>defaultSchedulers())
                .subscribe(new Observer<HttpResult<List<GanHuoDataBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe-->" + d.toString());
                    }

                    @Override
                    public void onNext(HttpResult<List<GanHuoDataBean>> listHttpResult) {
                        Log.e(TAG, "onNext-->" + listHttpResult.results.get(0).getUrl());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError" + e.toString());

                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");

                    }
                });
    }

    @Test
    public void testDownLoad() throws Exception {
        String url = "https://codeload.github.com/burgessjp/GanHuoIO/zip/master";
        CommonService downLoadService = RxRequest.getInstance().createService(CommonService.class);
        downLoadService.download(url).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe1-->" + d.toString());

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Log.e(TAG, "onNext1-->" + responseBody.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError1-->" + e.toString());

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete1-->");

            }
        });
    }

    @Test
    public void loadString() throws Exception {
        String url = "http://gank.io/api/data/Android/10/1";

        CommonService downLoadService = RxRequest.getInstance().createService(CommonService.class);
        downLoadService.loadString(url).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe2-->" + d.toString());

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                Log.e(TAG, "onNext2-->" + responseBody.toString());

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError2-->" + e.toString());

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete2-->");
            }
        });
    }
    //http://gank.io/api/data/Android/10/1


    @Test
    public void testGetTitle2() throws Exception {

        RxRequest.getInstance().createService(GankService.class)
                .getTitles().compose(RxUtils.<HttpResult<List<GanHuoTitleBean>>>defaultSchedulers()).subscribe(new Consumer<HttpResult<List<GanHuoTitleBean>>>() {
            @Override
            public void accept(@NonNull HttpResult<List<GanHuoTitleBean>> listHttpResult) throws Exception {
                Log.e(TAG,listHttpResult.results.get(1).getTitle());
            }
        });

    }

    @Test
    public void testGetTitle() throws Exception {
        RxRequest.getInstance().
                createService(GankService.class).getTitles()
                .compose(RxUtils.<HttpResult<List<GanHuoTitleBean>>>defaultSchedulers())
                .subscribe(new Observer<HttpResult<List<GanHuoTitleBean>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<List<GanHuoTitleBean>> listHttpResult) {
                Log.e(TAG, "onNextTiTle-->" + listHttpResult.results.get(0).getTitle());

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}

