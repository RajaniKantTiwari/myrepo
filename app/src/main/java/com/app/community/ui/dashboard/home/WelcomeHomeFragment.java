package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentWelcomehomeBinding;
import com.app.community.databinding.LayoutImpPlaceBinding;
import com.app.community.databinding.LayoutLastOrderBinding;
import com.app.community.databinding.LayoutLatestNewsBinding;
import com.app.community.databinding.LayoutNewsBinding;
import com.app.community.databinding.LayoutOfferBinding;
import com.app.community.databinding.LayoutWelcomeSearchBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.HelpPlaceAdapter;
import com.app.community.ui.dashboard.home.adapter.LatestNewsAdapter;
import com.app.community.ui.dashboard.home.adapter.NewsAdapter;
import com.app.community.ui.dashboard.home.event.FragmentEvent;
import com.app.community.ui.dashboard.home.fragment.NewsFragment;
import com.app.community.utils.AddWelcomeChildView;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class WelcomeHomeFragment extends DashboardFragment implements NewsAdapter.NewsListener {

    private FragmentWelcomehomeBinding mBinding;
    private HelpPlaceAdapter mImpPlaceAdapter;
    private LatestNewsAdapter mLatestNewsAdapter;
    @Inject
    DashboardInsidePresenter presenter;
    private LayoutWelcomeSearchBinding mWelcomeBinding;
    private LayoutNewsBinding mNewsViewBinding;
    private LayoutOfferBinding mOfferBinding;
    private LayoutLastOrderBinding mLastOrderBinding;
    private LayoutImpPlaceBinding mImportantPlaceBinding;
    private LayoutLatestNewsBinding mLatestBinding;
    private NewsAdapter mNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_welcomehome,container,false);
        mWelcomeBinding=AddWelcomeChildView.addWelcomeSearchView(inflater,mBinding);
        mNewsViewBinding=AddWelcomeChildView.addNewsView(inflater,mBinding);
        mOfferBinding=AddWelcomeChildView.addOfferView(inflater,mBinding);
        mLastOrderBinding=AddWelcomeChildView.addLastOrderView(inflater,mBinding);
        mImportantPlaceBinding=AddWelcomeChildView.addImportantPlace(inflater,mBinding);
        mLatestBinding=AddWelcomeChildView.addLatestNewsView(inflater,mBinding);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager placeManager=new LinearLayoutManager(getContext());
        placeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImportantPlaceBinding.rvImportantPlace.setLayoutManager(placeManager);
        mImpPlaceAdapter=new HelpPlaceAdapter(getBaseActivity());
        mImportantPlaceBinding.rvImportantPlace.setAdapter(mImpPlaceAdapter);
        LinearLayoutManager latestNewsManager=new LinearLayoutManager(getBaseActivity());
        mLatestBinding.rvLatestNews.setLayoutManager(latestNewsManager);
        mLatestNewsAdapter=new LatestNewsAdapter(getBaseActivity());
        mLatestBinding.rvLatestNews.setAdapter(mLatestNewsAdapter);


        LinearLayoutManager newsManager=new LinearLayoutManager(getBaseActivity());
        mNewsViewBinding.rvNews.setLayoutManager(newsManager);
        mNewsAdapter=new NewsAdapter(getBaseActivity(),this);
        mNewsViewBinding.rvNews.setAdapter(mNewsAdapter);
    }



    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void setListener() {
        mLastOrderBinding.layoutLastOrder.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return WelcomeHomeFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void onClick(View view) {
      if(view==mLastOrderBinding.layoutLastOrder){
           addFragment(GeneralConstant.FRAGMENTS.HOME_FRAGMENT);
       }
    }

    private void addFragment(int fragmentId) {
        FragmentEvent event=new FragmentEvent(fragmentId,false,true);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        WelcomeHomeFragment fragment = new WelcomeHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void itemClick(int adapterPosition) {
        if (mFragmentNavigation != null) {
            mFragmentNavigation.pushFragment(NewsFragment.newInstance(mInt+1));
        }
        //addFragment(GeneralConstant.FRAGMENTS.NEWS_FRAGMENT);
    }
}
