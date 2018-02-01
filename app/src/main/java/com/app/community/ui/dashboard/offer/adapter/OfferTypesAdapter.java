package com.app.community.ui.dashboard.offer.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.databinding.OfferItemsRowBinding;
import com.app.community.databinding.OfferRowBinding;
import com.app.community.network.response.dashboard.offer.OfferType;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class OfferTypesAdapter extends RecyclerView.Adapter<OfferTypesAdapter.OfferTypeViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<OfferType> offerTypeList;
    private final AppCompatActivity activity;
    private OfferItemsRowBinding mBinding;
    private OfferTypeListener listener;

    public interface OfferTypeListener {
        void onOfferTypeItemClicked(int adapterPosition);
    }

    public OfferTypesAdapter(AppCompatActivity activity, ArrayList<OfferType> offerTypeList, OfferTypeListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
        this.offerTypeList = offerTypeList;
    }

    @Override
    public OfferTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.offer_items_row, parent, false);
        return new OfferTypeViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OfferTypeViewHolder holder, int position) {
        if (CommonUtils.isNotNull(offerTypeList) && offerTypeList.size() > position) {
            OfferType type = offerTypeList.get(position);
            if (type.isSelected()) {
                holder.tvOfferType.setTextColor(CommonUtils.getColor(activity, R.color.deal_color));
            } else {
                holder.tvOfferType.setTextColor(CommonUtils.getColor(activity, R.color.search_bg_color));
            }
            holder.tvOfferType.setText(type.getOfferType());
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(offerTypeList) ? offerTypeList.size() : 0;
    }

    class OfferTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvOfferType;

        public OfferTypeViewHolder(OfferItemsRowBinding itemView) {
            super(itemView.getRoot());
            tvOfferType = itemView.tvOfferType;
            itemView.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(listener)) {
                listener.onOfferTypeItemClicked(getAdapterPosition());
            }
        }
    }
}
