package com.example.p2p.mvp.contract;

import com.example.lib_core.mvp.model.IModel;
import com.example.lib_core.mvp.view.view.IView;
import com.example.p2p.entity.ShouYeEntity;
import com.example.p2p.entity.UserEntity;

import io.reactivex.Observable;

public interface UserContract {

    interface UserView extends IView{

        void showLogin(UserEntity user);

        void showRegister(UserEntity user);

        void showShouye(ShouYeEntity shouYe);

    }

    interface UserModel extends IModel{

        Observable<UserEntity> requestlogin(String name, String pass);
        Observable<UserEntity> requestregister(String name,String pass);

        Observable<ShouYeEntity> requestshouye();

    }

}
