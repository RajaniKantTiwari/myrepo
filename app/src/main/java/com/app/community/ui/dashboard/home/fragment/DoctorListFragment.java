package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.FragmentDoctorListBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.DoctorDetailsFragment;
import com.app.community.ui.dashboard.home.MerchantDetailsFragment;
import com.app.community.ui.dashboard.home.adapter.DoctorAdapter;
import com.app.community.ui.dashboard.home.event.MerchantEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;

/**
 * Created by Amul on 27/12/17.
 */

public class DoctorListFragment extends DashboardFragment implements DoctorAdapter.DoctorClickListener {
    private FragmentDoctorListBinding mBinding;
    private DoctorAdapter mAdapter;
    private ArrayList<MerchantResponse> doctorsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CommonUtils.register(this);
        getDashboardActivity().showToast("DoctorListFragment called");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_list, container, false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        mAdapter = new DoctorAdapter(getBaseActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvDoctor.setLayoutManager(layoutManager);
        mBinding.rvDoctor.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return null;
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(MerchantEvent event) {
        if (event.getListMap() == GeneralConstant.LIST_PRODUCT) {
            mBinding.layoutList.setVisibility(View.VISIBLE);
            doctorsList = event.getProductList();
        } else if (event.getListMap() == GeneralConstant.MAP_PRODUCT) {
            mBinding.layoutList.setVisibility(View.GONE);

        }
    }

    public static Fragment newInstance() {
        return new DoctorListFragment();
    }

    @Override
    public void onView(int position) {

    }

    @Override
    public void onBook(int position) {
        Bundle bundle=new Bundle();
        getDashboardActivity().addFragmentInContainer(new DoctorDetailsFragment(), bundle, true, true, NONE);

    }
}
