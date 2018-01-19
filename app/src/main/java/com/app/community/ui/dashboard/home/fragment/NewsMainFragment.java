package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentMainNewsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.NewsPagerAdapter;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsMainFragment extends DashboardFragment {
    private FragmentMainNewsBinding mBinding;
    private NewsPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_news, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        getDashboardActivity().showToast("NewsMainFragment called");
        pagerAdapter=new NewsPagerAdapter(getDashboardActivity().getSupportFragmentManager());
        mBinding.viewPager.setAdapter(pagerAdapter);
        mBinding.viewPager.setCurrentItem(1);
    }

    @Override
    public void setListener() {
    }

    @Override
    public String getFragmentName() {
        return NewsMainFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }



    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        NewsMainFragment fragment = new NewsMainFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
