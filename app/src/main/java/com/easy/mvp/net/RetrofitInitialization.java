package com.easy.mvp.net;

import com.easy.mvp.BuildConfig;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * author: tianhonglong
 * new date: 2021/8/19
 * version: v 1.0
 */
public class RetrofitInitialization {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public LoggingInterceptor interceptor = new LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .log(Platform.INFO)
            .request("request")
            .response("response")
            .build();

    private RetrofitInitialization() {
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addCallAdapterFactory(DirectCallAdapterFactory.create())
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }

    public static class InnerRetrofitInitialization {
        private static RetrofitInitialization retrofitInitialization =  new RetrofitInitialization();
    }

    public static Retrofit get() {
        return InnerRetrofitInitialization.retrofitInitialization.retrofit;
    }
}
