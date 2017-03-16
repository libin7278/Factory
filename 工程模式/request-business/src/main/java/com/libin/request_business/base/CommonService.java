package com.libin.request_business.base;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by doudou on 2017/3/9.
 */

public interface CommonService {
    String BASE_URL = "http://www.example.com/"; //联网使用，这个不重要，可以随便写，但是必须有

    /**
     * 用来访问完整的url
     * @param url
     * @return
     */
    @GET
    Observable<ResponseBody> loadString(@Url String url);

    /**
     * 用来下载文件
     * @param url
     * @return
     */
    @GET
    @Streaming
    Observable<ResponseBody> download(@Url String url);
}
