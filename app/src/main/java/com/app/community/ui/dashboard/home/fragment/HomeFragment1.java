/*
package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentHomeBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.ProductDetailsActivity;
import com.app.community.ui.presenter.AuthenticationPresenter;
import com.app.community.utils.ApiConstants;
import com.app.community.utils.ExplicitIntent;

import javax.inject.Inject;

*/
/**
 * Created by ashok on 13/11/17.
 *//*


public class HomeFragment1 extends DashboardFragment {
    @Inject
    AuthenticationPresenter presenter;
    private FragmentHomeBinding mBinding;
    public static HomeFragment1 newInstance(){
        return new HomeFragment1();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
     mBinding.tvProductDetails.setOnClickListener(this);
     mBinding.tvCheckoutFragment.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvProductDetails){
            ExplicitIntent.getsInstance().navigateTo(getBaseActivity(),ProductDetailsActivity.class);
        }else if(view==mBinding.tvCheckoutFragment){
            getBaseActivity().pushChildFragment(getChildFragmentManager(), ApiConstants.FRAGMENTS.CHECKOUT_FRAGMENT,null,
                    R.id.container,false,true, BaseActivity.AnimationType.NONE);
            //android.R.id.content
        }
    }
}
*/
