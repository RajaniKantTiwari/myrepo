package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityProductDetailBinding;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.DashboardInsideActivity;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.community.ui.dashboard.home.adapter.StoreAdapter;
import com.app.community.ui.presenter.AuthenticationPresenter;

import javax.inject.Inject;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class ProductDetailsActivity extends DashboardInsideActivity {

    private ActivityProductDetailBinding mBinding;
    private StoreAdapter mPhotoAdapter;
    private ReviewAdapter mReviewAdapter;
    @Inject
    DashboardInsidePresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_product_detail);
        initializeView();
    }

    private void initializeView() {
        LinearLayoutManager photoManager=new LinearLayoutManager(this);
        photoManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.photoRecycler.setLayoutManager(photoManager);
        LinearLayoutManager reviewManager=new LinearLayoutManager(this);
        mBinding.rvReview.setLayoutManager(reviewManager);
        mBinding.rvReview.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
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
        mPhotoAdapter =new StoreAdapter(this);
        mBinding.photoRecycler.setAdapter(mPhotoAdapter);
        mReviewAdapter=new ReviewAdapter(this);
        mBinding.rvReview.setAdapter(mReviewAdapter);
        presenter.getProductDetails(this,new ProductRequest(3,8));
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
