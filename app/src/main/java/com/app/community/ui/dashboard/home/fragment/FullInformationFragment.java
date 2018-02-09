package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentFullInformationBinding;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.utils.CommonUtils;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class FullInformationFragment extends DashboardFragment {
    private FragmentFullInformationBinding mBinding;
    private int quantity;
    private int productId=8;
    private int merchantId=8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_information, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        ProductRequest request=new ProductRequest(productId,merchantId);
        getPresenter().getProductDetails(getBaseActivity(),request);
    }

    @Override
    public void setListener() {
        mBinding.tvAddToCart.setOnClickListener(this);
        mBinding.ivAdd.setOnClickListener(this);
        mBinding.ivSub.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return FullInformationFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        if (CommonUtils.isNotNull(getPresenter())){
            getPresenter().attachView(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.ivAdd) {
            quantity = quantity + 1;
            addRemoveCart(quantity);
        } else if (view == mBinding.ivSub) {
            if (quantity > 0) {
                quantity = quantity - 1;
                addRemoveCart(quantity);
            } else {
                getBaseActivity().showToast(getResources().getString(R.string.empty_cart));
            }
        }else if(view==mBinding.tvAddToCart){
            CommonUtils.clicked(mBinding.tvAddToCart);
        }
    }

    private void addRemoveCart(int quantity) {
        mBinding.tvCart.setText(String.valueOf(quantity));
        if (quantity == 0) {
            getBaseActivity().showToast(getResources().getString(R.string.nothing_in_cart));
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        FullInformationFragment fragment = new FullInformationFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
