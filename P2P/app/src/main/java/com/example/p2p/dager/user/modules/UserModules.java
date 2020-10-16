package com.example.p2p.dager.user.modules;

import com.example.p2p.mvp.contract.UserContract;
import com.example.p2p.mvp.model.UserModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModules {

    private UserContract.UserView userView;

    public UserModules(UserContract.UserView userView) {
        this.userView = userView;
    }

    @Provides
    public UserContract.UserView provideview(){
        return userView;
    }

    @Provides
    public UserContract.UserModel providemodel(UserModel myModel){
        return myModel;
    }

}
