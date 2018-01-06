package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentCartBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.CartAdapter;

/**
 * Created by rajnikant on 31/12/17.
 */

public class CartFragment extends DashboardFragment {
    private FragmentCartBinding mBinding;
    private CartAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_cart,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseActivity());
        mAdapter=new CartAdapter(getBaseActivity());
        mBinding.rvCartList.setLayoutManager(layoutManager);
        mBinding.rvCartList.setAdapter(mAdapter);
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
        return new CartFragment();
    }
}
