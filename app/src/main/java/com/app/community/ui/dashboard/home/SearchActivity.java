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
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.feed.MerchantResponse;
import com.app.community.network.response.dashboard.feed.SearchResponseData;
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
        mSearchAdapter = new SearchAdapter(this,merchantList, this);
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
        mBinding.ivBack.setOnClickListener(this);
        mBinding.tvGo.setOnClickListener(this);
        mBinding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence searchText, int start, int before, int count) {
                if (searchText.toString().length() == 0) {
                    mBinding.defaultSearch.layoutSearch.setVisibility(View.VISIBLE);
                    mBinding.rvSearch.setVisibility(View.GONE);
                }else{
                    search = searchText.toString();
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

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.ivBack) {
            finish();
        } else if (view == mBinding.tvGo) {
            gotoProduct();
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
        if (CommonUtils.isNotNull(response) && response instanceof SearchResponseData) {
            SearchResponseData responseData = (SearchResponseData) response;
            if (CommonUtils.isNotNull(responseData)){
                if(CommonUtils.isNotNull(responseData.getData())){
                    merchantList.clear();
                    merchantList.addAll(responseData.getData());
                    mSearchAdapter.notifyDataSetChanged();
                }
            }
        }
    }


    @Override
    public void itemClicked(int adapterPosition) {
        gotoProduct();

    }

    @Override
    public void onProductServiceItemClick(int position, int type) {

    }
}
