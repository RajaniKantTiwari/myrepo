package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.FragmentFullInformationBinding;
import com.app.community.event.ProductUpdateEvent;
import com.app.community.event.UpdateCartEvent;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.cart.ProductFullInformationData;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class FullInformationFragment extends DashboardFragment {
    private FragmentFullInformationBinding mBinding;
    private int quantity;
    private ProductData productData;
    private int merchantId;
    //check whether it is present in cart or not
    private boolean isAddedInCart = false;
    private ArrayList<ProductData> productList = new ArrayList<>();
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_information, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            productData = bundle.getParcelable(AppConstants.PRODUCT_DATA);
            position=bundle.getInt(GeneralConstant.POSITION);;
            merchantId = bundle.getInt(AppConstants.MERCHANT_ID);
            if (CommonUtils.isNotNull(PreferenceUtils.getCartData())) {
                productList = PreferenceUtils.getCartData();
                for (ProductData data : PreferenceUtils.getCartData()) {
                    if (data.getMasterproductid() == productData.getMasterproductid()) {
                        isAddedInCart = true;
                        quantity = data.getQty();
                        productData.setQty(quantity);
                    }
                }
            } else {
                productList = new ArrayList<>();
            }
            mBinding.tvCart.setText(String.valueOf(quantity));
        }
        if (CommonUtils.isNotNull(productData)) {
            ProductRequest request = new ProductRequest(productData.getMasterproductid(), merchantId);
            getPresenter().getProductDetails(getBaseActivity(), request);
        }
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
        if (CommonUtils.isNotNull(getPresenter())) {
            getPresenter().attachView(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.ivAdd) {
            if (quantity <10) {
                quantity = quantity + 1;
                addRemoveCart(quantity);
            } else {
                Toast.makeText(getDashboardActivity(), getResources().getString(R.string.you_can_not_add), Toast.LENGTH_SHORT).show();
            }
        } else if (view == mBinding.ivSub) {
            if (quantity > 0) {
                quantity = quantity - 1;
                addRemoveCart(quantity);
            } else {
                getBaseActivity().showToast(getResources().getString(R.string.empty_cart));
            }
        } else if (view == mBinding.tvAddToCart) {
            CommonUtils.clicked(mBinding.tvAddToCart);
            addInCart();
        }
    }

    private void addInCart() {
        if(quantity>0){
            if (isAddedInCart) {
                for (int i = 0; i < productList.size(); i++) {
                    ProductData data = productList.get(i);
                    if (data.getMasterproductid() == productData.getMasterproductid()) {
                        productList.set(i, productData);
                    }
                }
            } else {
                productList.add(productData);
            }
        }else{
            productList.remove(productData);
        }
        PreferenceUtils.setCartData(productList);
        EventBus.getDefault().post(new UpdateCartEvent());
        EventBus.getDefault().post(new ProductUpdateEvent(position,productData));
    }

    private void addRemoveCart(int quantity) {
        productData.setQty(quantity);
        mBinding.tvCart.setText(String.valueOf(quantity));
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response) && response instanceof ProductFullInformationData) {
            ProductFullInformationData data = (ProductFullInformationData) response;
            if (CommonUtils.isNotNull(data.getInfo()) && data.getInfo().size() > 0) {
                ProductData productData = data.getInfo().get(0);
                GlideUtils.loadImage(getDashboardActivity(), productData.getImagepath()
                        , mBinding.ivProductImage, null, R.drawable.background_placeholder);
                mBinding.setProduct(productData);
            }
        }
    }

}
