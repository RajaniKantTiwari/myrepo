package com.app.community.ui.dashboard.home.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ProductDialogBinding;
import com.app.community.network.response.dashboard.feed.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final LayoutInflater mInflator;
    private Activity mActivity;
    private HashMap<Marker, MerchantResponse> mMarkersHashMap;
    public MarkerInfoWindowAdapter(Activity activity, HashMap<Marker, MerchantResponse> mMarkersHashMap)
    {
        mActivity = activity;
        this.mMarkersHashMap=mMarkersHashMap;
        mInflator=LayoutInflater.from(mActivity);
    }

    @Override
    public View getInfoWindow(Marker marker)
    {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
       ProductDialogBinding mBinding= DataBindingUtil.inflate(mInflator, R.layout.product_dialog,null,false);
        MerchantResponse mettingResponse = mMarkersHashMap.get(marker);
        mBinding.setMerchantResponse(mettingResponse);
        if(CommonUtils.isNotNull(mettingResponse)&&!mettingResponse.getId().equalsIgnoreCase("0")){
            mBinding.layoutDate.setVisibility(View.VISIBLE);
            mBinding.tvLocation.setText(mettingResponse.getAddress());
        }else{
            try {
                mBinding.layoutDate.setVisibility(View.GONE);
                Geocoder geocoder = new Geocoder(mActivity, Locale.getDefault());
                mBinding.tvTitle.setText(mActivity.getResources().getString(R.string.current_location));
                // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(mettingResponse.getLatitude()),
                        Double.parseDouble(mettingResponse.getLongitude()), 1);
                String address = addresses.get(0).getAddressLine(0);
                if(CommonUtils.isNotNull(address)){
                   if(address.length()> GeneralConstant.ADDRESS_MAX_LENGTH) {
                       mBinding.tvLocation.setText(address.substring(0, GeneralConstant.ADDRESS_MAX_LENGTH));
                   }else {
                       mBinding.tvLocation.setText(address);
                   }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return mBinding.getRoot();
    }
}
