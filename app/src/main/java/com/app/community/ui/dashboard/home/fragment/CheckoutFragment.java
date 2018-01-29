package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentCheckoutBinding;
import com.app.community.network.request.PaymentOption;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.activity.PaymentAdapter;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.MerchantDetailsFragment;
import com.app.community.ui.dashboard.home.adapter.CheckoutCartAdapter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by ashok on 26/12/17.
 */

public class CheckoutFragment extends DashboardFragment{
    private FragmentCheckoutBinding mBinding;
    private CheckoutCartAdapter mCheckoutAdapter;
    List<PaymentOption> paymentList = new ArrayList<>();
    List<PaymentOption> deliveryList = new ArrayList<>();

    private PaymentAdapter paymentAdapter;
    private PaymentAdapter deliveryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager cartManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvCartItem.setLayoutManager(cartManager);
        mBinding.rvCartItem.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        LinearLayoutManager paymentManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvPayment.setLayoutManager(paymentManager);
        LinearLayoutManager deliveryManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvDelivery.setLayoutManager(deliveryManager);
        deliveryManager.setAutoMeasureEnabled(true);

    }

    @Override
    public void initializeData() {
        mCheckoutAdapter = new CheckoutCartAdapter(getBaseActivity());
        mBinding.rvCartItem.setAdapter(mCheckoutAdapter);
        setPaymentOption();
        paymentAdapter = new PaymentAdapter(getBaseActivity(), paymentList);
        mBinding.rvPayment.setAdapter(paymentAdapter);
        CommonUtils.setViewHeight(mBinding.rvPayment,paymentList);
        setDelivery();
        deliveryAdapter = new PaymentAdapter(getBaseActivity(), deliveryList);
        mBinding.rvDelivery.setAdapter(deliveryAdapter);
        CommonUtils.setViewHeight(mBinding.rvDelivery,deliveryList);

    }



    private void setDelivery() {
        PaymentOption option1 = new PaymentOption();
        option1.setPaymentString(getResources().getString(R.string.pick_on_the_way));
        PaymentOption option2 = new PaymentOption();
        option2.setPaymentString(getResources().getString(R.string.home_delivery));
        deliveryList.add(option1);
        deliveryList.add(option2);

    }

    private void setPaymentOption() {
        PaymentOption option1 = new PaymentOption();
        option1.setPaymentString(getResources().getString(R.string.cash_on_delivery));
        PaymentOption option2 = new PaymentOption();
        option2.setPaymentString(getResources().getString(R.string.credit_debit_card));
        PaymentOption option3 = new PaymentOption();
        option3.setPaymentString(getResources().getString(R.string.paytm));
        paymentList.add(option1);
        paymentList.add(option2);
        paymentList.add(option3);

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        CheckoutFragment fragment = new CheckoutFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
