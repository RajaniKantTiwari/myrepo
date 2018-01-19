package com.app.community.ui.dashboard.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.community.ui.dashboard.home.fragment.HelpsAndSupportFragment;
import com.app.community.ui.dashboard.home.fragment.NewsFragment;

/**
 * Created by rajnikant on 15/01/18.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {
        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new HelpsAndSupportFragment();
                case 1:
                    return new NewsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
}
