package com.app.community.ui.dashboard.home.expendedrecyclerview.adapter;

import android.view.View;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.databinding.ListItemSubproductBinding;
import com.app.community.ui.dashboard.home.expendedrecyclerview.holder.ChildViewHolder;


public class ProductSublistViewHolder extends ChildViewHolder {


    private final ListItemSubproductBinding mBinding;

    public ProductSublistViewHolder(ListItemSubproductBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public void setArtistName(String name) {
        mBinding.tvSubProductName.setText(name);
    }
}
