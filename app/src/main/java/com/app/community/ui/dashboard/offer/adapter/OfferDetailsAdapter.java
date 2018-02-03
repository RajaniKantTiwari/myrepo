package com.app.community.ui.dashboard.offer.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.community.R;
import com.app.community.databinding.OfferRowBinding;
import com.app.community.databinding.PaymentRowDetailsBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class OfferDetailsAdapter extends RecyclerView.Adapter<OfferDetailsAdapter.OfferViewHolder> {
    private final LayoutInflater mInflater;
    private PaymentRowDetailsBinding mBinding;

    public OfferDetailsAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.payment_row_details, parent, false);
        return new OfferViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class OfferViewHolder extends RecyclerView.ViewHolder{

        public OfferViewHolder(PaymentRowDetailsBinding itemView) {
            super(itemView.getRoot());
        }
    }
}
