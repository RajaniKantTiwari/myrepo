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
import com.app.community.event.NewsEventDetail;
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
import com.emoiluj.doubleviewpager.HorizontalViewPager;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsMainFragment extends DashboardFragment {
    private FragmentMainNewsBinding mBinding;
    private ArrayList<News> newsList;
    private int position;
    private FirstPagerAdapter firstPagerAdapter;
    private SecondPagerAdapter secondPager;
    private ThirdPagerAdapter thirdPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_news, container, false);
        CommonUtils.register(this);
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

        mBinding.viewPager.setOnPageChangeListener(new HorizontalViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==2){
                    //thirdPagerAdapter.setWebUrl("http://oimedia.in/index.php/author/webmaster/");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void generateVerticalAdapters(ArrayList<PagerAdapter> verticalAdapters) {
        for (int i = 0; i < AppConstants.HORIZONTAL_CHILD; i++) {
            if (i == 0) {
                firstPagerAdapter=new FirstPagerAdapter(getContext());
                verticalAdapters.add(firstPagerAdapter);
            } else if (i == 1) {
                secondPager=new SecondPagerAdapter(getContext(), newsList);
                mBinding.viewPager.setVerticalScrollbarPosition(2);
                verticalAdapters.add(secondPager);
            } else if (i == 2) {
                thirdPagerAdapter=new ThirdPagerAdapter(getContext());
                verticalAdapters.add(thirdPagerAdapter);
            }
        }
    }

    @Override
    public void onDestroy() {
        CommonUtils.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onNewsWebEvent(NewsEventDetail event) {
        thirdPagerAdapter.setWebUrl(event.getUrl());
        mBinding.viewPager.setCurrentItem(2);
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