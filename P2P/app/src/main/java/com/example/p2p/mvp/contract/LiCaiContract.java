package com.example.p2p.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.view.IView;
import com.example.p2p.entity.QuanBuEntity;

import io.reactivex.Observable;

public interface LiCaiContract {

    interface LiCaiView extends IView{

        void showquanbu(QuanBuEntity quanBuEntity);

    }

    interface LiCaiModel extends IModel{

        Observable<QuanBuEntity> requestquanbu();

    }

}
