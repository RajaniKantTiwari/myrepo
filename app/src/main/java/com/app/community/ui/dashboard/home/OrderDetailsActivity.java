package com.app.community.ui.dashboard.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityOrderDetailsBinding;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.OrderDetailsRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.network.response.dashboard.OrderDetailData;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.dashboard.home.adapter.OrderDetailsAdapter;
import com.app.community.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsActivity extends CommonActivity implements OrderListAdapter.OrderListener {

    private ActivityOrderDetailsBinding mBinding;
    private OrderDetailsAdapter mOrderAdapter;
    private Order order;
    @Inject
    CommonPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        initializeData();
        setListener();
    }




    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.order_details));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rvCartItem.setLayoutManager(manager);
        mOrderAdapter = new OrderDetailsAdapter(this);
        mBinding.rvCartItem.setAdapter(mOrderAdapter);
        Intent intent=getIntent();
        if(CommonUtils.isNotNull(intent)){
            Bundle bundle = intent.getExtras();
            if (CommonUtils.isNotNull(bundle)) {
                order = bundle.getParcelable(GeneralConstant.ORDER_DETAILS);
                mBinding.tvOrderDate.setText(order.getInvoiceDate());
                mBinding.tvInvoice.setText(order.getInvoiceNumber());
                mBinding.tvOrderStatus.setText(order.getOrder_status());
                OrderDetailsRequest request = new OrderDetailsRequest();
                request.setOrderid(order.getInvoiceNumber());
                presenter.orderDetails(this, request);
                MerchantRequest merchantRequest = new MerchantRequest(order.getMerchant_id());
                presenter.getMerchantDetails(this, merchantRequest);
            }
        }

    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }



    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == 1) {
                orderDetails(response);
            } else if (requestCode == 2) {
                merchantDetails(response);
            }
        }
    }

    private void merchantDetails(BaseResponse response) {
        MerchantResponseData data = (MerchantResponseData) response;
        ArrayList<MerchantResponse> merchantResponse = data.getInfo();
        if (CommonUtils.isNotNull(merchantResponse) && merchantResponse.size() > 0) {
            MerchantResponse merchant = merchantResponse.get(0);
            GlideUtils.loadImage(this, merchant.getImage(), mBinding.storeImage, null, R.drawable.icon_placeholder);
            mBinding.tvStoreName.setText(merchant.getName());
            mBinding.tvAddress.setText(merchant.getAddress());
        }
    }

    private void orderDetails(BaseResponse response) {
        OrderDetailData orderDetailData = (OrderDetailData) response;
        if (CommonUtils.isNotNull(orderDetailData.getOrderdetails()) && orderDetailData.getOrderdetails().size() > 0) {
            mOrderAdapter.setCartList(orderDetailData.getOrderdetails());

        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        }
    }


    @Override
    public void onOrderClick(int position) {

    }
}
