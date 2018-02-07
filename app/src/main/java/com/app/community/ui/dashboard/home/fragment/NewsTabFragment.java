package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsTabBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.event.NewsEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsTabFragment extends DashboardFragment {
    private FragmentNewsTabBinding mBinding;
    private NewsEvent event;
    public static NewsTabFragment newInstance(){
        return new NewsTabFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_tab, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        event=new NewsEvent();
        event.setRecentCategory(GeneralConstant.RECENT);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.CATEGORY_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.RECENT_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        EventBus.getDefault().post(event);
    }

    @Override
    public void setListener() {
        mBinding.tvRecent.setOnClickListener(this);
        mBinding.tvCategory.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return NewsTabFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvRecent) {
           recent();
        } else if (view == mBinding.tvCategory) {
            category();
        }
    }

    private void category() {
        mBinding.tvRecent.setTextColor(CommonUtils.getColor(getContext(),R.color.dark_black));
        mBinding.tvCategory.setTextColor(CommonUtils.getColor(getContext(),R.color.color_dark_sky));
        event.setRecentCategory(GeneralConstant.CATEGORY);
        EventBus.getDefault().post(event);

    }

    private void recent() {
        mBinding.tvRecent.setTextColor(CommonUtils.getColor(getContext(),R.color.color_dark_sky));
        mBinding.tvCategory.setTextColor(CommonUtils.getColor(getContext(),R.color.dark_black));
        event.setRecentCategory(GeneralConstant.RECENT);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
