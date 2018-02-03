package com.app.community.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentHelpsupportBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.HelpSupportAdapter;
import com.app.community.ui.dialogfragment.OrderDialogFragment;
import com.app.community.utils.CommonUtils;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class HelpsAndSupportActivity extends CommonActivity implements HelpSupportAdapter.HelpSupportListener, OrderDialogFragment.OrderDialogListener {
    private FragmentHelpsupportBinding mBinding;
    private HelpSupportAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.fragment_helpsupport);
        initializeAdapter();
        initializeData();
        setListener();
    }


    private void initializeAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new HelpSupportAdapter(this, this);
        mBinding.rvChoice.setLayoutManager(layoutManager);
        mBinding.rvChoice.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvChoice.setAdapter(mAdapter);
    }

    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.offer));
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
