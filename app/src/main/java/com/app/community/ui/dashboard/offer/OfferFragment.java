package com.app.community.ui.dashboard.offer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.community.R;
import com.app.community.databinding.FragmentOfferBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.presenter.AuthenticationPresenter;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class OfferFragment extends DashboardFragment {

    @Inject
    AuthenticationPresenter presenter;
    private FragmentOfferBinding mBinding;


    public static OfferFragment newInstance(){
        return new OfferFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_offer,container,false);
        return mBinding.getRoot();
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
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {

    }
}
