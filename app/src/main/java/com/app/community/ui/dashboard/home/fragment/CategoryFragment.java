package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentCategoryBinding;
import com.app.community.databinding.FragmentFullInformationBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.event.NewsEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by rajnikant on 31/12/17.
 */

public class CategoryFragment extends DashboardFragment {
    private FragmentCategoryBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        CommonUtils.register(this);

        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.layoutPolitics.setOnClickListener(this);
        mBinding.layoutHealth.setOnClickListener(this);
        mBinding.layoutBusiness.setOnClickListener(this);
        mBinding.layoutSports.setOnClickListener(this);
        mBinding.layoutTravel.setOnClickListener(this);
        mBinding.layoutTechnology.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (mBinding.layoutPolitics == view) {

        }else if (mBinding.layoutHealth == view) {

        }else if (mBinding.layoutBusiness == view) {

        }else if (mBinding.layoutSports == view) {

        }else if (mBinding.layoutTravel == view) {

        }else if (mBinding.layoutTechnology == view) {

        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(NewsEvent event) {
        if (event.getRecentCategory() == GeneralConstant.RECENT) {
            mBinding.layoutCategory.setVisibility(View.GONE);
        } else if (event.getRecentCategory() == GeneralConstant.CATEGORY) {
            mBinding.layoutCategory.setVisibility(View.VISIBLE);

        }
    }
}
