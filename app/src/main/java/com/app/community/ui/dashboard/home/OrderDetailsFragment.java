package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderDetailsBinding;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.OrderDetailsRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.network.response.dashboard.OrderData;
import com.app.community.network.response.dashboard.OrderDetailData;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.home.adapter.OrderDetailsAdapter;
import com.app.community.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.community.ui.dashboard.home.fragment.MyOrderActivity;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsFragment extends BaseFragment implements OrderListAdapter.OrderListener {

    private FragmentOrderDetailsBinding mBinding;
    private OrderDetailsAdapter mOrderAdapter;
    private FragmentActivity activity;
    private Order order;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false);
        activity = getActivity();
        //getDashboardActivity().setHeaderTitle(getString(R.string.order_details));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.order_details));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(getContext(), R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);

        mOrderAdapter = new OrderDetailsAdapter(getBaseActivity());
        mBinding.rvCartItem.setAdapter(mOrderAdapter);
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            order = bundle.getParcelable(GeneralConstant.ORDER_DETAILS);
            mBinding.tvOrderDate.setText(order.getInvoiceDate());
            mBinding.tvInvoice.setText(order.getInvoiceNumber());
            OrderDetailsRequest request = new OrderDetailsRequest();
            request.setOrderid(order.getInvoiceNumber());
            ((MyOrderActivity) getBaseActivity()).getOrderPresenter().orderDetails(getActivity(), request);
            MerchantRequest merchantRequest = new MerchantRequest(order.getMerchant_id());
            ((MyOrderActivity) getBaseActivity()).getOrderPresenter().getMerchantDetails(getActivity(), merchantRequest);
        }
    }

    @Override
    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return OrderDetailsFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        ((MyOrderActivity) getBaseActivity()).getOrderPresenter().attachView(this);
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
        MerchantResponseData data=(MerchantResponseData)response;
        ArrayList<MerchantResponse> merchantResponse = data.getInfo();
        if(CommonUtils.isNotNull(merchantResponse)&&merchantResponse.size()>0){
            MerchantResponse merchant = merchantResponse.get(0);
            GlideUtils.loadImage(getBaseActivity(),merchant.getImage(),mBinding.storeImage,null,R.drawable.icon_placeholder);
            mBinding.tvStoreName.setText(merchant.getName());
            mBinding.tvAddress.setText(merchant.getAddress());
        }
    }

    private void orderDetails(BaseResponse response) {
        OrderDetailData orderDetailData=(OrderDetailData)response;
        if(CommonUtils.isNotNull(orderDetailData.getOrderdetails())&&orderDetailData.getOrderdetails().size()>0){
            mOrderAdapter.setCartList(orderDetailData.getOrderdetails());

        }
    }
    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            activity.onBackPressed();
        }
    }


    @Override
    public void onOrderClick(int position) {

    }
}
