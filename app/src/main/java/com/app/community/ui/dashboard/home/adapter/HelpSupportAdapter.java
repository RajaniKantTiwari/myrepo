package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.CartRowItemBinding;
import com.app.community.databinding.HelpSupportRowBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class HelpSupportAdapter extends RecyclerView.Adapter<HelpSupportAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private HelpSupportRowBinding mBinding;

    public HelpSupportAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.help_support_row, parent, false);
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
