package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivitySearchBinding;
import com.app.community.network.request.dashboard.ProductSearchRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.dashboard.home.adapter.SearchAdapter;
import com.app.community.ui.dashboard.home.event.SearchProductEvent;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by rajnikant on 31/12/17.
 */

public class SearchActivity extends CommonActivity implements SearchAdapter.SearchListener {
    private ActivitySearchBinding mBinding;
    private SearchAdapter mAdapter;
    @Inject
    CommonPresenter presenter;
    private String search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        initializeAdapter();
        initializeData();
        setListener();
    }

    private void initializeAdapter() {
        mAdapter = new SearchAdapter(this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvSearch.setLayoutManager(layoutManager);
        mBinding.rvSearch.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvSearch.setAdapter(mAdapter);

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
                search=searchText.toString();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.searchProductList(SearchActivity.this,new ProductSearchRequest(search));
                    }
                }, GeneralConstant.API_SERVICE);
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
        SearchProductEvent searchEvent=new SearchProductEvent(search);
        EventBus.getDefault().post(searchEvent);
        finish();
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    @Override
    public void itemClicked(int adapterPosition) {
        gotoProduct();

    }
}
