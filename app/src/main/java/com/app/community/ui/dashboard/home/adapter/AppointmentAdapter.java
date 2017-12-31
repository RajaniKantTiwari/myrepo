package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.databinding.AppointmentRowItemBinding;
import com.app.community.network.response.dashboard.dashboardinside.AppointMentResponse;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder> {
    private final LayoutInflater mInflater;
    private AppointmentRowItemBinding mBinding;
    private ArrayList<AppointMentResponse> appointmentList;

    public AppointmentAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public AppointmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.appointment_row_item, parent, false);
        return new AppointmentHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(AppointmentHolder holder, int position) {
       if(CommonUtils.isNotNull(appointmentList)&&appointmentList.size()>position){
           AppointMentResponse response = appointmentList.get(position);
           if(response.isChecked()){
               holder.tvAppointment.setBackgroundResource(R.drawable.green_bg);
           }else {
               holder.tvAppointment.setBackgroundResource(R.drawable.stroke_green);
           }
       }
    }

    @Override
    public int getItemCount() {
        return appointmentList!=null?appointmentList.size():0;
    }

    public void setList(ArrayList<AppointMentResponse> appointmentList) {
        this.appointmentList=appointmentList;
        notifyDataSetChanged();
    }

    class AppointmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvAppointment;
       public AppointmentHolder(AppointmentRowItemBinding itemView) {
           super(itemView.getRoot());
           tvAppointment=itemView.tvAppointment;
           tvAppointment.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            AppointMentResponse response = appointmentList.get(getAdapterPosition());
            response.setChecked(!response.isChecked());
            appointmentList.set(getAdapterPosition(),response);
            notifyDataSetChanged();
        }
    }
}
