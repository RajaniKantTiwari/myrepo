package com.app.community.ui.dashboard.offer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.app.community.ui.dashboard.offer.OffersFragment;
import com.app.community.ui.dashboard.offer.TermsConditionFragment;
import com.app.community.utils.GeneralConstant;

/**
 * Created by atul on 03/10/17.
 * To inject activity reference.
 */

public class OfferPagerAdapter extends FragmentStatePagerAdapter {

    private final SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public OfferPagerAdapter(FragmentManager manager) {
        super(manager);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case GeneralConstant.FRAGMENT_OFFER.OFFER_DETAILS:
                return OffersFragment.newInstance();
            case GeneralConstant.FRAGMENT_OFFER.TERMS_CONDITION:
                return TermsConditionFragment.newInstance();
        }
        return null;
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 2;
    }

}
