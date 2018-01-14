package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentConfirmOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.home.fragment.HelpAndSupportFragment;
import com.app.community.utils.CommonUtils;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class ConfirmOrderFragment extends BaseFragment {

    private FragmentConfirmOrderBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_order, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {
        mBinding.tvRaiseAnIssue.setOnClickListener(this);
        mBinding.tvHome.setOnClickListener(this);

    }

    @Override
    public String getFragmentName() {
        return ConfirmOrderFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvRaiseAnIssue) {
            CommonUtils.clicked(mBinding.tvRaiseAnIssue);
            mFragmentNavigation.pushFragment(HelpAndSupportFragment.newInstance(mInt + 1));
        } else if (view == mBinding.tvHome) {
            CommonUtils.clicked(mBinding.tvHome);
            mFragmentNavigation.popFragment();
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
