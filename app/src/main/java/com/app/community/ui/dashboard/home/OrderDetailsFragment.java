package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderDetailsBinding;
import com.app.community.network.request.cart.CancelOrderRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.OrderDetail;
import com.app.community.network.response.dashboard.OrderDetailsData;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsFragment extends DashboardFragment implements OrderListAdapter.OrderListener {

    private FragmentOrderDetailsBinding mBinding;
    private OrderListAdapter mAdapter;
    private ArrayList<ProductData> productList;
    private String orderId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.order_details));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            orderId = bundle.getString(GeneralConstant.ORDER_ID);
        }
        productList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrderItems.setLayoutManager(layoutManager);
        mAdapter = new OrderListAdapter(getDashboardActivity(), productList, this);
        mBinding.rvOrderItems.setAdapter(mAdapter);
        //getPresenter().getPartialOrderDetail(getDashboardActivity(), new OrderRequest(orderId));

    }

    @Override
    public void setListener() {
        mBinding.tvCancel.setOnClickListener(this);
        mBinding.tvConfirmOrder.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return OrderDetailsFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == 1) {
                getDashboardActivity().showToast(response.getMsg());
                OrderDetailsData data = (OrderDetailsData) response;
                ArrayList<OrderDetail> orderDetailList = data.getData();
                if (CommonUtils.isNotNull(orderDetailList) && orderDetailList.size() > 0) {
                    setOrderDetails(orderDetailList.get(0));
                }
            }  else if (requestCode == 7||requestCode==8) {
                getDashboardActivity().showToast(response.getMsg());
            }
        }
    }

    private void setOrderDetails(OrderDetail orderDetail) {
        //mBinding.tvCustomerName.setText(CommonUtils.addStrings(orderDetail.getFirstname(), orderDetail.getMiddlename(), orderDetail.getLastname()));
        mBinding.tvCustomerAddress.setText(orderDetail.getDelivery_address());
        mBinding.tvOrderValue.setText(orderDetail.getGrandtotal());
        mBinding.tvOrderNumber.setText(orderDetail.getInvoiceNumber());
        mBinding.tvOrderDate.setText(orderDetail.getInvoiceDate());
        mBinding.tvOrderStatus.setText(orderDetail.getOrder_status());
        mBinding.tvPaymentStatus.setText(orderDetail.getPaymentStatus());
        try {
            ProductData productData = new ProductData();
            productData.setId(Integer.parseInt(orderId));
           /* productData.setProductname(orderDetail.getProductname());
            productData.setQty(Integer.parseInt(orderDetail.getQuantity()));
            productData.setMrp(orderDetail.getMrp());
            productData.setSelling_price(orderDetail.getMerchant_actual_selling_price());*/
            productList.add(productData);
            mAdapter.notifyDataSetChanged();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        if (view == mBinding.tvCancel) {
            CancelOrderRequest request = new CancelOrderRequest();
            request.setOrder_id(orderId);
            //getPresenter().confirmOrder(getDashboardActivity(), request);
        } /*else if (view == mBinding.tvConfirmOrder) {
            CancelOrderRequest request = new CancelOrderRequest();
            request.setOrder_id(orderId);
            getPresenter().cancelOrder(getDashboardActivity(), request);
        }*/
    }


    @Override
    public void onOrderClick(int position) {

    }
}
