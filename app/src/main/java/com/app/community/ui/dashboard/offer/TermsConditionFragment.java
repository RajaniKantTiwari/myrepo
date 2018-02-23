package com.app.community.ui.dashboard.offer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.home.adapter.LiveOrderAdapter;

/**
 * Created by rajnikant on 31/12/17.
 */

public class TermsConditionFragment extends BaseFragment {
    private FragmentOrderBinding mBinding;
    private LiveOrderAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_order,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }
    private void initializeAdapter() {

    }
    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return TermsConditionFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
    public static Fragment newInstance() {
        TermsConditionFragment fragment = new TermsConditionFragment();
        return fragment;
    }
}
