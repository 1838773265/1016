package com.example.p2p.fragment_licai;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.myview.FlowLayout;
import com.example.p2p.utils.DrawUtils;
import com.example.p2p.utils.MyUtils;

import java.util.Random;

public class Fragment_tuijian extends BaseFragment {
    @Override
    public int bindlayout() {
        return R.layout.fragment_tuijian;
    }

    @Override
    public void initInject() {

    }

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷计划", "30天理财计划", "180天理财计划", "月月升", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"
    };


    private FlowLayout flowHot;

    @Override
    public void initview() {
        flowHot = (FlowLayout) findviewbyid(R.id.flow_hot);

        for(int i = 0; i < datas.length; i++) {
            final TextView tv = new TextView(getContext());

            //设置属性
            tv.setText(datas[i]);
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = MyUtils.dp2px(getContext(),5);
            mp.rightMargin = MyUtils.dp2px(getContext(),5);
            mp.topMargin = MyUtils.dp2px(getContext(),5);
            mp.bottomMargin = MyUtils.dp2px(getContext(),5);
            tv.setLayoutParams(mp);//设置边距

            int padding = MyUtils.dp2px(getContext(),5);
            tv.setPadding(padding, padding, padding, padding);//设置内边距

            tv.setTextSize(MyUtils.dp2px(getContext(),10));

            Random random = new Random();
            int red = random.nextInt(211);
            int green = random.nextInt(211);
            int blue = random.nextInt(211);
            //设置单一背景
//             tv.setBackground(DrawUtils.getDrawable(Color.rgb(red,green,blue),UIUtils.dp2px(5)));
            //设置具有选择器功能的背景
            tv.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(getContext(),Color.rgb(red, green, blue), MyUtils.dp2px(getContext(),5)), DrawUtils.getDrawable(getContext(),Color.WHITE, MyUtils.dp2px(getContext(),5))));
            //设置textView是可点击的.如果设置了点击事件，则TextView就是可点击的。
//             tv.setClickable(true);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyUtils.toast(getContext(),tv.getText().toString(),false);
                }
            });
            flowHot.addView(tv);
        }

    }

    @Override
    public void initdata() {

    }
}
