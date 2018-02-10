package com.app.community.ui.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.FragmentProductSubproductBinding;
import com.app.community.databinding.ItemCartBinding;
import com.app.community.event.UpdateCartEvent;
import com.app.community.network.request.cart.CartListRequest;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.CategoryData;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.cart.SubCategory;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.fragment.CheckoutFragment;
import com.app.community.ui.dashboard.home.fragment.FullInformationFragment;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.LogUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;


public class ProductSubproductFragment extends DashboardFragment implements CartAdapter.OnAddToCart, CategoryAdapter.OnCatItemClick, SubCatAdapter.OnSubCatItemClick {
    private FragmentProductSubproductBinding mBinding;
    private CategoryAdapter mCategoryAdapter;
    private SubCatAdapter mSubCategoryAdapter;
    private LinearLayoutManager mLayoutManager, mLayoutMangerSubcat, mLayoutManagerCart;
    private CartAdapter mCartAdapter;
    private int oldCatPos, oldSubCatPos;
    private int MAX_LIMIT = 10, MIN_LIMIT = 0;
    private int merchantId;
    private ArrayList<CategoryData> mCatList = new ArrayList<>();
    private ArrayList<SubCategory> mSubCatList = new ArrayList<>();
    private ArrayList<ProductData> mCartList = new ArrayList<>();
    private ArrayList<ProductData> addCartList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_subproduct, container, false);
        return mBinding.getRoot();
    }

    private void setViews() {
        mLayoutManager = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutMangerSubcat = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerCart = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.VERTICAL, false);
        mBinding.rvCat.setLayoutManager(mLayoutManager);
        mBinding.rvSubCat.setLayoutManager(mLayoutMangerSubcat);
        mBinding.rvDetail.setLayoutManager(mLayoutManagerCart);
        mBinding.tvCheckout.setOnClickListener(this);
        mCategoryAdapter = new CategoryAdapter(mCatList, this);
        mSubCategoryAdapter = new SubCatAdapter(mSubCatList, this);
        mCartAdapter = new CartAdapter(mCartList, this);
        mBinding.rvCat.setAdapter(mCategoryAdapter);
        mBinding.rvSubCat.setAdapter(mSubCategoryAdapter);
        mBinding.rvDetail.setAdapter(mCartAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCheckout:
                CommonUtils.clicked(mBinding.tvCheckout);
                getDashboardActivity().addFragmentInContainer(new CheckoutFragment(), null, true, true, NONE);
                break;
        }

    }

    private void addToCart(TextView textView, int pos) {
        boolean isNotAdded = true;
        int count = Integer.parseInt(textView.getText().toString());
        if (count < MAX_LIMIT) {
            count++;
            textView.setText(String.valueOf(count));
        } else {
            Toast.makeText(getDashboardActivity(), getResources().getString(R.string.you_can_not_add), Toast.LENGTH_SHORT).show();
        }
        mCartList.get(pos).setQty(count);
        for (int i = 0; i < addCartList.size(); i++) {
            if (mCartList.get(pos).getMerchantlistid() == addCartList.get(i).getMerchantlistid()) {
                isNotAdded = false;
                addCartList.set(i, mCartList.get(pos));
            }
        }
        if (isNotAdded) {
            addCartList.add(mCartList.get(pos));
        }
        setCartData();
        setTotalAmount();
    }

    private void removeFromCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count > MIN_LIMIT) {
            count--;
            textView.setText(String.valueOf(count));
        } else {
            count = 0;
            Toast.makeText(getDashboardActivity(), "Your cart is already empty.", Toast.LENGTH_SHORT).show();
        }
        mCartList.get(pos).setQty(count);
        for (int i = 0; i < addCartList.size(); i++) {
            if (mCartList.get(pos).getMerchantlistid() == addCartList.get(i).getMerchantlistid()) {
                if (mCartList.get(pos).getQty() == 0) {
                    addCartList.remove(i);
                } else {
                    addCartList.set(i, mCartList.get(pos));
                }
            }
        }
        setCartData();
        setTotalAmount();

    }

    private void setCartData() {
        if(addCartList.size()>0){
            ProductData product = addCartList.get(0);
            product.setMerchantId(merchantId);
            addCartList.set(0,product);
        }
        PreferenceUtils.setCartData(addCartList);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (response != null) {
            if (response instanceof CategoryResponse) {
                CategoryResponse categoryResponse = (CategoryResponse) response;
                CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
                ArrayList<CategoryData> categoryList = categoryResponse.getInfo();
                if (CommonUtils.isNotNull(categoryList) && categoryList.size() > 0) {
                    mCatList.clear();
                    mSubCatList.clear();
                    mCartList.clear();
                    mCatList.addAll(categoryList);
                    mCatList.get(0).setSelected(true);
                    CategoryData categoryData = categoryList.get(0);
                    if (CommonUtils.isNotNull(categoryData)) {
                        mSubCatList.addAll(categoryData.getSubproduct());
                        if (mSubCatList.size() > 0) {
                            mSubCatList.get(0).setSelected(true);
                            mCartList.addAll(categoryData.getSubproduct().get(0).getSubproduct());
                            mBinding.tvTitle.setText(categoryData.getSubproduct().get(0).getName());
                        }
                        mCategoryAdapter.notifyDataSetChanged();
                        mSubCategoryAdapter.notifyDataSetChanged();
                        mCartAdapter.notifyDataSetChanged();

                    }
                    mBinding.tvTotal.setText(String.valueOf(0.00));
                } else {
                    CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
                }

            }
        } else {
            CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
        }
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void setListener() {
        mBinding.tvCheckout.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return ProductSubproductFragment.class.getName();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            String productResponse = bundle.getString(AppConstants.MERCHANT_ID);
            merchantId = Integer.parseInt(productResponse);
            getDashboardActivity().setHeader(bundle.getString(AppConstants.MERCHANT_ADDRESS),
                    bundle.getString(AppConstants.MERCHANT_IMAGE), bundle.getString(AppConstants.MERCHANT_BACKGROUND_COLOR));
        }
        setViews();
        callApi();
    }

    @Override
    public void addToCartClick(int pos, View view) {
        ItemCartBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.ivPlus:
                addToCart(viewBinding.tvQty, pos);
                break;
            case R.id.ivMinus:
                removeFromCart(viewBinding.tvQty, pos);
                break;
            case R.id.layoutProduct:
            case R.id.layoutInfo:
                if (CommonUtils.isNotNull(mCartList) && mCartList.size() > pos) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(AppConstants.PRODUCT_DATA, mCartList.get(pos));
                    getDashboardActivity().addFragmentInContainer(new FullInformationFragment(), bundle, true, true, NONE);
                }
                break;
        }


    }

    private void setTotalAmount() {
        float total = 0.0f;
        EventBus.getDefault().post(new UpdateCartEvent());
        for (ProductData data : addCartList) {
            total += data.getQty() * data.getProduct_mrp();
        }
        mBinding.tvTotal.setText(String.valueOf(total));
    }

    @Override
    public void onCatClick(int pos, View view) {
//        ItemCartBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();

        switch (itemView.getId()) {
            case R.id.tvName:
                mCatList.get(oldCatPos).setSelected(false);
                mCatList.get(pos).setSelected(true);
                mSubCatList.clear();
                mSubCatList.addAll(mCatList.get(pos).getSubproduct());
                for(int i=0;i<mSubCatList.size();i++){
                    mSubCatList.get(i).setSelected(false);
                }
                mSubCatList.get(0).setSelected(true);
                mCartList.clear();
                mCartList.addAll(mSubCatList.get(0).getSubproduct());
                mSubCategoryAdapter.notifyDataSetChanged();
                mCategoryAdapter.notifyDataSetChanged();
                mCartAdapter.notifyDataSetChanged();
                oldCatPos = pos;
                oldSubCatPos = 0;
                break;
        }
    }

    @Override
    public void onSubCatClick(int pos, View view) {
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.tvName:
                mSubCatList.get(oldSubCatPos).setSelected(false);
                mSubCatList.get(pos).setSelected(true);
                mCartList.clear();
                mCartList.addAll(mSubCatList.get(pos).getSubproduct());
                mSubCategoryAdapter.notifyDataSetChanged();
                mCartAdapter.notifyDataSetChanged();
                oldSubCatPos = pos;
                break;
        }
    }

    private void callApi() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setMerchant_id(merchantId);
        getPresenter().getCategory(getDashboardActivity(), categoryRequest);
    }
}
