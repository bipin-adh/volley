package com.example.volleydemo.activities;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.volleydemo.R;

import com.example.volleydemo.adapters.TabPagerAdapter;
import com.example.volleydemo.fragments.*;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: mainactivity oncreate");
        initView();
    }

    public void initView()
    {
        setToolbar();
        initTab();

    }

    public void setToolbar() {

        Log.d(TAG, "setToolbar: toolbar setting");
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarMain);
        toolbar = (Toolbar) findViewById(R.id.toolBarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);


    }
    private void initTab() {
        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutEvents);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setupWithViewPager(viewPager);


    }
    private void setupViewPager(ViewPager viewPager) {
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        String tabTitles[] = getResources().getStringArray(R.array.tab_titles);
        adapter.addFragWithTitle(new TopMoviesFragment(),tabTitles[0]);
        adapter.addFragWithTitle(new BoxOfficeFragment(),tabTitles[1]);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }
}