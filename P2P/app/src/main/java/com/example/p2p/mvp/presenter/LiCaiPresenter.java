package com.example.p2p.mvp.presenter;

import com.example.lib_core.mvp.presenter.BasePresenter;
import com.example.p2p.entity.QuanBuEntity;
import com.example.p2p.mvp.contract.LiCaiContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LiCaiPresenter extends BasePresenter<LiCaiContract.LiCaiModel,LiCaiContract.LiCaiView> {

    @Inject
    public LiCaiPresenter() {
    }

    public void getquanbu(){
        model.requestquanbu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QuanBuEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QuanBuEntity quanBuEntity) {
                        view.showquanbu(quanBuEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
