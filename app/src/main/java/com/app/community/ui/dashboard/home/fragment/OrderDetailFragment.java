package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentConfirmBinding;
import com.app.community.databinding.FragmentOrderDetailBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.CheckoutCartAdapter;

/**
 * Created by rajnikant on 31/12/17.
 */

public class OrderDetailFragment extends DashboardFragment {
    private FragmentOrderDetailBinding mBinding;
    private CheckoutCartAdapter mCheckoutAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_order_detail,container,false);
        initializeView();
        return mBinding.getRoot();
    }
    private void initializeView() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseActivity());
        mBinding.rvOrderItem.setLayoutManager(layoutManager);
        mBinding.rvOrderItem.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
    }
    @Override
    public void initializeData() {
        mCheckoutAdapter =new CheckoutCartAdapter(getBaseActivity());
        mBinding.rvOrderItem.setAdapter(mCheckoutAdapter);
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
}
