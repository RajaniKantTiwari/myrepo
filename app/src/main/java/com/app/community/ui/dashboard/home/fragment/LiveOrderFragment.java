package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.LiveOrderAdapter;
import com.app.community.ui.dashboard.home.event.MyOrderEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by rajnikant on 31/12/17.
 */

public class LiveOrderFragment extends DashboardFragment {
    private FragmentOrderBinding mBinding;
    private LiveOrderAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_order,container,false);
        CommonUtils.register(this);
        initializeAdapter();
        return mBinding.getRoot();
    }
    private void initializeAdapter() {
        mAdapter = new LiveOrderAdapter(getBaseActivity());
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mBinding.rvOrder.setAdapter(mAdapter);*/
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
    @Subscribe
    public void onMessageEvent(MyOrderEvent event) {
        if(event.getLivePastOrder()== GeneralConstant.LIVEORDER){
            mBinding.layoutOrder.setVisibility(View.VISIBLE);
        }else if(event.getLivePastOrder()== GeneralConstant.PASTORDER){
            mBinding.layoutOrder.setVisibility(View.GONE);

        }
    }
}
