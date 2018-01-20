package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.app.community.R;
import com.app.community.databinding.ItemProductRowBinding;
import com.app.community.network.response.dashboard.feed.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.MerchantViewHolder> {
    private final LayoutInflater mInflator;
    private final AppCompatActivity activity;
    private ArrayList<MerchantResponse> merchantList;
    private MerchantClickListener itemClickListener;

    public void setOnItemClick(MerchantClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface MerchantClickListener {
        void onItemClick(int adapterPosition);

        void onContactClick(int adapterPosition);

        void onViewClick(int adapterPosition);
    }

    public MerchantAdapter(AppCompatActivity activity) {
        this.activity = activity;
        mInflator = LayoutInflater.from(activity);
    }

    @Override
    public MerchantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductRowBinding mBinding = DataBindingUtil.inflate(mInflator, R.layout.item_product_row, parent, false);
        return new MerchantViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(MerchantViewHolder holder, int position) {
        if (CommonUtils.isNotNull(merchantList) && merchantList.size() > position) {
            MerchantResponse response = merchantList.get(position);
            GlideUtils.loadImage(activity, response.getLogo(), holder.imageView, null, 0);
            if (CommonUtils.isNotNull(response)) {
                if (CommonUtils.isNotNull(response.getRating())) {
                    holder.tvStar.setVisibility(View.VISIBLE);
                } else {
                    holder.tvStar.setVisibility(View.INVISIBLE);
                }
                if (CommonUtils.isNotNull(response.getOffer())) {
                    holder.tvDiscount.setBackgroundColor(CommonUtils.getColor(activity, R.color.light_green));
                } else {
                    holder.tvDiscount.setBackgroundColor(CommonUtils.getColor(activity, R.color.greyish_color));
                }
                holder.setData(response);
            }


        }

    }

    @Override
    public int getItemCount() {
        return merchantList != null ? merchantList.size() : 0;
    }

    public void setLocationList(ArrayList<MerchantResponse> productList) {
        this.merchantList = productList;
        notifyDataSetChanged();
    }

    class MerchantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CustomTextView tvContact;
        private final CustomTextView tvView;
        private final CustomTextView tvStar;
        private final ImageView imageView;
        private final CustomTextView tvDiscount;

        public MerchantViewHolder(ItemProductRowBinding itemView) {
            super(itemView.getRoot());
            imageView = itemView.ivProductImage;
            tvContact = itemView.tvContact;
            tvView = itemView.tvView;
            tvStar = itemView.tvStar;
            tvDiscount = itemView.tvDiscount;
            itemView.ivProductImage.setOnClickListener(this);
            itemView.tvProductName.setOnClickListener(this);
            itemView.tvLocation.setOnClickListener(this);
            itemView.layoutProductType.setOnClickListener(this);
            itemView.tvContact.setOnClickListener(this);
            itemView.tvView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(itemClickListener) && CommonUtils.isNotNull(merchantList) && merchantList.size() > getAdapterPosition()) {
                if (view == tvContact) {
                    CommonUtils.clicked(tvContact);
                    itemClickListener.onContactClick(getAdapterPosition());
                } else if (view == tvView) {
                    CommonUtils.clicked(tvView);
                    itemClickListener.onViewClick(getAdapterPosition());
                } else {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            }
        }

        public void setData(MerchantResponse response) {
            //itemView.setMerchantResponse(response);
        }
    }
}
