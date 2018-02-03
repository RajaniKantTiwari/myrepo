package com.app.community.ui.dashboard.home.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentMainNewsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.NewsPagerAdapter;
import com.app.community.utils.CommonUtils;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsMainActivity extends CommonActivity {
    private FragmentMainNewsBinding mBinding;
    private NewsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.fragment_main_news);
        initializeData();
    }

   /* @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_news, container, false);
        return mBinding.getRoot();
    }*/

    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.news));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        Intent intent = getIntent();
        if (CommonUtils.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            pagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(), bundle);
            mBinding.viewPager.setAdapter(pagerAdapter);
            mBinding.viewPager.setCurrentItem(1);
        }
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }


    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
