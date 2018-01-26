package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.CartRowItemBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private OnAddToCart listener;
    public interface OnAddToCart {
        void addToCartClick(int pos, View view);
    }
    public CartAdapter(AppCompatActivity activity, OnAddToCart listener){
        mInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.listener=listener;
    }
    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CartRowItemBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.cart_row_item, parent, false);
        return new CartHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class CartHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private CartRowItemBinding itemBinding;
       public CartHolder(CartRowItemBinding itemView) {
           super(itemView.getRoot());
           itemBinding=itemView;
           itemView.ivAdd.setOnClickListener(this);
           itemView.ivSub.setOnClickListener(this);

       }

        @Override
        public void onClick(View view) {
            View root = itemBinding.getRoot();
            root.setTag(view);
          if(view==itemBinding.ivAdd){
              listener.addToCartClick(getAdapterPosition(), root);
          }else if(view==itemBinding.ivSub){
              listener.addToCartClick(getAdapterPosition(), root);
          }
        }
    }
}
