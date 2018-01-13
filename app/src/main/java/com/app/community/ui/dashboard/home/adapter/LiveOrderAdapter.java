package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.OrderRowItemBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class LiveOrderAdapter extends RecyclerView.Adapter<LiveOrderAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private OrderRowItemBinding mBinding;

    public LiveOrderAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.order_row_item, parent, false);
        return new CartHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class CartHolder extends RecyclerView.ViewHolder{
       public CartHolder(View itemView) {
           super(itemView);
       }
   }
}
