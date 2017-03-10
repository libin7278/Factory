package com.libin.request_business.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.libin.request_business.base.OkHttpProvider;

import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by doudou on 2017/3/9.
 */

public class RxRequest {
    private final Gson mGson;
    private OkHttpClient mOkHttpClient;


    private RxRequest() {
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        mOkHttpClient = OkHttpProvider.getDefaultOkHttpClient();

    }

    private static class SingletonHolder {
        private static final RxRequest M_RX_REQUEST = new RxRequest();
    }

    public static RxRequest getInstance() {
        return SingletonHolder.M_RX_REQUEST;
    }

    public static RxRequest getNoCacheInstance() {
        RxRequest factory = SingletonHolder.M_RX_REQUEST;
        factory.mOkHttpClient = OkHttpProvider.getOkHttpClient();
        return factory;
    }

    public <S> S createService(Class<S> serviceClass) {
        String baseUrl = "";
        try {
            Field field1 = serviceClass.getField("BASE_URL");
            baseUrl = (String) field1.get(serviceClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //1.X为RxJavaCallAdapterFactory
                .build();
        return retrofit.create(serviceClass);
    }
}
