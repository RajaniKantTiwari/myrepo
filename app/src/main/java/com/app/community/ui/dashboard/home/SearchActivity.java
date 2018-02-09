package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivitySearchBinding;
import com.app.community.event.ProductDetailsEvent;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.dashboard.home.adapter.SearchAdapter;
import com.app.community.ui.dashboard.home.adapter.SearchProductServiceAdapter;
import com.app.community.ui.dashboard.home.event.SearchProductEvent;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by rajnikant on 31/12/17.
 */

public class SearchActivity extends CommonActivity implements SearchAdapter.SearchListener, SearchProductServiceAdapter.SearchProductServiceListener {
    @Inject
    CommonPresenter presenter;
    private String search;
    private SearchProductServiceAdapter mProductAdapter;
    private SearchProductServiceAdapter mServiceAdapter;
    private ActivitySearchBinding mBinding;
    private SearchAdapter mSearchAdapter;
    private ArrayList<MerchantResponse> merchantList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initializeAdapter();
        initializeData();
        setListener();
    }

    private void initializeAdapter() {
        // set search list
        merchantList = new ArrayList<>();
        mSearchAdapter = new SearchAdapter(this, merchantList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvSearch.setLayoutManager(layoutManager);
        mBinding.rvSearch.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvSearch.setAdapter(mSearchAdapter);
        //end
// set service list
        mProductAdapter = new SearchProductServiceAdapter(this, this, GeneralConstant.PRODUCT);
        GridLayoutManager productManager = new GridLayoutManager(this, 2);
        mBinding.defaultSearch.rvProduct.setLayoutManager(productManager);
        mBinding.defaultSearch.rvProduct.setAdapter(mProductAdapter);
//end
        // set service list
        mServiceAdapter = new SearchProductServiceAdapter(this, this, GeneralConstant.SERVICE);
        GridLayoutManager serviceManager = new GridLayoutManager(this, 2);
        mBinding.defaultSearch.rvService.setLayoutManager(serviceManager);
        mBinding.defaultSearch.rvService.setAdapter(mServiceAdapter);
//end
    }

    public void initializeData() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.layoutHeader.textView.setOnClickListener(this);
        mBinding.layoutHeader.textView.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.textView.setText(getResources().getString(R.string.go));
        mBinding.layoutHeader.edSearch.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence searchText, int start, int before, int count) {
                search = searchText.toString();
                if (searchText.toString().length() == 0) {
                    showDefault();

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.progressBar.setVisibility(View.VISIBLE);
                            presenter.getMerchantListBySearch(SearchActivity.this, new MerchantSearchRequest(search));
                        }
                    }, GeneralConstant.API_SERVICE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showDefault() {
        mBinding.defaultSearch.layoutSearch.setVisibility(View.VISIBLE);
        mBinding.rvSearch.setVisibility(View.GONE);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        } else if (view == mBinding.layoutHeader.textView) {
            if (CommonUtils.isNotNull(search) && search.length() > 0) {
                gotoProduct();
            } else {
                showToast(getResources().getString(R.string.please_enter_text_to_get_merchant));
            }
        }
    }

    private void gotoProduct() {
        SearchProductEvent searchEvent = new SearchProductEvent(search);
        EventBus.getDefault().post(searchEvent);
        finish();
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        mBinding.defaultSearch.layoutSearch.setVisibility(View.GONE);
        mBinding.rvSearch.setVisibility(View.VISIBLE);
        mBinding.tvNoResult.setVisibility(View.GONE);
        if (CommonUtils.isNotNull(response) && response instanceof SearchResponseData) {
            SearchResponseData responseData = (SearchResponseData) response;
            if (CommonUtils.isNotNull(responseData)) {
                if (CommonUtils.isNotNull(responseData.getData()) && responseData.getData().size() > 0) {
                    merchantList.clear();
                    merchantList.addAll(responseData.getData());
                    mSearchAdapter.notifyDataSetChanged();
                    if (CommonUtils.isNull(search) || search.length() == 0) {
                        showDefault();
                    }
                } else {
                    mBinding.tvNoResult.setVisibility(View.VISIBLE);
                }
            }
        }
    }


    @Override
    public void itemClicked(int position) {
        if (CommonUtils.isNotNull(merchantList) && merchantList.size() > position) {
            MerchantResponse merchant = merchantList.get(position);
            if (CommonUtils.isNotNull(merchant)) {
                gotoProductDetails(merchant.getId());
            }
        }


    }

    private void gotoProductDetails(String id) {
        ProductDetailsEvent productDetailsEvent = new ProductDetailsEvent(id);
        EventBus.getDefault().post(productDetailsEvent);
        finish();
    }

    @Override
    public void onProductServiceItemClick(int position, int type) {

    }
}
