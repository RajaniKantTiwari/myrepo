package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.CheckoutRowItemBinding;
import com.app.community.utils.CommonUtils;
import com.app.community.widget.CustomTextView;

/**
 * Created by ashok on 25/12/17.
 */

public class CheckoutCartAdapter extends RecyclerView.Adapter<CheckoutCartAdapter.CheckoutHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private CheckoutRowItemBinding mBinding;

    public CheckoutCartAdapter(AppCompatActivity activity){
        this.activity=activity;
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public CheckoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.checkout_row_item, parent, false);
        return new CheckoutHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CheckoutHolder holder, int position) {
        if(position==4){
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_sky_blue));
        }else{
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_black));
        }

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class CheckoutHolder extends RecyclerView.ViewHolder{
        private final CustomTextView tvProductPrice;

        public CheckoutHolder(CheckoutRowItemBinding itemView) {
           super(itemView.getRoot());
           tvProductPrice=itemView.tvProductPrice;
       }
   }
}
