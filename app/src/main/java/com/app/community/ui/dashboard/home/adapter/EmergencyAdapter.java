package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.community.R;
import com.app.community.databinding.HelpPlaceRowBinding;
import com.app.community.network.response.dashboard.home.Emergency;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.EmergencyViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<Emergency> emergencyList;
    private final AppCompatActivity activity;
    private HelpPlaceRowBinding mBinding;
    private EmergencyListener listener;
    public interface EmergencyListener {

        void emergencyClicked(int adapterPosition);
    }
    public EmergencyAdapter(AppCompatActivity activity, ArrayList<Emergency> emergencyList, EmergencyListener listener){
        this.listener=listener;
        mInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.emergencyList=emergencyList;

    }
    @Override
    public EmergencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.help_place_row, parent, false);
        return new EmergencyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(EmergencyViewHolder holder, int position) {
        if(CommonUtils.isNotNull(emergencyList)&&emergencyList.size()>position){
            holder.setData(emergencyList.get(position));
            GlideUtils.loadImage(activity,emergencyList.get(position).getIcon(),holder.ivPlace,null,R.drawable.icon_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(emergencyList)?emergencyList.size():0;
    }
    class EmergencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView ivPlace;
        private final HelpPlaceRowBinding itmView;

        public EmergencyViewHolder(HelpPlaceRowBinding itemView) {
           super(itemView.getRoot());
           itmView=itemView;
            ivPlace=itemView.ivPlace;
           itemView.ivPlace.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(CommonUtils.isNotNull(listener)){
                CommonUtils.clicked(itmView.ivPlace);
                listener.emergencyClicked(getAdapterPosition());
            }
        }

        public void setData(Emergency emergency) {
            itmView.setEmergency(emergency);
        }
    }
}
