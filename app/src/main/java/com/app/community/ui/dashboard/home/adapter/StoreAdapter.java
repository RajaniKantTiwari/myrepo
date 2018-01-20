package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.community.R;
import com.app.community.databinding.ItemImageBinding;
import com.app.community.network.response.dashboard.feed.StoreImages;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private ItemImageBinding mBinding;
    private ArrayList<StoreImages> imageList;

    public StoreAdapter(AppCompatActivity activity){
        this.activity=activity;
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.item_image, parent, false);
        return new StoreViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
      if(CommonUtils.isNotNull(imageList)&&imageList.size()>position){
          GlideUtils.loadImage(activity,imageList.get(position).getPath(),holder.ivStoreImage,null,0);
      }
    }

    @Override
    public int getItemCount() {
        return imageList!=null?imageList.size():0;
    }

    public void setImageList(ArrayList<StoreImages> imageList) {
        this.imageList=imageList;
    }

    class StoreViewHolder extends RecyclerView.ViewHolder{
        private final ImageView ivStoreImage;

        public StoreViewHolder(ItemImageBinding itemView) {
           super(itemView.getRoot());
           ivStoreImage=itemView.ivStoreImage;
       }
   }
}
