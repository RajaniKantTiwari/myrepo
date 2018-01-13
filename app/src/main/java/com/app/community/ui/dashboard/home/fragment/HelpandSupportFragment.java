package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentHelpandsupportBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.ConfirmOrderFragment;
import com.app.community.ui.dashboard.home.adapter.HelpSupportAdapter;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class HelpandSupportFragment extends DashboardFragment {
    private FragmentHelpandsupportBinding mBinding;
    private HelpSupportAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_helpandsupport,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseActivity());
        mAdapter=new HelpSupportAdapter(getBaseActivity());
        mBinding.rvChoice.setLayoutManager(layoutManager);
        mBinding.rvChoice.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        mBinding.rvChoice.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return HelpandSupportFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
    public static HelpandSupportFragment newInstance(int instance){
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        HelpandSupportFragment fragment = new HelpandSupportFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
