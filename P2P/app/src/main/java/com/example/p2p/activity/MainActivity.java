package com.example.p2p.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.p2p.R;
import com.example.p2p.entity.MainTab;
import com.example.p2p.fragment.Fragment_licai;
import com.example.p2p.fragment.Fragment_more;
import com.example.p2p.fragment.Fragment_shouye;
import com.example.p2p.fragment.Fragment_wode;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private CommonTabLayout tab;

    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.vp);
        tab = findViewById(R.id.tab);

        mTabEntitys.add(new MainTab("首页",R.drawable.bottom02,R.drawable.bottom01));
        mTabEntitys.add(new MainTab("投资",R.drawable.bottom04,R.drawable.bottom03));
        mTabEntitys.add(new MainTab("我的",R.drawable.bottom06,R.drawable.bottom05));
        mTabEntitys.add(new MainTab("更多",R.drawable.bottom08,R.drawable.bottom07));

        list.add(new Fragment_shouye());
        list.add(new Fragment_licai());
        list.add(new Fragment_wode());
        list.add(new Fragment_more());

        tab.setTabData(mTabEntitys);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }
}
