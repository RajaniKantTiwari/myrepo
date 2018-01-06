package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityConfirmOrderBinding;
import com.app.community.databinding.ActivityDoctorDetailBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.dashboardinside.AppointMentResponse;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.DashboardInsideActivity;
import com.app.community.ui.dashboard.home.adapter.AppointmentAdapter;

import java.util.ArrayList;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class DoctorDetailsActivity extends DashboardInsideActivity {

    private ActivityDoctorDetailBinding mBinding;
    private AppointmentAdapter mAdapter;
    private ArrayList<AppointMentResponse> appointmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_detail);
        appointmentList=new ArrayList<>();
        initializeAdapter();
    }

    private void initializeAdapter() {
        mAdapter=new AppointmentAdapter(this);
        setList();
        mAdapter.setList(appointmentList);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        mBinding.rvAppointmentTime.setLayoutManager(layoutManager);
        mBinding.rvAppointmentTime.setAdapter(mAdapter);
    }


    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initializeData() {

    }

    private void setList() {
        for(int i=0;i<10;i++){
            AppointMentResponse response=new AppointMentResponse();
            appointmentList.add(response);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
