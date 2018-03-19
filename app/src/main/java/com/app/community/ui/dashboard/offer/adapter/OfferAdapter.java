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
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.utils.CommonUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private final LayoutInflater mInflater;
    private OfferRowBinding mBinding;
    private OfferListener listener;
    private ArrayList<Offer> offersList;

    public interface OfferListener {
        void onOfferClicked(int position);
    }

    public OfferAdapter(AppCompatActivity activity, ArrayList<Offer> offersList, OfferListener listener) {
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
            Offer offer = offersList.get(position);
            if (CommonUtils.isNotNull(offer)) {
                holder.tvStoreName.setText(offer.getStore_name());
                holder.tvOffer.setText(offersList.get(position).getOffer_descr());
            }
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(offersList) ? offersList.size() : 0;
    }

    class OfferViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final LinearLayout layoutProduct;
        private final CustomTextView tvOffer;
        private final CustomTextView tvStoreName;

        public OfferViewHolder(OfferRowBinding itemView) {
            super(itemView.getRoot());
            layoutProduct = itemView.layoutProduct;
            tvOffer = itemView.tvOffer;
            tvStoreName = itemView.tvStoreName;
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
