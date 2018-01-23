package com.app.community.ui.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.CommonApplication;
import com.app.community.R;
import com.app.community.databinding.ItemCartBinding;
import com.app.community.databinding.ProductSubproductCartBinding;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.CategoryData;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.cart.SubCategory;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardPresenter;
import com.app.community.ui.dashboard.home.WelcomeHomeFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.LogUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.app.community.utils.CommonUtils.isNotNull;
import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;


public class ProductSubproductFragment extends DashboardFragment implements MvpView, OnClickListener, CartAdapter.OnAddToCart, CategoryAdapter.OnCatItemClick, SubCatAdapter.OnSubCatItemClick {
    private ProductSubproductCartBinding mBinding;
    private CategoryAdapter mCategoryAdapter;
    private SubCatAdapter mSubCategoryAdapter;
    private LinearLayoutManager mLayoutManager, mLayoutMangerSubcat, mLayoutManagerCart;
    private CartAdapter mCartAdapter;
    private int oldCatPos, oldSubCatPos;
    private int MAX_LIMIT = 10, MIN_LIMIT = 0;
    private int merchantId = 2;
    private ArrayList<CategoryData> mCatList = new ArrayList<>();
    private ArrayList<SubCategory> mSubCatList = new ArrayList<>();
    private ArrayList<ProductData> mCartList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_subproduct_cart, container, false);
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

//        mBinding.layoutSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                callApi();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCheckout:
                Toast.makeText(getDashboardActivity(), "Under process", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void addToCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());

        if (count < MAX_LIMIT) {
            count++;
            textView.setText(String.valueOf(count));

        } else {
            Toast.makeText(getDashboardActivity(), "You can not add more than 10 item in cart", Toast.LENGTH_SHORT).show();


        }
        mCartList.get(pos).setQty(count);
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

        setTotalAmount();

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        LogUtils.LOGD("", "onSuccess Called");
        if (response != null) {
            if (response instanceof CategoryResponse) {
                CategoryResponse categoryResponse = (CategoryResponse) response;
                if (isNotNull(categoryResponse)) {
                    mCatList.clear();
                    mSubCatList.clear();
                    mCartList.clear();
                    mCatList.addAll(categoryResponse.getInfo());
                    mCatList.get(0).setSelected(true);

                    mSubCatList.addAll(categoryResponse.getInfo().get(0).getSubproduct());
                    mSubCatList.get(0).setSelected(true);

                    mCartList.addAll(categoryResponse.getInfo().get(0).getSubproduct().get(0).getSubproduct());
                    mCategoryAdapter.notifyDataSetChanged();
                    mSubCategoryAdapter.notifyDataSetChanged();
                    mCartAdapter.notifyDataSetChanged();
                    mBinding.tvTitle.setText(categoryResponse.getInfo().get(0).getSubproduct().get(0).getName());

                    mBinding.tvTotal.setText("Sub Total : INR" + 0.00);
                }
            }
        }
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return ProductSubproductFragment.class.getName();
    }

    @Override
    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            merchantId = bundle.getInt(GeneralConstant.INTENT_EXTRA_ID);
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
        }


    }

    private void setTotalAmount() {
        float total = 0.0f;
        for (ProductData data : mCartList) {
            total += data.getQty() * data.getProduct_mrp();
        }
        mBinding.tvTotal.setText("Sub Total : INR" + total);

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

                mSubCatList.get(pos).setSelected(true);
                mSubCatList.get(oldSubCatPos).setSelected(false);
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
    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        ProductSubproductFragment fragment = new ProductSubproductFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
