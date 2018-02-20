/*
package com.app.community.ui.dashboard.home.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ProductDialogBinding;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater mInflator;
    private Activity mActivity;
    private HashMap<Marker, MerchantResponse> mMarkersHashMap;

    public MarkerInfoWindowAdapter(Activity activity, HashMap<Marker, MerchantResponse> mMarkersHashMap) {
        mActivity = activity;
        this.mMarkersHashMap = mMarkersHashMap;
        mInflator = LayoutInflater.from(mActivity);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ProductDialogBinding mBinding = DataBindingUtil.inflate(mInflator, R.layout.product_dialog, null, false);
        MerchantResponse response = mMarkersHashMap.get(marker);
        if (CommonUtils.isNotNull(response)) {
            GlideUtils.loadImage(mActivity, response.getImage(), mBinding.ivProduct, null, R.drawable.icon_placeholder);
            mBinding.setMerchantResponse(response);
            mBinding.tvTitle.setText(response.getName());
            mBinding.tvAddress.setText(response.getAddress());
            mBinding.tvType.setText(response.getCategory());
            mBinding.tvDistance.setText(CommonUtils.twoDecimalPlaceString(response.getDistance())+mActivity.getResources().getString(R.string.km));
        }
        return mBinding.getRoot();
    }
}
*/
