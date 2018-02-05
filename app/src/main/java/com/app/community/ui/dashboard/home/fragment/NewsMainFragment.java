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
import com.app.community.network.response.dashboard.home.News;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.viewpager.BTFragmentGridPager;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsMainFragment extends DashboardFragment {
    private FragmentMainNewsBinding mBinding;
    private BTFragmentGridPager.FragmentGridPagerAdapter mFragmentGridPagerAdapter;
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


        mFragmentGridPagerAdapter = new BTFragmentGridPager.FragmentGridPagerAdapter() {
            @Override
            public int rowCount() {
                return 10;
            }

            @Override
            public int columnCount(int row) {
                return CommonUtils.isNotNull(newsList) ? newsList.size() : 0;
            }

            @Override
            public Fragment getItem(BTFragmentGridPager.GridIndex index) {
                NewsFragment newsFragment = new NewsFragment();
                Bundle bundle=new Bundle();
                bundle.putParcelable(GeneralConstant.NEWS,newsList.get(index.getCol()));
                newsFragment.setArguments(bundle);
                newsFragment.setGridIndex(index);
                return newsFragment;
            }
        };
        mBinding.fragmentGridPager.setGridPagerAdapter(mFragmentGridPagerAdapter);
        mBinding.fragmentGridPager.setCurrentItem(position);
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