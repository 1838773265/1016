package com.example.lib_core.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lib_core.R;

public class LoadDialog extends Dialog {

    private LinearLayout linearLayout;
    private ProgressBar prg;
    private TextView tv;

    public LoadDialog(Context context) {
        super(context);

        setCancelable(false);
        getWindow().setDimAmount(0f);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(initview());

    }

    private LinearLayout initview() {

        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundResource(R.drawable.dialog_bg);
        //透明度
        linearLayout.setAlpha(0.5f);

        LinearLayout.LayoutParams proll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,0);
        proll.weight = 8;
        proll.gravity = Gravity.CENTER;
        prg = new ProgressBar(getContext());
        prg.setLayoutParams(proll);

        LinearLayout.LayoutParams tvll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
        tvll.weight = 2;
        tv = new TextView(getContext());
        tv.setText("正在加载...");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(tvll);

        linearLayout.addView(prg);
        linearLayout.addView(tv);
        return linearLayout;

    }
}
