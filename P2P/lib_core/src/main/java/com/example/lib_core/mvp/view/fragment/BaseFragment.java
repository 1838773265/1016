package com.example.lib_core.mvp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lib_core.mvp.presenter.IPresenter;
import com.example.lib_core.mvp.view.view.IView;

import javax.inject.Inject;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment, IView {

    @Inject
    public P presenter;
    private View baseview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return baseview = inflater.inflate(bindlayout(),container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();

        initInject();

        initdata();

    }

    @Override
    public <T extends View> T findviewbyid(int id) {
        return baseview.findViewById(id);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showMsg(String msg) {

    }
}
