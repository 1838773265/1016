package com.example.p2p.mvp.model;

import com.example.p2p.entity.QuanBuEntity;
import com.example.p2p.entity.ShouYeEntity;
import com.example.p2p.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("login")
    Observable<UserEntity> login(@Field("name") String name,@Field("password") String pass);

    @FormUrlEncoded
    @POST("register")
    Observable<UserEntity> register(@Field("name") String name,@Field("password") String pass);

    @GET("atguigu/json/P2PInvest/index.json")
    Observable<ShouYeEntity> shouye();

    @GET("atguigu/json/P2PInvest/product.json")
    Observable<QuanBuEntity> quanbu();

}
