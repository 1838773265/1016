package com.example.p2p.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.p2p.R;
import com.example.p2p.activity.Main2Activity;
import com.example.p2p.activity.MainActivity;
import com.example.p2p.entity.GenXinEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpLoadManager {

    private String saveFilePath = "/sdcard/updataApk/";
    private String saceFileName = saveFilePath+"apkName.apk";
    private Context mcontext;
    private  boolean canceFlag=false;

    private AlertDialog.Builder uploadparb;
    ProgressBar progressBar;
    TextView textView;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    mcontext.startActivity(new Intent(mcontext,Main2Activity.class));
                    installApk();
                    break;
                case 1:
                    int string = Integer.parseInt(msg.obj.toString());
                    progressBar.setProgress(string);
                    textView.setText(string+"%");
                    break;
            }
        }
    };

    public UpLoadManager(Context mcontext) {
        this.mcontext = mcontext;
    }


    //弹框显示更新
    public void showDialog(final GenXinEntity.ResultBean resultBean){

        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("发现全新版本"+resultBean.getVersionName());
        builder.setMessage(resultBean.getDesc());

        builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downloadApk(resultBean.getApkUrl());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (resultBean.isFlag()){
                    //暂时不更新
                    mcontext.startActivity(new Intent(mcontext, Main2Activity.class));
                }else {
                    downloadApk(resultBean.getApkUrl());
                }
            }
        });

        builder.create();
        builder.show();

    }

    //下载apk
    public void downloadApk(final String apkurl){

        uploadparb = new AlertDialog.Builder(mcontext);
        View inflate = LinearLayout.inflate(mcontext, R.layout.upload_pressgrras, null);
        uploadparb.setView(inflate);

        progressBar = inflate.findViewById(R.id.prob);
        textView = inflate.findViewById(R.id.ttv);

        uploadparb.create();
        uploadparb.show();

        new Thread(){

            @Override
            public void run() {

                try {
                    URL url = new URL(apkurl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int contentLength = connection.getContentLength();
                    InputStream inputStream = connection.getInputStream();
                    File file = new File(saveFilePath);


                    if (!file.exists()){
                        file.mkdir();
                    }

                    String apkfile = saceFileName;
                    File apkFile = new File(apkfile);

                    FileOutputStream outputStream = new FileOutputStream(apkFile);

                    int count = 0;
                    int len = 0;
                    byte []bytes = new byte[1024];

                    while ( (len = inputStream.read(bytes)) != -1){
                        outputStream.write(bytes,0,len);

                        count+=len;

                        int i = (count * 100)/contentLength;

                        Log.e("+++",i+"");

                        Message message = new Message();
                        message.obj = i;
                        message.what = 1;
                        handler.sendMessage(message);

                    }

                    handler.sendEmptyMessage(0);
                    outputStream.close();
                    inputStream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }


    public void installApk(){
        File apkFile = new File(saceFileName);

        if (!apkFile.exists()){
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://"+apkFile.toString()),"application/vnd.android.package-archive");

        mcontext.startActivity(intent);

    }

    //获取当前版本号
    public long getAppVersionCode(){
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = mcontext.getApplicationContext().getPackageManager().getPackageInfo(mcontext.getPackageName(), 0);
            appVersionCode = packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersionCode;

    }

    //获取版本名称
    public String getAppVersionName(){
        String appVersionName = "";
        try {
            PackageInfo packageInfo = mcontext.getApplicationContext().getPackageManager().getPackageInfo(mcontext.getPackageName(), 0);
            appVersionName = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersionName;

    }



}
