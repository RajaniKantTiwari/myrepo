package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityMyOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.MyOrder;
import com.app.community.network.response.MyOrderData;
import com.app.community.network.response.Order;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by rajnikant on 31/12/17.
 */

public class MyOrderActivity extends CommonActivity {
    private ActivityMyOrderBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private ArrayList<Order> recentOrderList;
    private ArrayList<Order> pastOrderList;
    private LiveOrderFragment liveOrderFragment;
    private PastOrderFragment pastOrderFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        initializeData();
        setListener();
    }

    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.my_order));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        liveOrderFragment = new LiveOrderFragment();
        pastOrderFragment = new PastOrderFragment();
        pushFragment(pastOrderFragment, null, R.id.container, true, true, BaseActivity.AnimationType.NONE);
        pushFragment(liveOrderFragment, null, R.id.container, true, true, BaseActivity.AnimationType.NONE);
        presenter.getMyOrder(this);
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.layoutLiveOrder.setOnClickListener(this);
        mBinding.layoutPastOrder.setOnClickListener(this);
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public CommonPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutLiveOrder) {
            liveOrder();
        } else if (view == mBinding.layoutPastOrder) {
            pastOrder();
        } else if (mBinding.layoutHeader.ivBack == view) {
            finish();
        }
    }

    private void pastOrder() {
        mBinding.tvLiveOrder.setTextColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.tvPastOrder.setTextColor(CommonUtils.getColor(this, R.color.color_dark_grey));
        mBinding.ivPastOrder.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black_color));
        mBinding.ivLiveOrder.setBackgroundColor(CommonUtils.getColor(this, R.color.ver_bg_color));
        pastOrderFragment.setPastOrder(pastOrderList);
        liveOrderFragment.setVisibility(View.GONE);
        pastOrderFragment.setVisibility(View.VISIBLE);

    }

    private void liveOrder() {
        mBinding.tvLiveOrder.setTextColor(CommonUtils.getColor(this, R.color.color_dark_grey));
        mBinding.tvPastOrder.setTextColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.ivLiveOrder.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black_color));
        mBinding.ivPastOrder.setBackgroundColor(CommonUtils.getColor(this, R.color.ver_bg_color));
        liveOrderFragment.setLiveOrder(recentOrderList);
        liveOrderFragment.setVisibility(View.VISIBLE);
        pastOrderFragment.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response) && response instanceof MyOrderData) {
            MyOrderData data = (MyOrderData) response;
            MyOrder myOrder = data.getMyorder();
            if (CommonUtils.isNotNull(myOrder)) {
                recentOrderList = myOrder.getRecent();
                pastOrderList = myOrder.getPast();
                pastOrderFragment.setPastOrder(pastOrderList);
                liveOrderFragment.setLiveOrder(recentOrderList);

            }
        }
    }

    public void addFragmentInContainer(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        pushFragment(fragment, bundle, android.R.id.content, addToBackStack, shouldAdd, animationType);
    }

    public CommonPresenter getOrderPresenter() {
      return presenter;
    }
}
