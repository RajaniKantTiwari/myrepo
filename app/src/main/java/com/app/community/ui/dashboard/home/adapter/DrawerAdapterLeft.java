package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.DrawerLeftRowItemBinding;
import com.app.community.databinding.ItemImageBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class DrawerAdapterLeft extends RecyclerView.Adapter<DrawerAdapterLeft.StoreViewHolder> {
    private final LayoutInflater mInflater;
    private DrawerLeftRowItemBinding mBinding;

    public DrawerAdapterLeft(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.drawer_left_row_item, parent, false);
        return new StoreViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class StoreViewHolder extends RecyclerView.ViewHolder{
       public StoreViewHolder(View itemView) {
           super(itemView);
       }
   }
}
