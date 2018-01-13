package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.SearchEvent;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivitySearchBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.home.adapter.SearchAdapter;
import com.app.community.ui.dashboard.home.event.SearchProductEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class SearchActivity extends BaseActivity implements SearchAdapter.SearchListener {
    private ActivitySearchBinding mBinding;
    private SearchAdapter mAdapter;

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

    }

    public void setListener() {
        mBinding.ivBack.setOnClickListener(this);
        mBinding.tvGo.setOnClickListener(this);
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
        SearchProductEvent searchEvent=new SearchProductEvent();
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
