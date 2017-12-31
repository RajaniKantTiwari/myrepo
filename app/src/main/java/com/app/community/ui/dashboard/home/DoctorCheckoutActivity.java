package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityDoctorDetailBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.dashboardinside.AppointMentResponse;
import com.app.community.ui.dashboard.DashboardInsideActivity;
import com.app.community.ui.dashboard.home.adapter.AppointmentAdapter;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/12/17.
 */

public class DoctorCheckoutActivity extends DashboardInsideActivity {

    private ActivityDoctorDetailBinding mBinding;
    private AppointmentAdapter mAdapter;
    private ArrayList<AppointMentResponse> appointmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_detail);
        appointmentList=new ArrayList<>();
    }


    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initializeData() {
        mAdapter=new AppointmentAdapter(this);
        setList();
        mAdapter.setList(appointmentList);
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
