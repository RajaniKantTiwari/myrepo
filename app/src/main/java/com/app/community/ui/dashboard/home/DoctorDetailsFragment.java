package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentDoctorDetailBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.dashboardinside.AppointMentResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.AppointmentAdapter;

import java.util.ArrayList;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class DoctorDetailsFragment extends DashboardFragment {

    private FragmentDoctorDetailBinding mBinding;
    private AppointmentAdapter mAdapter;
    private ArrayList<AppointMentResponse> appointmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_detail,container,false);
        appointmentList=new ArrayList<>();
        initializeAdapter();
        return mBinding.getRoot();
    }


    private void initializeAdapter() {
        mAdapter=new AppointmentAdapter(getDashboardActivity());
        setList();
        mAdapter.setList(appointmentList);
        GridLayoutManager layoutManager=new GridLayoutManager(getDashboardActivity(),3);
        mBinding.rvAppointmentTime.setLayoutManager(layoutManager);
        mBinding.rvAppointmentTime.setAdapter(mAdapter);
    }


    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
      mBinding.tvAppointment.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {

    }


    private void setList() {
        for(int i=0;i<10;i++){
            AppointMentResponse response=new AppointMentResponse();
            appointmentList.add(response);
        }
    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvAppointment){
            Bundle bundle=new Bundle();
            getDashboardActivity().addFragmentInContainer(new DoctorCheckoutFragment(), bundle, true, true, NONE);
        }

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
