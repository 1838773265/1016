package com.example.p2p.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lib_core.mvp.view.fragment.BaseFragment;
import com.example.p2p.R;
import com.example.p2p.entity.MainTab;
import com.example.p2p.fragment_licai.Fragment_quanbu;
import com.example.p2p.fragment_licai.Fragment_remen;
import com.example.p2p.fragment_licai.Fragment_tuijian;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_licai extends BaseFragment {
    @Override
    public int bindlayout() {
        return R.layout.fragmnet_licai;
    }

    private RadioButton radA1;
    private RadioButton radA2;
    private RadioButton radA3;
    private ViewPager vpLicai;

    private List<Fragment> list = new ArrayList<>();

    private Fragment_quanbu fragment_quanbu;
    private Fragment_remen fragment_remen;
    private Fragment_tuijian fragment_tuijian;


    @Override
    public void initInject() {

    }

    @Override
    public void initview() {

        vpLicai = (ViewPager) findviewbyid(R.id.vp_licai);
        radA1 = (RadioButton) findviewbyid(R.id.rad_a1);
        radA2 = (RadioButton) findviewbyid(R.id.rad_a2);
        radA3 = (RadioButton) findviewbyid(R.id.rad_a3);

        fragment_quanbu = new Fragment_quanbu();
        fragment_remen = new Fragment_remen();
        fragment_tuijian = new Fragment_tuijian();

        list.add(fragment_quanbu);
        list.add(fragment_remen);
        list.add(fragment_tuijian);
        radA1.setTextColor(Color.RED);
        radA1.setBackgroundColor(Color.parseColor("#51B3DF"));

        radA2.setTextColor(Color.BLACK);
        radA2.setBackgroundColor(Color.WHITE);
        radA3.setTextColor(Color.BLACK);
        radA3.setBackgroundColor(Color.WHITE);
        vpLicai.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        vpLicai.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    radA1.setTextColor(Color.RED);
                    radA1.setBackgroundColor(Color.parseColor("#51B3DF"));

                    radA2.setTextColor(Color.BLACK);
                    radA2.setBackgroundColor(Color.WHITE);
                    radA3.setTextColor(Color.BLACK);
                    radA3.setBackgroundColor(Color.WHITE);
                }else if (position == 1){
                    radA2.setTextColor(Color.RED);
                    radA2.setBackgroundColor(Color.parseColor("#51B3DF"));

                    radA1.setTextColor(Color.BLACK);
                    radA1.setBackgroundColor(Color.WHITE);
                    radA3.setTextColor(Color.BLACK);
                    radA3.setBackgroundColor(Color.WHITE);
                }else if (position == 2){
                    radA3.setTextColor(Color.RED);
                    radA3.setBackgroundColor(Color.parseColor("#51B3DF"));

                    radA2.setTextColor(Color.BLACK);
                    radA2.setBackgroundColor(Color.WHITE);
                    radA1.setTextColor(Color.BLACK);
                    radA1.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                radA1.setTextColor(Color.RED);
                radA1.setBackgroundColor(Color.parseColor("#51B3DF"));

                radA2.setTextColor(Color.BLACK);
                radA2.setBackgroundColor(Color.WHITE);
                radA3.setTextColor(Color.BLACK);
                radA3.setBackgroundColor(Color.WHITE);

                vpLicai.setCurrentItem(0);

            }
        });

        radA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radA2.setTextColor(Color.RED);
                radA2.setBackgroundColor(Color.parseColor("#51B3DF"));

                radA1.setTextColor(Color.BLACK);
                radA1.setBackgroundColor(Color.WHITE);
                radA3.setTextColor(Color.BLACK);
                radA3.setBackgroundColor(Color.WHITE);

                vpLicai.setCurrentItem(1);

            }
        });

        radA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radA3.setTextColor(Color.RED);
                radA3.setBackgroundColor(Color.parseColor("#51B3DF"));

                radA2.setTextColor(Color.BLACK);
                radA2.setBackgroundColor(Color.WHITE);
                radA1.setTextColor(Color.BLACK);
                radA1.setBackgroundColor(Color.WHITE);

                vpLicai.setCurrentItem(2);

            }
        });

    }


    @Override
    public void initdata() {



    }
}
