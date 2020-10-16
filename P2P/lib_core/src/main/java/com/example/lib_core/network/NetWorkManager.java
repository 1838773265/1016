package com.example.lib_core.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkManager {

    private Retrofit retrofit;

    private static NetWorkManager netWorkManager = new NetWorkManager();

    private NetWorkManager(){}

    public static NetWorkManager getInstance(){
        return netWorkManager;
    }


    public Retrofit getRetrofit(){
        if (retrofit == null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(5*1000, TimeUnit.MILLISECONDS)
                    .readTimeout(5*1000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5*1000, TimeUnit.MILLISECONDS)
                    .build();

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.client(client);
            builder.baseUrl(API.BASEURL);
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            builder.addConverterFactory(GsonConverterFactory.create());
            retrofit = builder.build();



        }

        return retrofit;

    }

}
