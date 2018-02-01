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
import com.app.community.databinding.FragmentOfferBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.offer.OfferType;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.offer.adapter.OfferAdapter;
import com.app.community.ui.dashboard.offer.adapter.OfferTypesAdapter;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by ashok on 13/11/17.
 */

public class OfferFragment extends DashboardFragment implements OfferTypesAdapter.OfferTypeListener {

    @Inject
    CommonPresenter presenter;
    private FragmentOfferBinding mBinding;
    private OfferAdapter mOfferAdapter;
    private OfferTypesAdapter mOfferTypeAdapter;
    private ArrayList<OfferType> offerTypeList;

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        OfferFragment fragment = new OfferFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_offer, container, false);
        getDashboardActivity().setTile(getString(R.string.offer));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        mOfferAdapter = new OfferAdapter(getDashboardActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvOffer.setLayoutManager(layoutManager);
        mBinding.rvOffer.setAdapter(mOfferAdapter);
        offerTypeList = new ArrayList<>();
        setOfferList(offerTypeList);
        mOfferTypeAdapter = new OfferTypesAdapter(getDashboardActivity(),offerTypeList , this);
        LinearLayoutManager offerTypeManager = new LinearLayoutManager(getDashboardActivity());
        offerTypeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvOfferType.setLayoutManager(offerTypeManager);
        mBinding.rvOfferType.setAdapter(mOfferTypeAdapter);
    }

    private void setOfferList(ArrayList<OfferType> offerTypeList) {
        OfferType type1 = new OfferType();
        type1.setSelected(true);
        type1.setOfferType(getString(R.string.fab_deal));
        OfferType type2 = new OfferType();
        type2.setOfferType(getString(R.string.food_drinks));
        OfferType type3 = new OfferType();
        type3.setOfferType(getString(R.string.spas));
        OfferType type4 = new OfferType();
        type4.setOfferType(getString(R.string.saloons));
        OfferType type5 = new OfferType();
        type5.setOfferType(getString(R.string.jewellery));
        OfferType type6 = new OfferType();
        type6.setOfferType(getString(R.string.fab_deal));
        OfferType type7 = new OfferType();
        type7.setOfferType(getString(R.string.food_drinks));
        offerTypeList.add(type1);
        offerTypeList.add(type2);
        offerTypeList.add(type3);
        offerTypeList.add(type4);
        offerTypeList.add(type5);
        offerTypeList.add(type6);
        offerTypeList.add(type7);
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

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
        } else {
            CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onOfferTypeItemClicked(int position) {
        for (int i = 0; i < offerTypeList.size(); i++) {
            OfferType type = offerTypeList.get(i);
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
