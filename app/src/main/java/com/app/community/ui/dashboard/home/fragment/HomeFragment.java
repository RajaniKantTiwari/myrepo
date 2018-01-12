package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentHomeBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardPresenter;
import com.app.community.ui.dashboard.home.event.ProductEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by Amul on 13/11/17.
 */
public class HomeFragment extends DashboardFragment {

    @Inject
    DashboardPresenter presenter;
    private ProductEvent event;
    FragmentHomeBinding mBinding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        addFragment();
        return mBinding.getRoot();
    }

    private void addFragment() {
        mBinding.listButton.setTag(R.drawable.ic_location);
        mBinding.listButton.setVisibility(View.VISIBLE);
        event = new ProductEvent();
        event.setListMap(GeneralConstant.LIST_PRODUCT);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.PRODUCT_MAP_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.PRODUCT_LIST,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
    }


    @Override
    public void initializeData() {
        getPresenter().getMerchantList(getBaseActivity());
    }

    @Override
    public void setListener() {
        mBinding.listButton.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        if (view == mBinding.listButton) {
            if ((int) mBinding.listButton.getTag() == R.drawable.ic_location) {
                mBinding.listButton.setImageResource(R.drawable.ic_list);
                mBinding.listButton.setTag(R.drawable.ic_list);
            } else {
                mBinding.listButton.setImageResource(R.drawable.ic_location);
                mBinding.listButton.setTag(R.drawable.ic_location);
            }
            CommonUtils.clicked(mBinding.listButton);
            listMapConversion();
        }
    }


    private void listMapConversion() {
        if (event.getListMap() == GeneralConstant.LIST_PRODUCT) {
            event.setListMap(GeneralConstant.MAP_PRODUCT);
        } else {
            event.setListMap(GeneralConstant.LIST_PRODUCT);
        }
        EventBus.getDefault().post(event);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (response instanceof ProductResponseData) {
                ProductResponseData meetingEventResponseData = (ProductResponseData) response;
                if (CommonUtils.isNotNull(meetingEventResponseData)) {
                    event.setProductList(meetingEventResponseData.getInfo());
                    EventBus.getDefault().post(event);
                }
            }
        }


    }

}
