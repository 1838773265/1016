package com.example.p2p.adapter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.p2p.R;
import com.example.p2p.entity.QuanBuEntity;
import com.example.p2p.myview.CircleProgressView;

import java.util.List;

public class MyQuanBuAdapter extends BaseQuickAdapter<QuanBuEntity.ResultBean, BaseViewHolder> {
    public MyQuanBuAdapter(int layoutResId, @Nullable List<QuanBuEntity.ResultBean> data) {
        super(layoutResId, data);
    }

    public int anInt = 0;

    @Override
    protected void convert(BaseViewHolder helper, QuanBuEntity.ResultBean item) {

        helper.setText(R.id.num1, item.getMoney() + "万");
        helper.setText(R.id.num2, item.getYearRate() + "%");
        helper.setText(R.id.num3, item.getSuodingDays() + "天");
        helper.setText(R.id.num4, item.getMoney() + "");
        helper.setText(R.id.num6, item.getMemberNum() + "");
        helper.setText(R.id.num5, "本息保障");
        helper.setText(R.id.p2p_name, item.getName());
        CircleProgressView view = helper.getView(R.id.circleprogress_item);
        view.setProgress(Integer.valueOf(item.getProgress()));
        helper.setText(R.id.pro_text_item, item.getProgress() + "%");
        if (helper.getAdapterPosition() == anInt-1){
            Log.e("+++",helper.getAdapterPosition()+"0000");
            helper.setVisible(R.id.xian,false);
        }

    }
    }

