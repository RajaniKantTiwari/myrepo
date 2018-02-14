package com.app.community.ui.dashboard.offer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityOfferDetailsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.ui.dashboard.DashboardInsideActivity;
import com.app.community.ui.dashboard.offer.adapter.OfferDetailsAdapter;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class OfferDetailsActivity extends DashboardInsideActivity {

    @Inject
    CommonPresenter presenter;
    private ActivityOfferDetailsBinding mBinding;
    private OfferDetailsAdapter mOfferAdapter;
    private Offer offer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_offer_details);
        initializeData();
        setListener();
    }

    private void setListener() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.offer));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }


    public void initializeData() {
        Intent intent = getIntent();
        if (CommonUtils.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtils.isNotNull(bundle)) {
                mBinding.rvOffer.setVisibility(View.VISIBLE);
                mBinding.noDataFound.layoutNoData.setVisibility(View.GONE);
                offer = bundle.getParcelable(AppConstants.OFFER);
            } else {
                mBinding.noDataFound.layoutNoData.setVisibility(View.VISIBLE);
                mBinding.rvOffer.setVisibility(View.GONE);

            }
        }
        mOfferAdapter = new OfferDetailsAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvOffer.setLayoutManager(layoutManager);
        mBinding.rvOffer.setAdapter(mOfferAdapter);
    }


    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
        if (mBinding.layoutHeader.ivBack == view) {
            finish();
        }
    }
}
