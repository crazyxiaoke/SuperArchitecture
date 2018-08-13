package com.app.lib_common.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 　　┏┓　　　　┏┓
 * 　┏┛┻━━━━┛┻┓
 * 　┃　　　　　　　　┃
 * 　┃　　　━　　　　┃
 * 　┃　┳┛　┗┳　　┃
 * 　┃　　　　　　　　┃
 * 　┃　　　┻　　　　┃
 * 　┃　　　　　　　　┃
 * 　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃　　　神兽保佑
 * 　　　　┃　　　┃　　　代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * <p>
 * Created by zxk on 18-8-8.
 */
public class RetrofitFactory {

    private static final int DEFAULT_TIMEOUT = 1; //超时设置1分钟
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private HttpLoggingInterceptor mHttpLoggingInterceptor;


    private RetrofitFactory(String baseUrl, long time, TimeUnit timeUnit) {
        mHttpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(time, timeUnit)
                .retryOnConnectionFailure(true)
                .addInterceptor(mHttpLoggingInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitFactory getInstance(String baseUrl) {
        return new RetrofitFactory(baseUrl, DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static RetrofitFactory getInstance(String baseUrl, long time, TimeUnit timeUnit) {
        return new RetrofitFactory(baseUrl, time, timeUnit);
    }

    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }

}
