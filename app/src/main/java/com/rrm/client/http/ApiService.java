package com.rrm.client.http;

import com.rrm.client.mvp.model.WeatherModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public interface ApiService {
    /**
     * URL
     */
    String API_SERVICE_URL = "http://www.weather.com.cn/";

    /**
     * 加载天气
     * @param cityId
     * @return
     */
    @GET("adat/sk/{cityId}.html")
    Observable<WeatherModel> loadWeather(@Path("cityId") String cityId);



}
