package com.example.desktop.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.desktop.R;
import com.example.desktop.adapter.HomeAdapter;
import com.example.desktop.entity.TabEntity;
import com.example.desktop.fragment.CollectFragment;
import com.example.desktop.fragment.HomeFragment;
import com.example.desktop.fragment.MyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "收藏", "我的"};
    private int[] mIconUnSelectIds = {
            R.mipmap.home_unselect, R.mipmap.collect_unselect,
            R.mipmap.my_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.home_selected, R.mipmap.collect_selected,
            R.mipmap.my_selected};

    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;

    @Override
    protected int initLayout() {
        return  R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(CollectFragment.newInstance("1","2"));
        mFragments.add(MyFragment.newInstance("1","2"));

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }

        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        // 上下联动添加
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(), mTitles, mFragments));
    }



}