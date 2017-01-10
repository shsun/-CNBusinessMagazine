package com.sshsun.cnbusinessmagazine;

import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.PageIndicator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.os.Bundle;
import android.view.Menu;

import com.shsunframework.utils.MathUtils;
import com.sshsun.cnbusinessmagazine.net.BMServer;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private BMServer mServer = null;
    private int mBMServerStartCounter = 0;


    NewsTabPagerAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBMServer();



        mPager = (ViewPager)this.findViewById(R.id.vp_news);
        mPager.setAdapter(new NewsTabPagerAdapter(getSupportFragmentManager()));//给viewpager设置数据适配器


        // ViewPagerIndicator声明
        TabPageIndicator indicator = (TabPageIndicator) this
                .findViewById(R.id.vpi_indicator);
        indicator.setViewPager(mPager);// 绑定必须要在viewpager设置好数据适配器之后

    }

    private void startBMServer() {
        try {
            if (mBMServerStartCounter < 10) {
                mBMServerStartCounter += 1;
                this.mServer = new BMServer(MathUtils.random(1000, 9999));
            } else {
                this.mServer = null;
            }
        } catch (IOException e) {
            startBMServer();
        }
    }
}
