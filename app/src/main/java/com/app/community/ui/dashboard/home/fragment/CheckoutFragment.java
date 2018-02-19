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
import com.app.community.network.request.cart.CheckoutRequest;
import com.app.community.network.request.dashboard.Coupon;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.activity.EditAddressActivity;
import com.app.community.ui.activity.PaymentAdapter;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.ConfirmOrderFragment;
import com.app.community.ui.dashboard.home.adapter.CheckoutCartAdapter;
import com.app.community.ui.dashboard.home.adapter.CouponAdapter;
import com.app.community.ui.dashboard.home.event.UpdateAddress;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by ashok on 26/12/17.
 */

public class CheckoutFragment extends DashboardFragment implements CouponAdapter.CouponListener {
    private FragmentCheckoutBinding mBinding;
    private CheckoutCartAdapter mCheckoutAdapter;
    private List<PaymentOption> paymentList = new ArrayList<>();
    private List<PaymentOption> deliveryList = new ArrayList<>();
    private List<Coupon> couponList = new ArrayList<>();


    private PaymentAdapter paymentAdapter;
    private PaymentAdapter deliveryAdapter;
    private CouponAdapter couponAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false);
        CommonUtils.register(this);
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
        LinearLayoutManager couponManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvCouponCode.setLayoutManager(couponManager);
        couponManager.setAutoMeasureEnabled(true);

    }

    @Override
    public void initializeData() {
        getPresenter().viewCart(getDashboardActivity());
        mCheckoutAdapter = new CheckoutCartAdapter(getBaseActivity());
        mBinding.rvCartItem.setAdapter(mCheckoutAdapter);
        setPaymentOption();
        paymentAdapter = new PaymentAdapter(getBaseActivity(), paymentList);
        mBinding.rvPayment.setAdapter(paymentAdapter);
        CommonUtils.setRecyclerViewHeight(mBinding.rvPayment, paymentList, GeneralConstant.PAYMENT_HEIGHT);
        setDelivery();
        deliveryAdapter = new PaymentAdapter(getBaseActivity(), deliveryList);
        mBinding.rvDelivery.setAdapter(deliveryAdapter);
        CommonUtils.setRecyclerViewHeight(mBinding.rvDelivery, deliveryList, GeneralConstant.PAYMENT_HEIGHT);
        setCoupon();
        couponAdapter = new CouponAdapter(getBaseActivity(), couponList, this);
        mBinding.rvCouponCode.setAdapter(couponAdapter);
        CommonUtils.setRecyclerViewHeight(mBinding.rvCouponCode, couponList, GeneralConstant.COUPON_HEIGHT);
    }

    private void setCoupon() {
        Coupon coupon = new Coupon();
        coupon.setCouponOffer("10 % off on all products");
        couponList.add(coupon);
        Coupon coupon1 = new Coupon();
        coupon1.setCouponOffer("10 % off on all products has given");
        couponList.add(coupon1);
        Coupon coupon2 = new Coupon();
        coupon2.setCouponOffer("20 % off on bevrage");
        couponList.add(coupon2);
        Coupon coupon3 = new Coupon();
        coupon3.setCouponOffer("20 % off on all bevrage given");
        couponList.add(coupon3);
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
        mBinding.tvProceedToPay.setOnClickListener(this);
        mBinding.editAddress.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return CheckoutFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.editAddress) {
            CommonUtils.clicked(mBinding.editAddress);
            ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), EditAddressActivity.class);

        } else if (view == mBinding.tvProceedToPay) {
            getPresenter().checkout(getDashboardActivity(), new CheckoutRequest(1));
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == GeneralConstant.VIEW_CART) {
            if (CommonUtils.isNotNull(response) && response instanceof ProductDetailsData) {
                ProductDetailsData data = (ProductDetailsData) response;
                setDtaForCheckout(data);
            }
        } else if (requestCode == GeneralConstant.CHECKOUT) {
            if (CommonUtils.isNotNull(response) && response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
                CommonUtils.resetCart(getDashboardActivity());
                getDashboardActivity().addFragmentInContainer(new ConfirmOrderFragment(), null, true, true, NONE);
            } else {
                getDashboardActivity().showToast(getResources().getString(R.string.something_went_wrong));
            }
        }

    }

    private void setDtaForCheckout(ProductDetailsData data) {
        mCheckoutAdapter.setCartList(data.getProduct());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);
    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        CheckoutFragment fragment = new CheckoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Subscribe
    public void onAddressEvent(UpdateAddress event) {
        mBinding.tvAddress.setText(PreferenceUtils.getAddress());
    }

    @Override
    public void onCouponClick(int position) {
        if (CommonUtils.isNotNull(couponList) && couponList.size() > position) {
            for (int i = 0; i < couponList.size(); i++) {
                Coupon coupon = couponList.get(i);
                if (i == position) {
                    coupon.setChecked(true);
                } else {
                    coupon.setChecked(false);
                }
                couponList.set(i, coupon);
            }
            couponAdapter.notifyDataSetChanged();
        }
    }
}
