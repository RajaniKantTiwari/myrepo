package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentMainNewsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.FirstPagerAdapter;
import com.app.community.ui.dashboard.home.adapter.SecondPagerAdapter;
import com.app.community.ui.dashboard.home.adapter.ThirdPagerAdapter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.emoiluj.doubleviewpager.DoubleViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsMainFragment extends DashboardFragment {
    private FragmentMainNewsBinding mBinding;
    private ArrayList<News> newsList;
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_news, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            newsList = bundle.getParcelableArrayList(GeneralConstant.NEWSLIST);
            position = bundle.getInt(GeneralConstant.POSITION);
        }
        ArrayList<PagerAdapter> verticalAdapters = new ArrayList<>();
        generateVerticalAdapters(verticalAdapters);
        mBinding.viewPager.setAdapter(new DoubleViewPagerAdapter(getContext(), verticalAdapters));
        mBinding.viewPager.setCurrentItem(1);
    }

    private void generateVerticalAdapters(ArrayList<PagerAdapter> verticalAdapters) {
        for (int i = 0; i < AppConstants.HORIZONTAL_CHILD; i++) {
            if (i == 0) {
                verticalAdapters.add(new FirstPagerAdapter(getContext(), i, 1));
            } else if (i == 1) {
                verticalAdapters.add(new SecondPagerAdapter(getContext(), newsList));
            } else if (i == 2) {
                verticalAdapters.add(new ThirdPagerAdapter(getContext(), i, 1));
            }
        }
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

}