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
        void onContactClick(int adapterPosition);

        void onMessageClick(int adapterPosition);
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
           if(position%2==0){
              holder.tvDiscount.setBackgroundColor(CommonUtils.getColor(activity,R.color.greyish_color));
              holder.tvDiscount.setText("");
           }else{
               holder.tvDiscount.setBackgroundColor(CommonUtils.getColor(activity,R.color.light_green));
           }

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
        private final CustomTextView tvContact;
        private final CustomTextView tvView;
        private final CustomTextView tvDiscount;

        public LocationViewHolder(ItemProductRowBinding itemView) {
            super(itemView.getRoot());
            imageView=itemView.ivProductImage;
            tvProductName=itemView.tvProductName;
            tvLocation=itemView.tvLocation;
            tvContact=itemView.tvContact;
            tvView=itemView.tvView;
            tvDiscount=itemView.tvDiscount;
            imageView.setOnClickListener(this);
            tvContact.setOnClickListener(this);
            tvView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if (CommonUtils.isNotNull(itemClickListener) && CommonUtils.isNotNull(productList) && productList.size() > getAdapterPosition()) {
                if(view==imageView||view==tvProductName||view==tvLocation){
                    itemClickListener.onItemClick(getAdapterPosition());
                }else if(view==tvContact){
                    CommonUtils.clicked(tvContact);
                    itemClickListener.onContactClick(getAdapterPosition());
                }else if(view==tvView){
                    CommonUtils.clicked(tvView);
                    itemClickListener.onMessageClick(getAdapterPosition());
                }
            }
        }
    }
}
