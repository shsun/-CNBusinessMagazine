package com.sshsun.cnbusinessmagazine;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.PageIndicator;

import android.support.v4.view.ViewPager;

import android.os.Bundle;

import com.shsunframework.utils.MathUtils;
import com.sshsun.cnbusinessmagazine.net.BMServer;
import com.viewpagerindicator.TabPageIndicator;

import java.io.IOException;

public class HomePageActivity extends AppCompatActivity {


    private static final String SP_NAME = "isAppLaunched";
    private static final String SP_KEY_IS_APP_LAUNCHED = "isAppLaunched";
    private static final String SP_KEY_PKG_VERSION_CODE = "versionCode";
    private static final String SP_KEY_PKG_VERSION_NAME = "versionName";


    private BMServer mServer = null;
    private int mBMServerStartCounter = 0;


    NewsTabPagerAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        startBMServer();

        boolean isAppLaunched = false;
        try {
            SharedPreferences sp = this.getSharedPreferences(SP_NAME, 0);
            isAppLaunched = sp.getBoolean(SP_KEY_IS_APP_LAUNCHED, false);

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(SP_KEY_IS_APP_LAUNCHED, true);
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            editor.putInt(SP_KEY_PKG_VERSION_CODE, info.versionCode);
            editor.putInt(SP_KEY_PKG_VERSION_NAME, info.versionCode);



            // Unlike commit(), which writes its preferences out to persistent storage synchronously,
            // apply() commits its changes to the in-memory SharedPreferences immediately but starts
            // an asynchronous commit to disk and you won't be notified of any failures.
            // editor.commit();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor.apply();
            } else {
                editor.commit();
            }
        } catch (Exception e) {
            isAppLaunched = false;
        }

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
