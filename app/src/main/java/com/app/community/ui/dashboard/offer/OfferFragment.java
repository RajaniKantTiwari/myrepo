package com.app.community.ui.dashboard.offer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.community.R;
import com.app.community.databinding.FragmentOfferBinding;
import com.app.community.network.request.dashboard.MerchantOfferRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.coupon.ViewAllCouponResponseData;
import com.app.community.network.response.dashboard.home.MerchantCategory;
import com.app.community.network.response.dashboard.home.MerchantCategoryData;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.network.response.dashboard.offer.MerchantOffer;
import com.app.community.network.response.dashboard.offer.MerchantOfferData;
import com.app.community.network.response.dashboard.offer.OfferType;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.offer.adapter.OfferAdapter;
import com.app.community.ui.dashboard.offer.adapter.OfferTypesAdapter;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class OfferFragment extends DashboardFragment implements
        OfferTypesAdapter.OfferTypeListener, OfferAdapter.OfferListener {

    @Inject
    CommonPresenter presenter;
    private FragmentOfferBinding mBinding;
    private OfferAdapter mOfferAdapter;
    private OfferTypesAdapter mOfferTypeAdapter;
    private ArrayList<MerchantCategory> offerTypeList;
    private ArrayList<MerchantOffer> offersList;
    private int selectedPosition;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_offer, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.offer));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        offersList = new ArrayList<>();
        mOfferAdapter = new OfferAdapter(getDashboardActivity(), offersList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvOffer.setLayoutManager(layoutManager);
        mBinding.rvOffer.setAdapter(mOfferAdapter);
        offerTypeList = new ArrayList<>();
        mOfferTypeAdapter = new OfferTypesAdapter(getDashboardActivity(), offerTypeList, this);
        LinearLayoutManager offerTypeManager = new LinearLayoutManager(getDashboardActivity());
        offerTypeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvOfferType.setLayoutManager(offerTypeManager);
        mBinding.rvOfferType.setAdapter(mOfferTypeAdapter);
        getPresenter().viewAllCoupon(getDashboardActivity());
        getPresenter().getMerchantCategory(getDashboardActivity());
    }


    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return OfferFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            /*if (requestCode == 1) {
                ViewAllCouponResponseData responseData = (ViewAllCouponResponseData) response;
                if (CommonUtils.isNotNull(responseData) && CommonUtils.isNotNull(responseData.getInfo()) && responseData.getInfo().size() > 0) {
                    CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
                    offersList.clear();
                    offersList.addAll(responseData.getInfo());
                    mOfferAdapter.notifyDataSetChanged();
                } else {
                    CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
                }
            } else*/ if (requestCode == 2) {
                showOfferCategory(response);
            } else if (requestCode == 3) {
                showOffer(response);
            }

        }
    }

    private void showOffer(BaseResponse response) {
        MerchantOfferData offerData=(MerchantOfferData)response;
        if(CommonUtils.isNotNull(offerData.getData())&&offerData.getData().size()>0){
            CommonUtils.setVisibility(mBinding.rvOffer, mBinding.layoutNoData.layoutNoData, true);
            offersList.addAll(offerData.getData());
        }else {
            CommonUtils.setVisibility(mBinding.rvOffer, mBinding.layoutNoData.layoutNoData, false);
        }
    }

    private void showOfferCategory(BaseResponse response) {
        MerchantCategoryData data = (MerchantCategoryData) response;
        if (CommonUtils.isNotNull(data.getInfo()) && data.getInfo().size() > 0) {
            offerTypeList.clear();
            offerTypeList.addAll(data.getInfo());
            MerchantCategory category = offerTypeList.get(0);
            selectedPosition = 0;
            callOfferData(category);
            category.setSelected(true);
            offerTypeList.set(0, category);
            mOfferTypeAdapter.notifyDataSetChanged();
        }
    }

    private void callOfferData(MerchantCategory category) {
        MerchantOfferRequest request = new MerchantOfferRequest();
        request.setCategory_id(category.getCategory_id());
        request.setCategory_name(category.getCategory_name());
        getPresenter().getMerchantOffer(getDashboardActivity(), request);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOfferTypeItemClicked(int position) {
        if (selectedPosition != position) {
            selectedPosition=position;
            callOfferData(offerTypeList.get(position));
            for (int i = 0; i < offerTypeList.size(); i++) {
                MerchantCategory type = offerTypeList.get(i);
                if (i == position) {
                    type.setSelected(true);
                } else {
                    type.setSelected(false);
                }
                offerTypeList.set(i, type);
            }
            mOfferTypeAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onOfferClicked(int position) {
        if (CommonUtils.isNotNull(offersList) && offersList.size() > position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.OFFER, offersList.get(position));
            ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), OfferDetailsActivity.class, bundle);
        }
    }
}
