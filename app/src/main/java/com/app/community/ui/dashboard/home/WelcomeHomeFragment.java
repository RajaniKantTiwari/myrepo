package com.app.community.ui.dashboard.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.app.community.ui.dashboard.home.event.SearchProductEvent;
import com.app.community.ui.dashboard.home.fragment.HelpsAndSupportFragment;
import com.app.community.ui.dashboard.home.fragment.HomeFragment;
import com.app.community.ui.dashboard.home.fragment.MyOrderFragment;
import com.app.community.ui.dashboard.home.fragment.NewsFragment;
import com.app.community.ui.dashboard.home.fragment.NewsMainFragment;
import com.app.community.ui.dialogfragment.ContactImpPlaceDialogFragment;
import com.app.community.utils.AddWelcomeChildView;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;
import static com.app.community.utils.GeneralConstant.REQUEST_CALL;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class WelcomeHomeFragment extends DashboardFragment implements NewsAdapter.NewsListener,LatestNewsAdapter.LatestNewsListener,HelpPlaceAdapter.HelpListener,
        ContactImpPlaceDialogFragment.ContactDialogListener {

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
    private Intent callIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_welcomehome,container,false);
        CommonUtils.register(this);
        mWelcomeBinding=AddWelcomeChildView.addWelcomeSearchView(inflater,mBinding);
        mImportantPlaceBinding=AddWelcomeChildView.addImportantPlace(inflater,mBinding);
        mNewsViewBinding=AddWelcomeChildView.addNewsView(inflater,mBinding);
        mOfferBinding=AddWelcomeChildView.addOfferView(inflater,mBinding);
        mLastOrderBinding=AddWelcomeChildView.addLastOrderView(inflater,mBinding);
        mLatestBinding=AddWelcomeChildView.addLatestNewsView(inflater,mBinding);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager placeManager=new LinearLayoutManager(getContext());
        placeManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mImportantPlaceBinding.rvImportantPlace.setLayoutManager(placeManager);
        mImpPlaceAdapter=new HelpPlaceAdapter(getBaseActivity(),this);
        mImportantPlaceBinding.rvImportantPlace.setAdapter(mImpPlaceAdapter);
        LinearLayoutManager latestNewsManager=new LinearLayoutManager(getBaseActivity());
        mLatestBinding.rvLatestNews.setLayoutManager(latestNewsManager);
        mLatestNewsAdapter=new LatestNewsAdapter(getBaseActivity(),this);
        mLatestBinding.rvLatestNews.setAdapter(mLatestNewsAdapter);


        LinearLayoutManager newsManager=new LinearLayoutManager(getBaseActivity());
        mNewsViewBinding.rvNews.setLayoutManager(newsManager);
        mNewsAdapter=new NewsAdapter(getBaseActivity(),this);
        mNewsViewBinding.rvNews.setAdapter(mNewsAdapter);
    }



    @Override
    public void attachView() {
        if(CommonUtils.isNotNull(getActivityComponent())){
            getActivityComponent().inject(this);
            presenter.attachView(this);
        }
    }

    @Override
    public void setListener() {
        mWelcomeBinding.tvSearch.setOnClickListener(this);
        mLastOrderBinding.layoutLastOrder.setOnClickListener(this);
        mLastOrderBinding.layoutRating.setOnClickListener(this);
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
          mFragmentNavigation.pushFragment(ConfirmOrderFragment.newInstance(mInt+1));
       }else if(view==mWelcomeBinding.tvSearch){
          ExplicitIntent.getsInstance().navigateTo(getActivity(), SearchActivity.class);
      }else if(view==mLastOrderBinding.layoutRating){
          mFragmentNavigation.pushFragment(MyOrderFragment.newInstance(mInt+1));
      }
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
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mFragmentNavigation.pushFragment(NewsMainFragment.newInstance(mInt+1));
                }
            },100);
        }
    }

    @Override
    public void onItemClick(int adapterPosition) {
        mFragmentNavigation.pushFragment(NewsFragment.newInstance(mInt+1));
    }

    @Override
    public void itemHelpClicked(int adapterPosition) {
        Bundle bundle = new Bundle();
        CommonUtils.showContactImpDialog(getBaseActivity(), bundle, this);
    }

    @Override
    public void contact(String phoneNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getBaseActivity(),new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }


    @Override
    public void view(String message) {
        mFragmentNavigation.pushFragment(ProductDetailsFragment.newInstance(mInt+1));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    getBaseActivity().showToast(getResources().getString(R.string.permition_denied));
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSearchProduct(SearchProductEvent event){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentNavigation.pushFragment(HomeFragment.newInstance(mInt+1));
            }
        },100);

    }
}
