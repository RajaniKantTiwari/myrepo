package com.app.community.ui.dashboard.offer.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.community.R;
import com.app.community.databinding.OfferRowBinding;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.network.response.dashboard.offer.MerchantOffer;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;
import com.app.community.widget.CustomTextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private OfferRowBinding mBinding;
    private OfferListener listener;
    private ArrayList<MerchantOffer> offersList;

    public interface OfferListener {
        void onOfferClicked(int position);
    }

    public OfferAdapter(AppCompatActivity activity, ArrayList<MerchantOffer> offersList, OfferListener listener) {
        this.activity=activity;
        mInflater = LayoutInflater.from(activity);
        this.offersList = offersList;
        this.listener = listener;
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.offer_row, parent, false);
        return new OfferViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
        if (CommonUtils.isNotNull(offersList) && offersList.size() > position) {
            MerchantOffer offer = offersList.get(position);
            if (CommonUtils.isNotNull(offer)) {
                holder.tvProductName.setText(offer.getProduct_name());
                holder.tvMrp.setText(activity.getResources().getString(R.string.mrp_rs)+offer.getProduct_mrp());
                holder.tvPrice.setText(activity.getResources().getString(R.string.mrp_rs)+offer.getSelling_price());
                holder.tvMerchantName.setText(offer.getStore_name());
                holder.tvOff.setText(offer.getOffer_percent()+"% OFF");
                GlideUtils.loadImageTwoRoundedCorner(activity,offer.getLogo(),holder.ivOffer,
                        null,0,15,0,0,15);
            }
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(offersList) ? offersList.size() : 0;
    }

    class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout layoutOffer;
        private final CustomTextView tvProductName;
        private final CustomTextView tvMrp;
        private final ImageView ivOffer;
        private final CustomTextView tvPrice;
        private final CustomTextView tvMerchantName;
        private final CustomTextView tvOff;

        public OfferViewHolder(OfferRowBinding itemView) {
            super(itemView.getRoot());
            layoutOffer = itemView.layoutOffer;
            tvProductName = itemView.tvProductName;
            tvMrp = itemView.tvMrp;
            ivOffer=itemView.ivOffer;
            tvPrice=itemView.tvPrice;
            tvOff=itemView.tvOff;
            tvMerchantName=itemView.tvMerchantName;
            itemView.layoutOffer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(listener)) {
                listener.onOfferClicked(getAdapterPosition());
            }
        }
    }
}
