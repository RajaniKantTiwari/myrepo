package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.CheckoutRowItemBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class CheckoutCartAdapter extends RecyclerView.Adapter<CheckoutCartAdapter.CheckoutHolder> {
    private final LayoutInflater mInflater;
    private CheckoutRowItemBinding mBinding;

    public CheckoutCartAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public CheckoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.checkout_row_item, parent, false);
        return new CheckoutHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(CheckoutHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class CheckoutHolder extends RecyclerView.ViewHolder{
       public CheckoutHolder(View itemView) {
           super(itemView);
       }
   }
}
