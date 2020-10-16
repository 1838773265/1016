package com.example.lib_core.mvp.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lib_core.mvp.presenter.IPresenter;
import com.example.lib_core.mvp.view.view.IView;
import com.example.lib_core.widget.LoadDialog;

import javax.inject.Inject;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity, IView {

    @Inject
    public P presenter;

    public LoadDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindlayout());
        loadDialog = new LoadDialog(this);
        initview();

        initInject();

        initdata();

    }

    @Override
    public void showDialog() {
        loadDialog.show();
    }

    @Override
    public void hideDialog() {
        loadDialog.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
