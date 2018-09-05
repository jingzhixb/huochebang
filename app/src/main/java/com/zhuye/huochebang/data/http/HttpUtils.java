package com.zhuye.huochebang.data.http;

import com.zhuye.huochebang.contants.Contans;
import com.zhuye.huochebang.data.http.parse.MyGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class HttpUtils {

    public static OkHttpClient getOkhttp(){

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build();
        return okHttpClient;
    }

    public static Retrofit getRetrofit(OkHttpClient okHttpClient){
        Retrofit retrofit = new  Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Contans.BASE)
                .build();
        return retrofit;
    }
}
