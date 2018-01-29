package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.VerticlePagerAdapter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsFragment extends DashboardFragment  {
    private FragmentNewsBinding mBinding;
    private ArrayList<News> newsList;
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle=getArguments();
        if(CommonUtils.isNotNull(bundle)){
            newsList=bundle.getParcelableArrayList(GeneralConstant.NEWSLIST);
            position=bundle.getInt(GeneralConstant.POSITION);
        }
        mBinding.ivPager.setAdapter(new VerticlePagerAdapter(getContext(),newsList,position));
        mBinding.ivPager.setCurrentItem(position);
    }

    @Override
    public void setListener() {
    }

    @Override
    public String getFragmentName() {
        return NewsFragment.class.getSimpleName();
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

    public static Fragment newInstance(int instance, Bundle bundle) {
        bundle.putInt(ARGS_INSTANCE, instance);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
