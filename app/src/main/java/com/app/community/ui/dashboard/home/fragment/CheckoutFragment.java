package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentCheckoutBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.CheckoutCartAdapter;

/**
 * Created by ashok on 26/12/17.
 */

public class CheckoutFragment extends DashboardFragment {
    private FragmentCheckoutBinding mBinding;
    private CheckoutCartAdapter mCheckoutAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_checkout,container,false);
        initializeView();
        return mBinding.getRoot();
    }
    private void initializeView() {
        LinearLayoutManager photoManager=new LinearLayoutManager(getBaseActivity());
        mBinding.rvCartItem.setLayoutManager(photoManager);
        mBinding.rvCartItem.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
    }
    @Override
    public void initializeData() {
        mCheckoutAdapter =new CheckoutCartAdapter(getBaseActivity());
        mBinding.rvCartItem.setAdapter(mCheckoutAdapter);
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
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
