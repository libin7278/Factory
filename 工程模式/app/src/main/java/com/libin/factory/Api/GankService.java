package com.libin.factory.Api;

import com.libin.factory.Entity.GanHuoDataBean;
import com.libin.factory.Entity.GanHuoRecentlyBean;
import com.libin.factory.Entity.GanHuoTitleBean;
import com.libin.factory.Entity.SearchResult;
import com.libin.request_business.subscriber.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by _SOLID
 * Date:2016/8/3
 * Time:9:28
 */
public interface GankService {

    String BASE_URL = "http://www.gank.io/api/";

    /**
     * 获取发布干货的日期
     *
     * @return
     */
    @GET("day/history")
    Observable<HttpResult<List<String>>> getRecentlyDate();

    /**
     * 获取最近5日干货网站数据
     *
     * @return
     */
    @GET("history/content/5/1")
    Observable<HttpResult<List<GanHuoTitleBean>>> getTitles();

    /***
     * 根据类别查询干货
     *
     * @param category
     * @param pageIndex
     * @return
     */
    @GET("data/{category}/20/{pageIndex}")
    Observable<HttpResult<List<GanHuoDataBean>>> getGanHuo(@Path("category") String category
            , @Path("pageIndex") int pageIndex);

    /**
     * 获取某天的干货
     *
     * @param date
     * @return
     */
    @GET("day/{date}")
    Observable<HttpResult<GanHuoRecentlyBean>> getRecentlyGanHuo(@Path("date") String date);

    /**
     * 搜索
     *
     * @param keyword
     * @param pageIndex
     * @return
     */
    @GET("search/query/{keyword}/category/all/count/20/page/{pageIndex}")
    Observable<HttpResult<List<SearchResult>>> search(@Path("keyword") String keyword, @Path("pageIndex") int pageIndex);
}
