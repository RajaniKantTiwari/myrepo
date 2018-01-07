package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentWelcomehomeBinding;
import com.app.community.databinding.LayoutImpPlaceBinding;
import com.app.community.databinding.LayoutLastOrderBinding;
import com.app.community.databinding.LayoutLatestNewsBinding;
import com.app.community.databinding.LayoutNewsBinding;
import com.app.community.databinding.LayoutOfferBinding;
import com.app.community.databinding.LayoutWelcomeSearchBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.HelpPlaceAdapter;
import com.app.community.ui.dashboard.home.adapter.LatestNewsAdapter;
import com.app.community.utils.AddWelcomeChildView;

import javax.inject.Inject;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class WelcomeHomeFragment extends DashboardFragment {

    private FragmentWelcomehomeBinding mBinding;
    private HelpPlaceAdapter mImpPlaceAdapter;
    private LatestNewsAdapter mLatestNewsAdapter;
    @Inject
    DashboardInsidePresenter presenter;
    private LayoutWelcomeSearchBinding mWelcomeBinding;
    private LayoutNewsBinding mNewsViewBinding;
    private LayoutOfferBinding mOfferBinding;
    private LayoutLastOrderBinding mLastOrderBinding;
    private LayoutImpPlaceBinding mImportantPlaceBinding;
    private LayoutLatestNewsBinding mLatestBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_welcomehome,container,false);
        mWelcomeBinding=AddWelcomeChildView.addWelcomeSearchView(inflater,mBinding);
        mNewsViewBinding=AddWelcomeChildView.addNewsView(inflater,mBinding);
        mOfferBinding=AddWelcomeChildView.addOfferView(inflater,mBinding);
        mLastOrderBinding=AddWelcomeChildView.addLastOrderView(inflater,mBinding);
        mImportantPlaceBinding=AddWelcomeChildView.addImportantPlace(inflater,mBinding);
        mLatestBinding=AddWelcomeChildView.addLatestNewsView(inflater,mBinding);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager placeManager=new LinearLayoutManager(getContext());
        placeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImportantPlaceBinding.rvImportantPlace.setLayoutManager(placeManager);
        mImpPlaceAdapter=new HelpPlaceAdapter(getBaseActivity());
        mImportantPlaceBinding.rvImportantPlace.setAdapter(mImpPlaceAdapter);
        LinearLayoutManager latestNewsManager=new LinearLayoutManager(getBaseActivity());
        mLatestBinding.rvLatestNews.setLayoutManager(latestNewsManager);
        mLatestNewsAdapter=new LatestNewsAdapter(getBaseActivity());
        mLatestBinding.rvLatestNews.setAdapter(mLatestNewsAdapter);
    }



    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void setListener() {
        mNewsViewBinding.layoutNews.setOnClickListener(this);
        mLastOrderBinding.layoutLastOrder.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return WelcomeHomeFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void onClick(View view) {
       if(view==mNewsViewBinding.layoutNews){

       }else if(view==mLastOrderBinding.layoutLastOrder){

       }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    public static Fragment newInstance() {
        return new WelcomeHomeFragment();
    }
}
