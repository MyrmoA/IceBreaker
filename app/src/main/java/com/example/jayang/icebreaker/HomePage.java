package com.example.jayang.icebreaker;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class HomePage extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        toolbar = (Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        viewPager =(ViewPager)findViewById(R.id.viewpager);
        VIewPagerAdapter vIewPagerAdapter = new VIewPagerAdapter(getSupportFragmentManager());
        vIewPagerAdapter.addFragment(new OneFrag(),"Questions");
        vIewPagerAdapter.addFragment(new TwoFrag(),"Users");

        viewPager.setAdapter(vIewPagerAdapter);

        tabLayout =(TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
