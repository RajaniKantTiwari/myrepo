package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.LiveOrderAdapter;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class PastOrderFragment extends DashboardFragment implements LiveOrderAdapter.OrderListener {
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
        mAdapter = new LiveOrderAdapter(getBaseActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mBinding.rvOrder.setAdapter(mAdapter);
    }
    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return PastOrderFragment.class.getSimpleName();
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

    public void setPastOrder(ArrayList<Order> recentOrderList) {
        mAdapter.setList(recentOrderList);
    }

    public void setVisibility(int visible) {
        mBinding.layoutOrder.setVisibility(visible);
    }

    @Override
    public void viewDetailsClick(int position) {

    }

    @Override
    public void helpClick(int position) {

    }

    @Override
    public void feedBackClicked(int position) {

    }
}
