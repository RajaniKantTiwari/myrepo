package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityWelcomehomeBinding;
import com.app.community.databinding.LayoutImpPlaceBinding;
import com.app.community.databinding.LayoutLastOrderBinding;
import com.app.community.databinding.LayoutLatestNewsBinding;
import com.app.community.databinding.LayoutNewsBinding;
import com.app.community.databinding.LayoutOfferBinding;
import com.app.community.databinding.LayoutWelcomeSearchBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardInsideActivity;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.HelpPlaceAdapter;
import com.app.community.ui.dashboard.home.adapter.LatestNewsAdapter;
import com.app.community.utils.AddWelcomeChildView;

import javax.inject.Inject;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class WelcomeHomeActivity extends DashboardInsideActivity {

    private ActivityWelcomehomeBinding mBinding;
    private HelpPlaceAdapter mImpPlaceAdapter;
    private LatestNewsAdapter mLatestNewsAdapter;
    @Inject
    DashboardInsidePresenter presenter;
    private LayoutInflater mInflator;
    private LayoutWelcomeSearchBinding mWelcomeBinding;
    private LayoutNewsBinding mNewsViewBinding;
    private LayoutOfferBinding mOfferBinding;
    private LayoutLastOrderBinding mLastOrderBinding;
    private LayoutImpPlaceBinding mImportantPlaceBinding;
    private LayoutLatestNewsBinding mLatestBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_welcomehome);
        mInflator=LayoutInflater.from(this);
        mWelcomeBinding=AddWelcomeChildView.addWelcomeSearchView(mInflator,mBinding);
        mNewsViewBinding=AddWelcomeChildView.addNewsView(mInflator,mBinding);
        mOfferBinding=AddWelcomeChildView.addOfferView(mInflator,mBinding);
        mLastOrderBinding=AddWelcomeChildView.addLastOrderView(mInflator,mBinding);
        mImportantPlaceBinding=AddWelcomeChildView.addImportantPlace(mInflator,mBinding);
        mLatestBinding=AddWelcomeChildView.addLatestNewsView(mInflator,mBinding);
        initializeView();
    }

    private void initializeView() {
        LinearLayoutManager placeManager=new LinearLayoutManager(this);
        placeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImportantPlaceBinding.rvImportantPlace.setLayoutManager(placeManager);
        mImpPlaceAdapter=new HelpPlaceAdapter(this);
        mImportantPlaceBinding.rvImportantPlace.setAdapter(mImpPlaceAdapter);
        LinearLayoutManager latestNewsManager=new LinearLayoutManager(this);
        mLatestBinding.rvLatestNews.setLayoutManager(latestNewsManager);
        mLatestNewsAdapter=new LatestNewsAdapter(this);
        mLatestBinding.rvLatestNews.setAdapter(mLatestNewsAdapter);
    }



    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initializeData() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


}
