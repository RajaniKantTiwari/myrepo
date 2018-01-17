package com.app.community.utils;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;

import com.app.community.R;
import com.app.community.databinding.FragmentWelcomehomeBinding;
import com.app.community.databinding.LayoutImpPlaceBinding;
import com.app.community.databinding.LayoutLastOrderBinding;
import com.app.community.databinding.LayoutLatestNewsBinding;
import com.app.community.databinding.LayoutNewsBinding;
import com.app.community.databinding.LayoutOfferBinding;
import com.app.community.databinding.LayoutWelcomeSearchBinding;

/**
 * Created by rajnikant on 03/01/18.
 */

public class AddWelcomeChildView {

    public static LayoutWelcomeSearchBinding addWelcomeSearchView(LayoutInflater inflater, FragmentWelcomehomeBinding mBinding) {
        LayoutWelcomeSearchBinding binding=DataBindingUtil.inflate(inflater,R.layout.layout_welcome_search,null,false);
        mBinding.layoutHo.addView(binding.getRoot());
        return binding;
    }

    public static LayoutNewsBinding addNewsView(LayoutInflater inflater,FragmentWelcomehomeBinding mBinding) {
        LayoutNewsBinding binding=DataBindingUtil.inflate(inflater,R.layout.layout_news,null,false);
        mBinding.layoutHo.addView(binding.getRoot());
        return binding;
    }

    public static LayoutOfferBinding addOfferView(LayoutInflater mInflator, FragmentWelcomehomeBinding mBinding) {
        LayoutOfferBinding binding=DataBindingUtil.inflate(mInflator,R.layout.layout_offer,null,false);
        mBinding.layoutHome.addView(binding.getRoot());
        return binding;
    }

    public static LayoutLastOrderBinding addLastOrderView(LayoutInflater mInflator, FragmentWelcomehomeBinding mBinding) {
        LayoutLastOrderBinding binding=DataBindingUtil.inflate(mInflator,R.layout.layout_last_order,null,false);
        mBinding.layoutHome.addView(binding.getRoot());
        return binding;
    }
    public static LayoutImpPlaceBinding addImportantPlace(LayoutInflater mInflator, FragmentWelcomehomeBinding mBinding) {
        LayoutImpPlaceBinding binding=DataBindingUtil.inflate(mInflator,R.layout.layout_imp_place,null,false);
        mBinding.layoutHome.addView(binding.getRoot());
        return binding;
    }
    public static LayoutLatestNewsBinding addLatestNewsView(LayoutInflater mInflator, FragmentWelcomehomeBinding mBinding) {
        LayoutLatestNewsBinding binding=DataBindingUtil.inflate(mInflator,R.layout.layout_latest_news,null,false);
        mBinding.layoutHome.addView(binding.getRoot());
        return binding;
    }
}
