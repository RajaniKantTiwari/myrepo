package com.app.community.ui.newspaper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ItemSubcatBinding;
import com.app.community.network.response.dashboard.cart.SubCategory;

import java.util.ArrayList;

/**
 * Created by virender on 10/01/18.
 */

public class NewsSubCatAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<SubCategory> mDataList = new ArrayList<>();
    private OnNewsSubCatItemClick mListener;
    public interface OnNewsSubCatItemClick {
        void onSubCatClick(int pos, View view);
    }
    public NewsSubCatAdapter(ArrayList<SubCategory> cartDataList, OnNewsSubCatItemClick listener) {
        this.mDataList = cartDataList;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ItemSubcatBinding subcatBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_subcat, parent, false);
        return new NewsSubCatAdapter.ItemCat(subcatBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsSubCatAdapter.ItemCat itemCat = (NewsSubCatAdapter.ItemCat) holder;
//        itemCat.bind(mDataList.get(position));
        itemCat.bind(position);
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }


    private class ItemCat extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemSubcatBinding mBinding;

        public ItemCat(ItemSubcatBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.tvName.setOnClickListener(this);
        }

        public void bind(int position) {
            mBinding.tvName.setTag(position);
            mBinding.tvName.setText("Cat" + position);
            if (mDataList.get(position).isSelected()) {
//                mBinding.tvName.setBackgroundColor(Color.parseColor("#ffffaa"));
//                mBinding.tvName.setBackgroundColor(mContext.getColor(R.color.active_tab_color));
                mBinding.tvName.setBackgroundColor(Color.parseColor("#b8b8b8"));


            } else {
                mBinding.tvName.setBackgroundColor(Color.parseColor(mDataList.get(position).getColorcode()));

            }
            mBinding.tvName.setText(mDataList.get(position).getName());


        }


        @Override
        public void onClick(View view) {
            View root = mBinding.getRoot();
            root.setTag(view);
            int pos = (Integer) view.getTag();
            switch (view.getId()) {
                case R.id.tvName:

                    mListener.onSubCatClick(pos, root);
                    break;


            }

        }
    }




}