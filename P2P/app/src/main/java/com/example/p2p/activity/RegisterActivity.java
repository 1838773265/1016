package com.example.p2p.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lib_core.mvp.view.activity.BaseActivity;
import com.example.p2p.R;
import com.example.p2p.dager.user.compent.DaggerUserCompont;
import com.example.p2p.dager.user.modules.UserModules;
import com.example.p2p.entity.ShouYeEntity;
import com.example.p2p.entity.UserEntity;
import com.example.p2p.mvp.contract.UserContract;
import com.example.p2p.mvp.presenter.UserPresenter;

public class RegisterActivity extends BaseActivity<UserPresenter> implements UserContract.UserView {
    private EditText etLoginNumber;
    private EditText etLoginPwd;
    private Button btnLogin;
    private Button btnRegister;


    @Override
    public int bindlayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initInject() {
        DaggerUserCompont.builder().userModules(new UserModules(this)).build().injectRegisterActivity(this);
    }


    @Override
    public void initview() {
        etLoginNumber = findViewById(R.id.et_login_number);
        etLoginPwd = findViewById(R.id.et_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    public void initdata() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etLoginNumber.getText().toString().trim();
                String pass = etLoginPwd.getText().toString().trim();

                presenter.getregister(name,pass);

            }
        });
    }

    @Override
    public void showLogin(UserEntity user) {

    }

    @Override
    public void showRegister(UserEntity user) {
        String message = user.getMessage();

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
    }

    @Override
    public void showShouye(ShouYeEntity shouYe) {

    }
}
