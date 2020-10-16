package com.example.p2p.mvp.model;

import com.example.lib_core.mvp.model.BaseModel;
import com.example.lib_core.network.NetWorkManager;
import com.example.p2p.entity.ShouYeEntity;
import com.example.p2p.entity.UserEntity;
import com.example.p2p.mvp.contract.UserContract;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserModel extends BaseModel implements UserContract.UserModel {

    @Inject
    public UserModel() {
    }


    @Override
    public Observable<UserEntity> requestlogin(String name, String pass) {
        return NetWorkManager.getInstance().getRetrofit().create(ApiService.class).login(name,pass);
    }

    @Override
    public Observable<UserEntity> requestregister(String name, String pass) {
        return NetWorkManager.getInstance().getRetrofit().create(ApiService.class).register(name,pass);
    }

    @Override
    public Observable<ShouYeEntity> requestshouye() {
        return NetWorkManager.getInstance().getRetrofit().create(ApiService.class).shouye();
    }
}
