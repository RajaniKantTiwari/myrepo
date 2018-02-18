package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.LatestNewsRowItemBinding;
import com.app.community.databinding.OffersRowBinding;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final ArrayList<Offer> offersList;
    private OffersListener listener;

    public interface OffersListener {
        void offersItemClick(int adapterPosition);
    }

    public OffersAdapter(AppCompatActivity activity, ArrayList<Offer> offersList, OffersListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.offersList = offersList;
        this.listener = listener;
    }

    @Override
    public OffersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OffersRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.offers_row, parent, false);
        return new OffersViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {
        if (CommonUtils.isNotNull(offersList) && offersList.size() > position) {
            holder.setOfferData(offersList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(offersList) ? offersList.size() : 0;
    }

    class OffersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OffersRowBinding mBinding;
        public OffersViewHolder(OffersRowBinding itemView) {
            super(itemView.getRoot());
            mBinding=itemView;
            itemView.layoutOffer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.offersItemClick(getAdapterPosition());
            }
        }

        public void setOfferData(Offer offer) {
            try {
                mBinding.cvLayout.setCardBackgroundColor(CommonUtils.getColor(offer.getOffer_bg_color()));
                mBinding.setOffer(offer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
