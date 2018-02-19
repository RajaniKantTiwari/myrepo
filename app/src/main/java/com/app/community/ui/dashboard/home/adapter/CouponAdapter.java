package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.app.community.R;
import com.app.community.databinding.CouponRowItemBinding;
import com.app.community.network.request.dashboard.Coupon;
import com.app.community.utils.CommonUtils;

import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CouponViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<Coupon> couponList;
    private CouponListener listener;

    public interface CouponListener {
        void onCouponClick(int position);
    }

    public CouponAdapter(AppCompatActivity activity, List<Coupon> couponList, CouponListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.couponList = couponList;
        this.listener = listener;
    }

    @Override
    public CouponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CouponRowItemBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.coupon_row_item, parent, false);
        return new CouponViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CouponViewHolder holder, int position) {
        if (CommonUtils.isNotNull(couponList) && couponList.size() > position) {
            holder.setData(couponList.get(position));
            holder.radio.setChecked(couponList.get(position).isChecked());
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(couponList) ? couponList.size() : 0;
    }

    class CouponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CouponRowItemBinding mBinding;
        private final RadioButton radio;

        public CouponViewHolder(CouponRowItemBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            radio = itemView.radio;
            mBinding.layoutCoupon.setOnClickListener(this);
        }

        public void setData(Coupon coupon) {
            mBinding.setCoupon(coupon);
        }

        @Override
        public void onClick(View view) {
            listener.onCouponClick(getAdapterPosition());
        }
    }
}
