package com.example.p2p.dager.licai.compent;

import com.example.p2p.dager.licai.modules.LiCaiModules;
import com.example.p2p.fragment_licai.Fragment_quanbu;

import dagger.Component;

@Component(modules = LiCaiModules.class)
public interface LiCaiCompont {

    void injectFragment_quan(Fragment_quanbu fragment_quanbu);

}
