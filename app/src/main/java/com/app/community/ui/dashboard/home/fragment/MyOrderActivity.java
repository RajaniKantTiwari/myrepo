package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityMyOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.home.event.MyOrderEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by rajnikant on 31/12/17.
 */

public class MyOrderActivity extends CommonActivity {
    private ActivityMyOrderBinding mBinding;
    private MyOrderEvent event;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_my_order);
        initializeData();
        setListener();
    }
    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.offer));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this,R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);

        event=new MyOrderEvent();
        event.setLivePastOrder(GeneralConstant.LIVEORDER);

        pushFragment( GeneralConstant.FRAGMENTS.LIVEORDER_FRAGMENT,null,R.id.container,false,false,BaseActivity.AnimationType.NONE);
        pushFragment( GeneralConstant.FRAGMENTS.PASTORDER_FRAGMENT,null,R.id.container,false,false,BaseActivity.AnimationType.NONE);
        EventBus.getDefault().post(event);
    }

    
    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.layoutLiveOrder.setOnClickListener(this);
        mBinding.layoutPastOrder.setOnClickListener(this);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutLiveOrder) {
           liveOrder();
        } else if (view == mBinding.layoutPastOrder) {
            pastOrder();
        }else if(mBinding.layoutHeader.ivBack==view){
            finish();
        }
    }

    private void pastOrder() {
        mBinding.tvLiveOrder.setTextColor(CommonUtils.getColor(this,R.color.dark_black));
        mBinding.tvPastOrder.setTextColor(CommonUtils.getColor(this,R.color.color_dark_grey));
        mBinding.ivPastOrder.setBackgroundColor(CommonUtils.getColor(this,R.color.dark_black_color));
        mBinding.ivLiveOrder.setBackgroundColor(CommonUtils.getColor(this,R.color.ver_bg_color));
        mBinding.ivLiveOrder.setVisibility(View.VISIBLE);
        mBinding.ivPastOrder.setVisibility(View.INVISIBLE);
        event.setLivePastOrder(GeneralConstant.PASTORDER);
        EventBus.getDefault().post(event);

    }

    private void liveOrder() {
        mBinding.tvLiveOrder.setTextColor(CommonUtils.getColor(this,R.color.color_dark_grey));
        mBinding.tvPastOrder.setTextColor(CommonUtils.getColor(this,R.color.dark_black));
        mBinding.ivLiveOrder.setBackgroundColor(CommonUtils.getColor(this,R.color.dark_black_color));
        mBinding.ivPastOrder.setBackgroundColor(CommonUtils.getColor(this,R.color.ver_bg_color));
        mBinding.ivLiveOrder.setVisibility(View.INVISIBLE);
        mBinding.ivPastOrder.setVisibility(View.VISIBLE);
        event.setLivePastOrder(GeneralConstant.LIVEORDER);
        EventBus.getDefault().post(event);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
