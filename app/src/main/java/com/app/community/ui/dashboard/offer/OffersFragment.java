package com.app.community.ui.dashboard.offer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOfferDetailsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.offer.adapter.OfferDetailsAdapter;

/**
 * Created by rajnikant on 31/12/17.
 */

public class OffersFragment extends BaseFragment {
    private FragmentOfferDetailsBinding mBinding;
    private OfferDetailsAdapter mOfferAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_offer_details,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }
    private void initializeAdapter() {
        mOfferAdapter = new OfferDetailsAdapter(getBaseActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvOffer.setLayoutManager(layoutManager);
        mBinding.rvOffer.setAdapter(mOfferAdapter);
    }
    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
    public static Fragment newInstance() {
        OffersFragment fragment = new OffersFragment();
        return fragment;
    }
}
