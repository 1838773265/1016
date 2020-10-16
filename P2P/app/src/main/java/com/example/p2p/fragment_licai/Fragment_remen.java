package com.example.p2p.fragment_licai;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.myview.RandomLayout;
import com.example.p2p.utils.MyUtils;

import java.util.Random;

public class Fragment_remen extends BaseFragment implements View.OnClickListener {

    private String[] tags1 = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    private String[] tags2 = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    public int bindlayout() {
        return R.layout.fragment_remen;
    }

    @Override
    public void initInject() {

    }

    private RandomLayout randomlayout1;
    private RandomLayout randomlayout2;




    @Override
    public void initview() {
        randomlayout1 = (RandomLayout) findviewbyid(R.id.randomlayout1);
        randomlayout2 = (RandomLayout) findviewbyid(R.id.randomlayout2);

        for (String tag : tags1) {

            randomlayout1.addView(createTagView(tag));

        }

        for (String tag : tags2) {

            randomlayout2.addView(createTagView(tag));
        }

    }

    @Override
    public void initdata() {

    }

    private View createTagView(String tag) {

        TextView textView = new TextView(getContext());
        textView.setOnClickListener(this);
        textView.setText(tag);
        textView.setGravity(Gravity.CENTER);
        int padding = (int) MyUtils.dipToPX(getContext(), 4);
        textView.setPadding(padding, padding, padding, padding);
        //需要指定宽度和高度
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //通过 GradientDrawable实现textview文本的效果设计
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(MyUtils.dipToPX(getContext(), 4));
        gradientDrawable.setStroke((int) MyUtils.dipToPX(getContext(), 1), Color.GRAY);
        //背景设置随机颜色
        int randomColor = geneareRandomColor();
        gradientDrawable.setColor(randomColor);
        textView.setBackgroundDrawable(gradientDrawable);
        //根据背景颜色的深浅来设置字体颜色
        textView.setTextColor(generateTextColor(randomColor));
        return textView;
    }


    private int generateTextColor(int randomColor) {
        //0~255
        int avgColor = (Color.red(randomColor) + Color.blue(randomColor) + Color.blue(randomColor)) / 3;
        //背景颜色浅
        if (avgColor > 0xff / 2) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }
    private Random mRandom = new Random();

    private int geneareRandomColor() {
        //0~255
        return Color.argb(0xff, mRandom.nextInt(0xfff), mRandom.nextInt(0xfff), mRandom.nextInt(0xfff));
    }

    @Override
    public void onClick(View v) {
        String tag = ((TextView) v).getText().toString();
        Toast.makeText(getContext(), tag+"", Toast.LENGTH_SHORT).show();
    }


}
