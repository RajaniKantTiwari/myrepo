package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.OrderRowItemBinding;
import com.app.community.network.response.Order;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ashok on 25/12/17.
 */

public class LiveOrderAdapter extends RecyclerView.Adapter<LiveOrderAdapter.LiveOrderHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ArrayList<Order> orderList;
    private OrderListener listener;

    public interface OrderListener{

        void viewDetailsClick(int position);

        void helpClick(int position);

        void feedBackClicked(int position);
    }

    public LiveOrderAdapter(AppCompatActivity activity,OrderListener listener,ArrayList<Order> orderList) {
        this.activity=activity;
        mInflater = LayoutInflater.from(activity);
        this.orderList=orderList;
        this.listener=listener;
    }

    @Override
    public LiveOrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderRowItemBinding mBinding= DataBindingUtil.inflate(mInflater, R.layout.order_row_item, parent, false);
        return new LiveOrderHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LiveOrderHolder holder, int position) {
        holder.setOrderData(position);
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(orderList) ? orderList.size() : 0;
    }

    class LiveOrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OrderRowItemBinding itemView;

        public LiveOrderHolder(OrderRowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            itemView.tvViewDetails.setOnClickListener(this);
            itemView.tvFeedBack.setOnClickListener(this);
            itemView.tvHelp.setOnClickListener(this);

        }

        public void setOrderData(int position) {
            Order order = orderList.get(position);
            if (CommonUtils.isNotNull(order)) {
                itemView.layoutFirst.tvStoreName.setText(order.getStore_name());
                itemView.layoutFirst.tvTotal.setText(order.getGrandtotal());
                itemView.layoutSecond.tvOrderDate.setText(order.getInvoiceDate());
                itemView.layoutSecond.tvOrderNumber.setText(order.getInvoiceNumber());
                if (CommonUtils.isNotNull(order.getOrder_status()) && order.getOrder_status().length() > 0) {
                    itemView.tvOrderStatus.setText(order.getOrder_status());
                }
                if (CommonUtils.isNotNull(order.getRating())&&!order.getRating().equalsIgnoreCase("0")) {
                    itemView.ratingBar.setVisibility(View.VISIBLE);
                    try {
                        itemView.ratingBar.setRating(Float.parseFloat(order.getRating()));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    itemView.tvFeedBack.setVisibility(View.GONE);
                } else {
                    itemView.ratingBar.setVisibility(View.GONE);
                    itemView.tvFeedBack.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onClick(View view) {
            if(CommonUtils.isNotNull(listener)){
                if(view==itemView.tvViewDetails){
                    CommonUtils.clicked(itemView.tvViewDetails);
                    listener.viewDetailsClick(getAdapterPosition());
                }else if(view==itemView.tvHelp){
                    CommonUtils.clicked(itemView.tvHelp);
                    listener.helpClick(getAdapterPosition());
                }else if(view==itemView.tvFeedBack){
                    CommonUtils.clicked(itemView.tvFeedBack);
                    listener.feedBackClicked(getAdapterPosition());
                }
            }
        }
    }
}
