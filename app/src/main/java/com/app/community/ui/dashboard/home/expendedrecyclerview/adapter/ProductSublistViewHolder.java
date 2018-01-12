package com.app.community.ui.dashboard.home.expendedrecyclerview.adapter;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.ListItemSubproductBinding;
import com.app.community.ui.dashboard.home.expendedrecyclerview.holder.ChildViewHolder;
import com.app.community.utils.CommonUtils;
import com.app.community.widget.CustomTextView;


public class ProductSublistViewHolder extends ChildViewHolder {

    private final ListItemSubproductBinding mBinding;
    public final LinearLayout layoutChild;
    public final CustomTextView tvSubProductName;

    public ProductSublistViewHolder(ListItemSubproductBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
        layoutChild=mBinding.layoutChild;
        tvSubProductName=mBinding.tvSubProductName;
    }

    public void setArtistName(String name) {
        mBinding.tvSubProductName.setText(name);
    }
}
