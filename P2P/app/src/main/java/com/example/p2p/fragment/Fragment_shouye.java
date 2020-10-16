package com.example.p2p.fragment;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.dager.user.compent.DaggerUserCompont;
import com.example.p2p.dager.user.modules.UserModules;
import com.example.p2p.entity.ShouYeEntity;
import com.example.p2p.entity.UserEntity;
import com.example.p2p.mvp.contract.UserContract;
import com.example.p2p.mvp.presenter.UserPresenter;
import com.example.p2p.myview.CircleProgressView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_shouye extends BaseFragment<UserPresenter> implements UserContract.UserView {



    private List<String> imgurllist = new ArrayList<>();


    private Banner bannerShouye;
    private TextView ttvTitle;
    private TextView ttvBaidu;
    private CircleProgressView cirview;
    private TextView ttvLilv;


    @Override
    public int bindlayout() {
        return R.layout.fragmnet_shouye;
    }

    @Override
    public void initInject() {
        DaggerUserCompont.builder().userModules(new UserModules(this)).build().injectShouyeFragment(this);
    }

    @Override
    public void initview() {


        bannerShouye = findviewbyid(R.id.banner_shouye);
        ttvTitle = findviewbyid(R.id.ttv_title);
        ttvBaidu = findviewbyid(R.id.ttv_baidu);
        cirview = findviewbyid(R.id.cirview);
        ttvLilv = findviewbyid(R.id.ttv_lilv);

    }

    @Override
    public void initdata() {
        presenter.getshouye();

        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            int anInt = 0;
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ttvBaidu.setText(anInt+"%");
                        cirview.setProgress(anInt);
                        if (anInt == 90){
                            timer.cancel();
                        }else {
                            anInt++;
                        }
                    }
                });
            }
        },0,20);

    }

    @Override
    public void showLogin(UserEntity user) {

    }

    @Override
    public void showRegister(UserEntity user) {

    }

    @Override
    public void showShouye(ShouYeEntity shouYe) {

        ShouYeEntity.ResultBean result = shouYe.getResult();
        List<ShouYeEntity.ResultBean.ImageArrBean> imageArr = result.getImageArr();

        imgurllist.clear();

        for (int i = 0; i < imageArr.size(); i++) {

            imgurllist.add(imageArr.get(i).getIMAURL());

        }

        bannerShouye.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getContext()).load(path).into(imageView);
            }
        });

        bannerShouye.setImages(imgurllist);
        bannerShouye.start();

        Log.e("+++",shouYe.toString()+"");


    }
}
