package com.example.p2p.dager.licai.modules;

import com.example.p2p.mvp.contract.LiCaiContract;
import com.example.p2p.mvp.model.LiCaiModel;

import dagger.Module;
import dagger.Provides;

@Module
public class LiCaiModules {

    private LiCaiContract.LiCaiView view;

    public LiCaiModules(LiCaiContract.LiCaiView view) {
        this.view = view;
    }

    @Provides
    public LiCaiContract.LiCaiView provideview(){
        return view;
    }

    @Provides
    public LiCaiContract.LiCaiModel providemodel(LiCaiModel myModel){
        return myModel;
    }

}
