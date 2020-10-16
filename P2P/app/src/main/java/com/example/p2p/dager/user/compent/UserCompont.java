package com.example.p2p.dager.user.compent;

import com.example.p2p.activity.LoginActivity;
import com.example.p2p.activity.RegisterActivity;
import com.example.p2p.dager.user.modules.UserModules;
import com.example.p2p.fragment.Fragment_shouye;

import dagger.Component;

@Component(modules = UserModules.class)
public interface UserCompont {

    void injectLoginActivity (LoginActivity loginActivity);
    void injectRegisterActivity (RegisterActivity registerActivity);
    void injectShouyeFragment(Fragment_shouye fragment_shouye);

}
