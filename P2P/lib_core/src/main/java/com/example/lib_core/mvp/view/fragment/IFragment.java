package com.example.lib_core.mvp.view.fragment;

import android.view.View;

import androidx.annotation.IdRes;

import com.example.lib_core.mvp.view.activity.IActivity;

public interface IFragment extends IActivity {

    <T extends View> T findviewbyid(@IdRes int id);

}
