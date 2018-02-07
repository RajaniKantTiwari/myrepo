package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.DrawerLeftRowItemBinding;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.UserPreference;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ashok on 25/12/17.
 */

public class DrawerAdapterLeft extends RecyclerView.Adapter<DrawerAdapterLeft.StoreViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private DrawerLeftListener listener;
    private ArrayList<String> drawerList;

    public interface DrawerLeftListener {
        void onLeftDrawerItemClicked(int adapterPosition);
    }

    public DrawerAdapterLeft(AppCompatActivity activity, DrawerLeftListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
        drawerList = new ArrayList<>();
        drawerList.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.left_drawer)));
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DrawerLeftRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.drawer_left_row_item, parent, false);
        return new StoreViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {
        if (CommonUtils.isNotNull(drawerList) && drawerList.size() > position) {
            holder.setData(drawerList.get(position));
            if (position == 2) {
                holder.tvChange.setVisibility(View.VISIBLE);
                if (UserPreference.getAddress() != null) {
                    holder.tvChange.setText(UserPreference.getAddress());
                } else {
                    holder.tvChange.setText(UserPreference.getAddress(activity, UserPreference.getLatitude(), UserPreference.getLongitude()));
                }
            } else {
                holder.tvChange.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(drawerList) ? drawerList.size() : 0;
    }

    class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final DrawerLeftRowItemBinding mBinding;
        private final CustomTextView tvChange;

        public StoreViewHolder(DrawerLeftRowItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvChange = itemView.tvChange;
            itemView.layoutUserItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(listener)) {
                listener.onLeftDrawerItemClicked(getAdapterPosition());
            }
        }

        public void setData(String str) {
            mBinding.tvMenuName.setText(str);
        }
    }
}
