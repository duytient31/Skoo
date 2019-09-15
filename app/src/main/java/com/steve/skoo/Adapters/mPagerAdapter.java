package com.steve.skoo.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.steve.skoo.Fragments.howto1;
import com.steve.skoo.Fragments.howto2;
import com.steve.skoo.Fragments.howto3;

public class mPagerAdapter extends FragmentPagerAdapter {
    public mPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new howto1();
            case 1:
                return new howto2();
            case 2:
                return new howto3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Tab 1";
            case 1:
                return "Tab 2";
            case 2:
                return "Tab 3";
            default:
                return null;

        }
    }
}