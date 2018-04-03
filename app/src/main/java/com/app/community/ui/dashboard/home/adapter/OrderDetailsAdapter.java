package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.CheckoutRowItemBinding;
import com.app.community.network.response.dashboard.OrderData;
import com.app.community.utils.CommonUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.CheckoutHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private CheckoutRowItemBinding mBinding;
    private ArrayList<OrderData> orderList;

    public OrderDetailsAdapter(AppCompatActivity activity){
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
        if(CommonUtils.isNotNull(orderList)){
            if(position>=orderList.size()){
                otherCharge(holder,position);
            }else{
                OrderData data = orderList.get(position);
                holder.tvProductName.setText(data.getProductname()+"("+data.getQuantity()+")");
                int total= (int) (data.getQuantity()*data.getSelling_price());
                holder.tvProductPrice.setText(String.valueOf(total));
                holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_black));
            }
        }
    }

    private void otherCharge(CheckoutHolder holder,int position) {
        if(position==orderList.size()){
            int subTotal=0;
            for(int i=0;i<orderList.size();i++) {
                OrderData data=orderList.get(i);
                subTotal= (int) (subTotal+data.getQuantity()*data.getSelling_price());
            }
            holder.tvProductName.setText(activity.getResources().getString(R.string.sub_total));
            holder.tvProductPrice.setText(String.valueOf(subTotal));
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_black));
        }else if(position==orderList.size()+1){
            /*int tax=0;
            for(int i=0;i<cartList.size();i++) {
                ProductResponse data = cartList.get(i);
                tax = tax + data.getTax();
            }*/
            holder.tvProductName.setText(activity.getResources().getString(R.string.tax));
/*
            holder.tvProductPrice.setText(String.valueOf(tax));
*/            holder.tvProductPrice.setText(String.valueOf(orderList.get(0).getTax()));

            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_black));
        }else if(position==orderList.size()+2){
            /*int shipingCharge=0;
            for(int i=0;i<cartList.size();i++){
                ProductResponse data=cartList.get(i);
                shipingCharge=shipingCharge+data.getShipping();
            }*/
            holder.tvProductName.setText(activity.getResources().getString(R.string.shipping_charge));
            //holder.tvProductPrice.setText(String.valueOf(shipingCharge));
            holder.tvProductPrice.setText(String.valueOf(orderList.get(0).getShipping()));
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_black));
        }else if(position==orderList.size()+3){
            int subTotal=0;
            for(int i=0;i<orderList.size();i++){
                OrderData data=orderList.get(i);
                subTotal= (int) (subTotal+data.getQuantity()*data.getProduct_mrp());
            }
            int totalAmount= (int) (subTotal+orderList.get(0).getTax()+orderList.get(0).getShipping());
            holder.tvProductName.setText(activity.getResources().getString(R.string.total_amount));
            holder.tvProductPrice.setText(String.valueOf(totalAmount));
            holder.tvProductPrice.setTextColor(CommonUtils.getColor(activity,R.color.color_sky_blue));
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(orderList)?orderList.size()+4:0;
    }

    public void setCartList(ArrayList<OrderData> orderList) {
        this.orderList=orderList;
        notifyDataSetChanged();
    }

    class CheckoutHolder extends RecyclerView.ViewHolder{
        private final CustomTextView tvProductPrice;
        private final CustomTextView tvProductName;

        public CheckoutHolder(CheckoutRowItemBinding itemView) {
           super(itemView.getRoot());
           tvProductPrice=itemView.tvProductPrice;
            tvProductName=itemView.tvProductName;
       }
   }
}
