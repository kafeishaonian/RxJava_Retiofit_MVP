package com.rrm.client.http;

import com.rrm.client.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/4/6 0006.
 */
public class APIClient {

    public static final boolean DEBUG = true;//日志打印，项目中需要单独设置

    public static Retrofit mRetrofit;

    public static Retrofit retrofit(){
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (DEBUG){
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.API_SERVICE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

}
