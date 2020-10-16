package com.example.p2p.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class MainTab implements CustomTabEntity {

    private String title;

    private int a;
    private int b;

    public MainTab(String title, int a, int b) {
        this.title = title;
        this.a = a;
        this.b = b;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return a;
    }

    @Override
    public int getTabUnselectedIcon() {
        return b;
    }
}
