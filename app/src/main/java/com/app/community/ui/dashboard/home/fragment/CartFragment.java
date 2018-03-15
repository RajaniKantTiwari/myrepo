package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.CartRowItemBinding;
import com.app.community.databinding.FragmentCartBinding;
import com.app.community.event.StartShoppingEvent;
import com.app.community.event.UpdateCartEvent;
import com.app.community.network.request.cart.Cart;
import com.app.community.network.request.cart.CartListRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.cart.DeleteCartRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.CartRowAdapter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class CartFragment extends DashboardFragment implements CartRowAdapter.OnAddToCart {
    private FragmentCartBinding mBinding;
    private CartRowAdapter mAdapter;
    private int MAX_LIMIT = 10, MIN_LIMIT = 0;
    private ArrayList<ProductData> mCartList;
    private CartRequest cartRequest;
    private int merchantId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mCartList = PreferenceUtils.getCartData();
        if(CommonUtils.isNotNull(mCartList)&&mCartList.size()>0){
            merchantId=mCartList.get(0).getMerchantId();
        }
        mAdapter = new CartRowAdapter(getBaseActivity(), mCartList, this);
        mBinding.rvCartList.setLayoutManager(layoutManager);
        mBinding.rvCartList.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvCartList.setAdapter(mAdapter);
        setTotalAmount();
    }

    @Override
    public void initializeData() {
        //cartRequest = new CartRequest();
        //getPresenter().addToCart(getDashboardActivity(), cartRequest);

    }

    @Override
    public void setListener() {
        mBinding.tvCheckoutNow.setOnClickListener(this);
        mBinding.layoutNoData.tvStartShopping.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return CartFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvCheckoutNow) {
            CommonUtils.clicked(mBinding.tvCheckoutNow);
            if (CommonUtils.isNotNull(PreferenceUtils.getCartData()) && PreferenceUtils.getCartData().size() > 0) {
                addToCartList();
            } else {
                getDashboardActivity().showToast(getResources().getString(R.string.please_add_data_in_cart_first));
            }
        }else if(view ==mBinding.layoutNoData.tvStartShopping){
            CommonUtils.clicked(mBinding.layoutNoData.tvStartShopping);
            EventBus.getDefault().post(new StartShoppingEvent(String .valueOf(merchantId)));
        }
    }

    private void addToCartList() {
        if (CommonUtils.isNotNull(PreferenceUtils.getCartData()) &&
                CommonUtils.isNotNull(PreferenceUtils.getCartData().size())
                && PreferenceUtils.getCartData().size() > 0) {
            //request for cart
            CartListRequest request = new CartListRequest();
            //list of product added in cart
            ArrayList<Cart> cartList = new ArrayList<>();
            ArrayList<ProductData> productList = PreferenceUtils.getCartData();
            request.setMerchant_id(productList.get(0).getMerchantId());
            //id of merchant
            for (ProductData product : productList) {
                if (CommonUtils.isNotNull(product)) {
                    Cart cart = new Cart();
                    cart.setMerchantlist_id(product.getMerchantlistid());
                    cart.setMasterproductid(product.getMasterproductid());
                    cart.setQty(product.getQty());
                    cartList.add(cart);
                }
            }
            request.setCart(cartList);
            getPresenter().addForCartList(getDashboardActivity(), request, this);
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == AppConstants.CARTADDED) {
            getDashboardActivity().addFragmentInContainer(new CheckoutFragment(), null, true, true, NONE);
        } else if (CommonUtils.isNotNull(response) && response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
            if (CommonUtils.isNotNull(mCartList) && mCartList.size() > requestCode) {
                mCartList.remove(requestCode);
                setCartData();
            }
        }
    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void addToCartClick(int pos, View view) {
        CartRowItemBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.ivAdd:
                addToCart(viewBinding.tvQuantity, pos);
                break;
            case R.id.ivSub:
                removeFromCart(viewBinding.tvQuantity, pos);
                break;
            case R.id.ivDeleteCart:
                if (CommonUtils.isNotNull(mCartList) && mCartList.size() > pos) {
                    getPresenter().deleteFromCart(getDashboardActivity(), new DeleteCartRequest(mCartList.get(0).getMerchantId(), mCartList.get(pos).getMasterproductid()), pos);
                }
               /* if (CommonUtils.isNotNull(mCartList) && mCartList.size() > pos) {
                    mCartList.remove(pos);
                    setCartData();
                }*/
                break;
        }
    }

    private void addToCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count < MAX_LIMIT) {
            count++;
            textView.setText(String.valueOf(count));
            mCartList.get(pos).setQty(count);
            setCartData();
            setTotalAmount();
        } else {
            Toast.makeText(getDashboardActivity(), getResources().getString(R.string.you_can_not_add), Toast.LENGTH_SHORT).show();
        }

    }

    private void setCartData() {
        PreferenceUtils.setCartData(mCartList);
        EventBus.getDefault().post(new UpdateCartEvent());
        if (CommonUtils.isNull(mCartList) || mCartList.size() == 0) {
            mBinding.layoutNoData.layoutNoData.setVisibility(View.VISIBLE);
            mBinding.layoutNoData.tvStartShopping.setVisibility(View.VISIBLE);
            mBinding.layoutNoData.tvNoData.setText(getResources().getString(R.string.your_cart_is_empty));
            mBinding.mainLayout.setVisibility(View.GONE);
        } else {
            mBinding.layoutNoData.layoutNoData.setVisibility(View.GONE);
            mBinding.mainLayout.setVisibility(View.VISIBLE);

        }
        mAdapter.notifyDataSetChanged();
    }

    private void removeFromCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count > MIN_LIMIT) {
            count--;
            textView.setText(String.valueOf(count));
            mCartList.get(pos).setQty(count);
            setTotalAmount();
            if (count == 0) {
                mCartList.remove(pos);
            }
            setCartData();
        }
    }


    private void setTotalAmount() {
        float total = 0.0f;
        EventBus.getDefault().post(new UpdateCartEvent());
        for (ProductData data : mCartList) {
            total += data.getQty() * data.getProduct_mrp();
        }
        mBinding.tvTotal.setText(String.valueOf(total));
    }
}
