package com.app.community.ui.dashboard.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.community.ui.dashboard.home.fragment.HelpsAndSupportFragment;
import com.app.community.ui.dashboard.home.fragment.NewsFragment;

/**
 * Created by rajnikant on 15/01/18.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private final Bundle bundle;

    public NewsPagerAdapter(FragmentManager fm, Bundle bundle) {
            super(fm);
            this.bundle=bundle;
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new HelpsAndSupportFragment();
                case 1:
                    return NewsFragment.newInstance(bundle);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
}
