package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.community.R;
import com.app.community.databinding.ItemImageBinding;
import com.app.community.network.response.dashboard.home.StoreImages;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.StoreViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ItemImageBinding mBinding;
    private ArrayList<StoreImages> imageList;
    private ImageListener listener;

    public interface ImageListener {

        void onItemClick(int adapterPosition);
    }

    public ImageAdapter(AppCompatActivity activity, ArrayList<StoreImages> imageList, ImageListener listener) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
        this.listener = listener;
        this.imageList=imageList;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.item_image, parent, false);
        return new StoreViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        if (CommonUtils.isNotNull(imageList) && imageList.size() > position) {
            GlideUtils.loadImage(activity, imageList.get(position).getPath(), holder.ivStoreImage, null, 0);
        }
    }

    @Override
    public int getItemCount() {
        return imageList != null ? imageList.size() : 0;
    }
    class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView ivStoreImage;

        public StoreViewHolder(ItemImageBinding itemView) {
            super(itemView.getRoot());
            ivStoreImage = itemView.ivStoreImage;
            ivStoreImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(listener)) {
                listener.onItemClick(getAdapterPosition());
            }

        }
    }
}
