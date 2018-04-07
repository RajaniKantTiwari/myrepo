package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.community.R;
import com.app.community.databinding.ItemDoctorRowBinding;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.LocationViewHolder> {
    private final LayoutInflater mInflator;
    private final AppCompatActivity activity;
    private DoctorClickListener itemClickListener;

    public void setOnItemClick(DoctorClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface DoctorClickListener {
        void onView(int position);

        void onBook(int position);
    }

    public DoctorAdapter(AppCompatActivity activity) {
        this.activity = activity;
        mInflator = LayoutInflater.from(activity);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDoctorRowBinding mBinding = DataBindingUtil.inflate(mInflator, R.layout.item_doctor_row, parent, false);
        return new LocationViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return  5;
    }



    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;
        private final CustomTextView tvProductName;
        private final CustomTextView tvLocation;
        private final CustomTextView tvBook;
        private final CustomTextView tvView;
        private final ItemDoctorRowBinding mBinding;

        public LocationViewHolder(ItemDoctorRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            imageView = itemView.ivProductImage;
            tvProductName = itemView.tvProductName;
            tvLocation = itemView.tvLocation;
            tvBook = itemView.tvBook;
            tvView = itemView.tvView;
            tvBook.setOnClickListener(this);
            tvView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (view == mBinding.tvView) {
                itemClickListener.onView(getAdapterPosition());
            } else if (view == mBinding.tvBook) {
                  itemClickListener.onBook(getAdapterPosition());
            }
        }
    }
}
