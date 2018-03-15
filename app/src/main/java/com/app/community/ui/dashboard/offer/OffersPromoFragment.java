package com.app.community.ui.dashboard.offer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOfferPromoBinding;
import com.app.community.network.request.dashboard.MerchantCouponRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.coupon.MerchantCouponResponseData;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.offer.adapter.PromoOfferAdapter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class OffersPromoFragment extends DashboardFragment implements PromoOfferAdapter.PromoListener {
    private FragmentOfferPromoBinding mBinding;
    private PromoOfferAdapter mOfferAdapter;
    private ArrayList<Offer> couponList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_offer_promo, container, false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        couponList = new ArrayList<>();
        mOfferAdapter = new PromoOfferAdapter(getBaseActivity(),couponList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvPromo.setLayoutManager(layoutManager);
        mBinding.rvPromo.setAdapter(mOfferAdapter);
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            int merchantId = bundle.getInt(GeneralConstant.MERCHANT_ID);
            getPresenter().viewMerchantCoupon(getDashboardActivity(), new MerchantCouponRequest(merchantId));
        }

    }

    @Override
    public void setListener() {
        mBinding.tvApply.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return OffersPromoFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvApply) {
            getBaseActivity().onBackPressed();
        }
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            MerchantCouponResponseData responseData = (MerchantCouponResponseData) response;
            if (CommonUtils.isNotNull(responseData) && CommonUtils.isNotNull(responseData.getInfo()) && responseData.getInfo().size() > 0) {
                couponList.clear();
                couponList.addAll(responseData.getInfo());
                mOfferAdapter.notifyDataSetChanged();
            }
        }
    }

    public static Fragment newInstance() {
        OffersPromoFragment fragment = new OffersPromoFragment();
        return fragment;
    }

    @Override
    public void onApplyClick(int position) {
        getBaseActivity().onBackPressed();
    }
}
