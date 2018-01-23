package com.app.community.ui.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ItemCartBinding;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;


/**
 * Created by  on 01/01/18.
 */

public class CartAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<ProductData> mDataList = new ArrayList<>();
    private OnAddToCart mListener;


    public CartAdapter(ArrayList<ProductData> cartDataList, OnAddToCart listener) {
        this.mDataList = cartDataList;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ItemCartBinding cartBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false);
        return new CartAdapter.ItemCart(cartBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CartAdapter.ItemCart itemCat = (CartAdapter.ItemCart) holder;
//        itemCat.bind(mDataList.get(position));
        itemCat.bind(position);
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }


    private class ItemCart extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemCartBinding mBinding;

        public ItemCart(ItemCartBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.tvProductName.setOnClickListener(this);
            mBinding.ivPlus.setOnClickListener(this);
            mBinding.ivMinus.setOnClickListener(this);
            mBinding.tvSubscribe.setOnClickListener(this);

//            mBinding.tvName.setBackgroundColor(Color.parseColor("#ff00ff"));
        }

        public void bind(int position) {
//            mBinding.tvName.setTag(position);
//            if (position % 2 == 0) {
//                mBinding.ivProduct.setBackgroundsetsetColor(Color.parseColor("#ffffaa"));
//
//            } else {
//                mBinding.tvName.setBackgroundColor(Color.parseColor("#aaffaa"));
//
//            }
            mBinding.ivMinus.setTag(position);
            mBinding.ivPlus.setTag(position);
            mBinding.tvProductName.setText(mDataList.get(position).getProductname());
            mBinding.tvProductPrice.setText("INR " + String.valueOf(mDataList.get(position).getProduct_mrp()));
            mBinding.tvQty.setText(String.valueOf(mDataList.get(position).getQty()));
            mBinding.tvProductQty.setText(mDataList.get(position).getMeasure());
            if (!TextUtils.isEmpty(mDataList.get(position).getIcon())) {
                GlideUtils.loadImage(mContext, mDataList.get(position).getIcon(), mBinding.ivProduct, null, R.drawable.app_icon);
            }else{
                mBinding.ivProduct.setImageResource(R.drawable.app_icon);
            }
        }


        @Override
        public void onClick(View view) {
            View root = mBinding.getRoot();
            root.setTag(view);
            int pos = (Integer) view.getTag();
            switch (view.getId()) {
                case R.id.tvName:

//                mViewListener.onItemClick(view.getTag(), root);
                    break;
                case R.id.ivPlus:
                    mListener.addToCartClick(pos, root);
                    break;
                case R.id.ivMinus:
                    mListener.addToCartClick(pos, root);
                    break;


            }

        }
    }

    public interface OnAddToCart {
        public void addToCartClick(int pos, View view);

    }
}

