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
import com.app.community.network.response.dashboard.meeting.ProductResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.LocationViewHolder> {
    private final LayoutInflater mInflator;
    private final AppCompatActivity activity;
    private ArrayList<ProductResponse> productList;
    private ProductClickListener itemClickListener;

    public void setOnItemClick(ProductClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public interface ProductClickListener{
        void onItemClick(int adapterPosition);
    }

    public ProductAdapter(AppCompatActivity activity){
        this.activity=activity;
        mInflator=LayoutInflater.from(activity);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       ItemProductRowBinding mBinding= DataBindingUtil.inflate(mInflator, R.layout.item_product_row,parent,false);
        return new LocationViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
       if(CommonUtils.isNotNull(productList)&&productList.size()>position){
           ProductResponse response=productList.get(position);
           GlideUtils.loadImage(activity,response.getLogo(),holder.imageView,null,0);
           holder.tvProductName.setText(response.getName());
           holder.tvLocation.setText(response.getAddress());

       }

    }

    @Override
    public int getItemCount() {
        return productList !=null?productList.size():0;
    }

    public void setLocationList(ArrayList<ProductResponse> productList) {
        this.productList =productList;
        notifyDataSetChanged();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ImageView imageView;
        private final CustomTextView tvProductName;
        private final CustomTextView tvLocation;

        public LocationViewHolder(ItemProductRowBinding itemView) {
            super(itemView.getRoot());
            imageView=itemView.ivProductImage;
            tvProductName=itemView.tvProductName;
            tvLocation=itemView.tvLocation;
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(itemClickListener) && CommonUtils.isNotNull(productList) && productList.size() > getAdapterPosition()) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
