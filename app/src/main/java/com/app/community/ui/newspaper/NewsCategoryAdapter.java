package com.app.community.ui.newspaper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ItemCatBinding;
import com.app.community.network.response.dashboard.cart.CategoryData;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;


/**
 * Created  on 31/12/17.
 */

public class NewsCategoryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<CategoryData> mDataList = new ArrayList<>();
    private NewsOnCatItemClick mListener;
    public interface NewsOnCatItemClick {
        void onCatClick(int pos, View view);
    }
    public NewsCategoryAdapter(ArrayList<CategoryData> cartDataList, NewsOnCatItemClick listener) {
        this.mDataList = cartDataList;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        ItemCatBinding catBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cat, parent, false);
        return new ItemCat(catBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsCategoryAdapter.ItemCat itemCat = (NewsCategoryAdapter.ItemCat) holder;
//        itemCat.bind(mDataList.get(position));
        itemCat.bind(position);
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }


    private class ItemCat extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ItemCatBinding mBinding;

        public ItemCat(ItemCatBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.tvName.setOnClickListener(this);
//            mBinding.tvName.setBackgroundColor(Color.parseColor("#ff00ff"));
        }

        public void bind(int position) {
            mBinding.tvName.setTag(position);
            mBinding.tvName.setText("Cat" + position);
            if (mDataList.get(position).isSelected()) {
                mBinding.layoutCat.setBackgroundColor(Color.parseColor("#b8b8b8"));
                mBinding.ivArrow.setVisibility(View.VISIBLE);
            } else {
                mBinding.ivArrow.setVisibility(View.GONE);
                String colorCode = mDataList.get(position).getColorcode();
                if (colorCode.contains(" ")) {
                    colorCode = colorCode.replace(" ", "");
                    mBinding.layoutCat.setBackgroundColor(Color.parseColor(colorCode));

                }else {
                    mBinding.layoutCat.setBackgroundColor(Color.parseColor(mDataList.get(position).getColorcode()));
                }
            }
            mBinding.tvName.setText(mDataList.get(position).getName());
            if (!TextUtils.isEmpty(mDataList.get(position).getIcons())) {
                GlideUtils.loadImage(mContext, mDataList.get(position).getIcons(), mBinding.ivIcon, null, 0);
            }
//            else{
//            }

        }


        @Override
        public void onClick(View view) {
            View root = mBinding.getRoot();
            root.setTag(view);
            int pos = (Integer) view.getTag();
            switch (view.getId()) {
                case R.id.tvName:
                    mListener.onCatClick(pos, root);
                    break;


            }

        }
    }



}