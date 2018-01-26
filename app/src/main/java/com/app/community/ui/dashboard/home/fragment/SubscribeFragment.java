package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentSubscribeBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.newspaper.event.SubscriptionEvent;
import com.app.community.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.app.community.utils.GeneralConstant.SUBSCRIPTION;
import static com.app.community.utils.GeneralConstant.SUBSCRIPTION_DETAIL;

/**
 * Created by rajnikant on 31/12/17.
 */

public class SubscribeFragment extends DashboardFragment {
    private FragmentSubscribeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_subscribe, container, false);
        CommonUtils.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mBinding.tvSubscribe.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return SubscribeFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubscribe) {
            EventBus.getDefault().post(new SubscriptionEvent(SUBSCRIPTION_DETAIL));
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);

    }
    @Subscribe
    public void onEvent(SubscriptionEvent event) {
        if (event.getShow() == SUBSCRIPTION) {
            mBinding.layoutSubscribe.setVisibility(View.VISIBLE);
        } else if (event.getShow() == SUBSCRIPTION_DETAIL) {
            mBinding.layoutSubscribe.setVisibility(View.GONE);
        }
    }
    public static Fragment newInstance() {
        return new SubscribeFragment();
    }
}
