package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsRowBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.viewpager.BTFragmentGridPager;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsFragment extends DashboardFragment  {
    private FragmentNewsRowBinding mBinding;
    private News news;
    private BTFragmentGridPager.GridIndex mGridIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_row, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle=getArguments();
        if(CommonUtils.isNotNull(bundle)){
            news=bundle.getParcelable(GeneralConstant.NEWS);
        }
        if (CommonUtils.isNotNull(news)) {
            GlideUtils.loadImage(getDashboardActivity(), news.getDisplay_image(), mBinding.ivNews, null, R.drawable.background_placeholder);
            mBinding.setNews(news);
        }
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
    public void setGridIndex(BTFragmentGridPager.GridIndex gridIndex){
        mGridIndex = gridIndex;
    }
}
