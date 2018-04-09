package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.CheckoutRowItemBinding;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.dashboardinside.ProductResponse;
import com.app.community.network.response.dashboard.dashboardinside.TotalTax;
import com.app.community.utils.CommonUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class CheckoutCartAdapter extends RecyclerView.Adapter<CheckoutCartAdapter.CheckoutHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private CheckoutRowItemBinding mBinding;
    private ArrayList<ProductResponse> cartList;
    private TotalTax total;

    public CheckoutCartAdapter(AppCompatActivity activity) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public CheckoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.checkout_row_item, parent, false);
        return new CheckoutHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CheckoutHolder holder, int position) {
        if (CommonUtils.isNotNull(cartList)) {
            if (position >= cartList.size()) {
                otherCharge(holder, position);
            } else {
                ProductResponse data = cartList.get(position);
                holder.tvProductName.setText(data.getProductname() + "(" + data.getQuantity() + ")");
                int total = data.getQuantity() * data.getProduct_mrp();
                holder.tvProductPrice.setText(String.valueOf(total));
                holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity, R.color.color_black));
            }
        }
    }

    private void otherCharge(CheckoutHolder holder, int position) {
        if (position == cartList.size()) {
            int subTotal = 0;
            for (int i = 0; i < cartList.size(); i++) {
                ProductResponse data = cartList.get(i);
                subTotal = subTotal + data.getQuantity() * data.getProduct_mrp();
            }
            holder.tvProductName.setText(activity.getResources().getString(R.string.sub_total));
            holder.tvProductPrice.setText(String.valueOf(subTotal));
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity, R.color.color_black));
        } else if (position == cartList.size() + 1) {
            /*int tax=0;
            for(int i=0;i<cartList.size();i++) {
                ProductResponse data = cartList.get(i);
                tax = tax + data.getTax();
            }*/
            holder.tvProductName.setText(activity.getResources().getString(R.string.tax));
/*
            holder.tvProductPrice.setText(String.valueOf(tax));
*/
            if(CommonUtils.isNotNull(total)){
                holder.tvProductPrice.setText(total.getTotaltax());
            }

            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity, R.color.color_black));
        } else if (position == cartList.size() + 2) {
            /*int shipingCharge=0;
            for(int i=0;i<cartList.size();i++){
                ProductResponse data=cartList.get(i);
                shipingCharge=shipingCharge+data.getShipping();
            }*/
            holder.tvProductName.setText(activity.getResources().getString(R.string.shipping_charge));
            //holder.tvProductPrice.setText(String.valueOf(shipingCharge));
            holder.tvProductPrice.setText(String.valueOf(cartList.get(0).getShipping()));
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity, R.color.color_black));
        } else if (position == cartList.size() + 3) {
            int subTotal = 0;
            int tax = 0;
            int shipingCharge = 0;
            for (int i = 0; i < cartList.size(); i++) {
                ProductResponse data = cartList.get(i);
                subTotal = subTotal + data.getQuantity() * data.getProduct_mrp();
                tax = tax + data.getTax();
                shipingCharge = shipingCharge + data.getShipping();
            }
            int totalAmount = subTotal + tax + shipingCharge;
            holder.tvProductName.setText(activity.getResources().getString(R.string.total_amount));
            holder.tvProductPrice.setText(String.valueOf(totalAmount));
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity, R.color.color_sky_blue));
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(cartList) ? cartList.size() + 4 : 0;
    }

    public void setCartList(ArrayList<ProductResponse> cartList, TotalTax total) {
        this.cartList = cartList;
        this.total = total;
        notifyDataSetChanged();
    }

    class CheckoutHolder extends RecyclerView.ViewHolder {
        private final CustomTextView tvProductPrice;
        private final CustomTextView tvProductName;

        public CheckoutHolder(CheckoutRowItemBinding itemView) {
            super(itemView.getRoot());
            tvProductPrice = itemView.tvProductPrice;
            tvProductName = itemView.tvProductName;
        }
    }
}
