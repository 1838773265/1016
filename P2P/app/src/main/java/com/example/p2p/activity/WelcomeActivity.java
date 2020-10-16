package com.example.p2p.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.p2p.R;
import com.example.p2p.entity.GenXinEntity;
import com.example.p2p.utils.UpLoadManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"},100);
        }

        startActivity(new Intent(WelcomeActivity.this,Main2Activity.class));

//        getGenXin();

    }

    private void getGenXin() {
        OkGo.<String>get("http://10.161.9.91/banben/gengxin.json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        String string = response.body().toString();
                        Log.e("+++",string+"");

                        Gson gson = new Gson();
                        GenXinEntity genXinEntity = gson.fromJson(string, GenXinEntity.class);

                        UpLoadManager upLoadManager = new UpLoadManager(WelcomeActivity.this);
                        long appVersionCode = upLoadManager.getAppVersionCode();
                        upLoadManager.getAppVersionName();

//                        Log.e("+++",appVersionCode+""+genXinEntity.getResult().getVersionCode());

                        if (appVersionCode < genXinEntity.getResult().getVersionCode()){
                            upLoadManager.showDialog(genXinEntity.getResult());
                        }else {
                            startActivity(new Intent(WelcomeActivity.this,Main2Activity.class));
                        }




                    }
                });



    }

}
