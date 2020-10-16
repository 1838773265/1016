package com.example.p2p.mvp.model;

import com.example.lib_core.mvp.model.BaseModel;
import com.example.lib_core.network.NetWorkManager;
import com.example.p2p.entity.QuanBuEntity;
import com.example.p2p.mvp.contract.LiCaiContract;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LiCaiModel extends BaseModel implements LiCaiContract.LiCaiModel {

    @Inject
    public LiCaiModel() {
    }

    @Override
    public Observable<QuanBuEntity> requestquanbu() {
        return NetWorkManager.getInstance().getRetrofit().create(ApiService.class).quanbu();
    }
}
