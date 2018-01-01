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
import com.app.community.databinding.FragmentLocationListBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.ProductAdapter;
import com.app.community.ui.dashboard.home.adapter.RecentAdapter;
import com.app.community.ui.dashboard.home.event.NewsEvent;
import com.app.community.ui.dashboard.home.event.ProductEvent;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by rajnikant on 31/12/17.
 */

public class RecentFragment extends DashboardFragment {
    private FragmentLocationListBinding mBinding;
    private RecentAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_location_list,container,false);
        EventBus.getDefault().register(this);
        initializeAdapter();
        return mBinding.getRoot();
    }
    private void initializeAdapter() {
        mAdapter=new RecentAdapter(getBaseActivity());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseActivity());
        mBinding.rvProduct.setLayoutManager(layoutManager);
        mBinding.rvProduct.setAdapter(mAdapter);
    }
    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return RecentFragment.class.getSimpleName();
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(NewsEvent event) {
        if(event.getRecentCategory()== GeneralConstant.RECENT){
            mBinding.layoutList.setVisibility(View.VISIBLE);
        }else if(event.getRecentCategory()== GeneralConstant.CATEGORY){
            mBinding.layoutList.setVisibility(View.GONE);

        }
    }
}
