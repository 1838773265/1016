package com.example.p2p.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.p2p.R;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    private TextView ttvtime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ttvtime = findViewById(R.id.ttvtime);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int anInt = 3;
            @Override
            public void run() {


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (anInt == 0){
                            startActivity(new Intent(Main2Activity.this,MainActivity.class));
                            timer.cancel();
                            finish();
                        }

                        ttvtime.setText("倒计时："+anInt+"秒");

                        anInt--;

                    }
                });



            }
        },0,1000);

    }
}
