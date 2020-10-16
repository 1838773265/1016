package com.example.p2p.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.lang.UCharacter;
import android.os.Bundle;
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

public class LoginActivity extends BaseActivity<UserPresenter> implements UserContract.UserView {

    private EditText etLoginNumber;
    private EditText etLoginPwd;
    private Button btnLogin;
    private Button btnRegister;





    @Override
    public int bindlayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initInject() {
        DaggerUserCompont.builder().userModules(new UserModules(this)).build().injectLoginActivity(this);
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etLoginNumber.getText().toString().trim();
                String pass = etLoginPwd.getText().toString().trim();

                presenter.getlogin(name,pass);

            }
        });

    }


    @Override
    public void showLogin(UserEntity user) {
        String message = user.getMessage();


        SharedPreferences sp = getSharedPreferences("1802a", Context.MODE_PRIVATE);

        sp.edit().putString("name",""+etLoginNumber.getText().toString().trim()).commit();

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(LoginActivity.this,MainActivity.class));

    }

    @Override
    public void showRegister(UserEntity user) {

    }

    @Override
    public void showShouye(ShouYeEntity shouYe) {

    }
}
