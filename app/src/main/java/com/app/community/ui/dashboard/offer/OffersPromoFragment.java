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
import com.app.community.event.CouponEvent;
import com.app.community.network.request.dashboard.CheckCouponRequest;
import com.app.community.network.request.dashboard.MerchantCouponRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.coupon.MerchantCouponResponseData;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.offer.adapter.PromoOfferAdapter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class OffersPromoFragment extends DashboardFragment implements PromoOfferAdapter.PromoListener {
    private FragmentOfferPromoBinding mBinding;
    private PromoOfferAdapter mOfferAdapter;
    private ArrayList<Offer> offersList;
    private int merchantId = 6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_offer_promo, container, false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        offersList = new ArrayList<>();
        mOfferAdapter = new PromoOfferAdapter(getBaseActivity(), offersList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvPromo.setLayoutManager(layoutManager);
        mBinding.rvPromo.setAdapter(mOfferAdapter);
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            //merchantId = bundle.getInt(GeneralConstant.MERCHANT_ID);
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
            if (requestCode == 1) {
                MerchantCouponResponseData responseData = (MerchantCouponResponseData) response;
                if (CommonUtils.isNotNull(responseData) && CommonUtils.isNotNull(responseData.getInfo()) && responseData.getInfo().size() > 0) {
                    offersList.clear();
                    offersList.addAll(responseData.getInfo());
                    mOfferAdapter.notifyDataSetChanged();
                }
            } else if (requestCode == 2) {
                getDashboardActivity().showToast(response.getMsg());
                if (AppConstants.SUCCESS.equalsIgnoreCase(response.getStatus())) {
                    EventBus.getDefault().post(new CouponEvent());
                    getBaseActivity().onBackPressed();
                }
            }

        }
    }

    public static Fragment newInstance() {
        OffersPromoFragment fragment = new OffersPromoFragment();
        return fragment;
    }

    @Override
    public void onApplyClick(int position) {
        getPresenter().checkCoupon(getDashboardActivity(), new CheckCouponRequest(merchantId, offersList.get(position).getId()));

    }

    @Override
    public void onCouponDetailClick(int position) {
        if (CommonUtils.isNotNull(offersList) && offersList.size() > position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.OFFER, offersList.get(position));
            ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), OfferDetailsActivity.class, bundle);
        }
    }
}
