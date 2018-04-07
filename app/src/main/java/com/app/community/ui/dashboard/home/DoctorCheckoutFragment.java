package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentDoctorCheckoutBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.dashboardinside.AppointMentResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.AppointmentAdapter;
import com.app.community.ui.dashboard.home.fragment.AppointmentConfirmedFragment;

import java.util.ArrayList;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class DoctorCheckoutFragment extends DashboardFragment {

    private FragmentDoctorCheckoutBinding mBinding;
    private AppointmentAdapter mAdapter;
    private ArrayList<AppointMentResponse> appointmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_checkout, container, false);
        appointmentList = new ArrayList<>();
        initializeData();
        setListener();
        return mBinding.getRoot();
    }


    @Override
    public void attachView() {

    }

    public void setListener() {
        mBinding.tvProceedToPay.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return DoctorCheckoutFragment.class.getSimpleName();
    }

    public void initializeData() {
        mAdapter = new AppointmentAdapter(getDashboardActivity());
        setList();
        mAdapter.setList(appointmentList);
    }

    private void setList() {
        for (int i = 0; i < 10; i++) {
            AppointMentResponse response = new AppointMentResponse();
            appointmentList.add(response);
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        getDashboardActivity().addFragmentInContainer(new AppointmentConfirmedFragment(), bundle, true, true, NONE);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
