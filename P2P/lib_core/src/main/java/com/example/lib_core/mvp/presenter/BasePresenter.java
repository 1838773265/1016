package com.example.lib_core.mvp.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M extends IModel,V extends IView> implements IPresenter {

    @Inject
    protected M model;
    @Inject
    protected V view;



    protected List<Disposable> disposables = new ArrayList<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    public void OnDestory() {
        for (Disposable disposable: disposables){
            if (!disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
