package com.example.p2p.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_core.mvp.view.activity.BaseActivity;
import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.activity.BarChartActivity;
import com.example.p2p.activity.LoginActivity;
import com.example.p2p.activity.MoreActivity;
import com.example.p2p.activity.PieChartActivity;

public class Fragment_wode extends BaseFragment {

    SharedPreferences sp;

    String name;
    String img;
    private ImageView imgMore;
    private ImageView imgTou;
    private TextView ttvName;

    private TextView ttvTouzi;
    private TextView ttvZichan;



    @Override
    public int bindlayout() {
        return R.layout.fragmnet_wode;
    }

    @Override
    public void initInject() {

    }

    @Override
    public void initview() {
        sp = getActivity().getSharedPreferences("1802a", Context.MODE_PRIVATE);
        name = sp.getString("name", null);
        img = sp.getString("img", null);

        ttvTouzi = (TextView) findviewbyid(R.id.ttv_touzi);
        ttvZichan = (TextView) findviewbyid(R.id.ttv_zichan);
        imgMore = (ImageView) findviewbyid(R.id.img_more);
        imgTou = (ImageView) findviewbyid(R.id.img_tou);
        ttvName = (TextView) findviewbyid(R.id.ttv_name);
        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getContext(), MoreActivity.class));
            }
        });

        ttvTouzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), BarChartActivity.class));

            }
        });

        ttvZichan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PieChartActivity.class));
            }
        });

    }

    @Override
    public void initdata() {

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("main","onPause");
        if (name == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle("提示")
                    .setMessage("您还没有登录")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            getActivity().startActivity(new Intent(getContext(), LoginActivity.class));

                        }
                    })
                    .setCancelable(false)
                    .show();

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("main","onResume");

        if (name == null){
            new AlertDialog.Builder(this.getActivity())
                    .setTitle("提示")
                    .setMessage("您还没有登录")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            getActivity().startActivity(new Intent(getContext(), LoginActivity.class));

                        }
                    })
                    .setCancelable(true)
                    .show();

        }else {
            ttvName.setText(name+"");
        }

        if (img != null){
            imgTou.setImageBitmap(BitmapFactory.decodeFile("/storage/sdcard0/Android/data/com.example.p2p/files/icon.png"));
        }

    }

}
