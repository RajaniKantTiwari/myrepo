package com.app.community.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityAboutusBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.dashboard.home.adapter.HelpSupportAdapter;
import com.app.community.ui.dialogfragment.OrderReviewSubmitDialogFragment;
import com.app.community.utils.CommonUtils;

/**
 * Created by rajnikant on 31/12/17.
 */

public class AboutUsActivity extends CommonActivity implements HelpSupportAdapter.HelpSupportListener, OrderReviewSubmitDialogFragment.OrderDialogListener {
    private ActivityAboutusBinding mBinding;
    private HelpSupportAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_aboutus);
        initializeAdapter();
        initializeData();
        setListener();
    }


    private void initializeAdapter() {

    }

    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.help_support));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view==mBinding.layoutHeader.ivBack){
            finish();
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }


    @Override
    public void itemClicked(int adapterPosition, boolean isChecked) {
        if (isChecked) {
            CommonUtils.showOrderDialog(this, null, this);
        }
    }

    @Override
    public void submit(String submit) {

    }
}
