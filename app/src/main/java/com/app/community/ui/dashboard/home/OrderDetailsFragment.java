package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderDetailsBinding;
import com.app.community.network.request.dashboard.OrderDetailsRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.home.adapter.CheckoutCartAdapter;
import com.app.community.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.community.ui.dashboard.home.fragment.MyOrderActivity;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsFragment extends BaseFragment implements OrderListAdapter.OrderListener {

    private FragmentOrderDetailsBinding mBinding;
    private CheckoutCartAdapter mCheckoutAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_details, container, false);
        //getDashboardActivity().setHeaderTitle(getString(R.string.order_details));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

        mCheckoutAdapter = new CheckoutCartAdapter(getBaseActivity());
        mBinding.rvCartItem.setAdapter(mCheckoutAdapter);
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            String orderId = bundle.getString(GeneralConstant.ORDER_ID);
            OrderDetailsRequest request = new OrderDetailsRequest();
            request.setOrderid(orderId);
            ((MyOrderActivity) getBaseActivity()).getOrderPresenter().orderDetails(getActivity(), request);
        }
    }

    @Override
    public void setListener() {
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

        }
    }

    private void setDtaForCheckout(ProductDetailsData data) {
        mCheckoutAdapter.setCartList(data.getProduct());
    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onOrderClick(int position) {

    }
}
