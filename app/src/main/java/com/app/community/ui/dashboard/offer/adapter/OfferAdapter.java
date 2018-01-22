package com.app.community.ui.dashboard.offer.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.community.R;
import com.app.community.databinding.CartRowItemBinding;
import com.app.community.databinding.OfferRowBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {
    private final LayoutInflater mInflater;
    private OfferRowBinding mBinding;

    public OfferAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.offer_row, parent, false);
        return new OfferViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(OfferViewHolder holder, int position) {
      if(position%2==0){
          holder.layoutProduct.setVisibility(View.VISIBLE);
      }else {
          holder.layoutProduct.setVisibility(View.GONE);
      }
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class OfferViewHolder extends RecyclerView.ViewHolder{
        private final LinearLayout layoutProduct;

        public OfferViewHolder(OfferRowBinding itemView) {
            super(itemView.getRoot());
            layoutProduct=itemView.layoutProduct;
        }
    }
}
