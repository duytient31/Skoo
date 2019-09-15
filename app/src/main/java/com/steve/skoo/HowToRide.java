package com.steve.skoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.steve.skoo.Adapters.mPagerAdapter;

public class HowToRide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_ride);

        Toolbar toolbar = findViewById(R.id.how_to_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        ViewPager viewPager = findViewById(R.id.how_to_viewpager);
        mPagerAdapter myPagerAdapter = new mPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.how_to_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}


