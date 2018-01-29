package com.app.community.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.community.R;
import com.app.community.databinding.PaymentRowBinding;
import com.app.community.network.request.PaymentOption;
import com.app.community.utils.CommonUtils;

import java.util.List;

/**
 * Created by ashok on 25/12/17.
 */

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private final List<PaymentOption> paymentList;

    public PaymentAdapter(AppCompatActivity activity, List<PaymentOption> paymentList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.paymentList = paymentList;
    }

    @Override
    public PaymentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PaymentRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.payment_row, parent, false);
        return new PaymentViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(PaymentViewHolder holder, int position) {
        if (CommonUtils.isNotNull(paymentList) && paymentList.size() > position) {
            holder.setData(paymentList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(paymentList) ? paymentList.size() : 0;
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final PaymentRowBinding mBinding;
        private final LinearLayout layoutRadio;

        public PaymentViewHolder(PaymentRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            layoutRadio=mBinding.layoutRadio;
            mBinding.layoutRadio.setOnClickListener(this);
        }

        public void setData(PaymentOption paymentOption) {
            mBinding.setPayment(paymentOption);
            mBinding.radioButton.setChecked(paymentOption.isChecked());
        }

        @Override
        public void onClick(View view) {
            onItemClick(getAdapterPosition());
        }
    }

    private void onItemClick(int adapterPosition) {
        for (int i = 0; i < paymentList.size(); i++) {
            if (i == adapterPosition) {
                PaymentOption payment = paymentList.get(i);
                payment.setChecked(true);
                paymentList.set(i, payment);
            } else {
                PaymentOption payment = paymentList.get(i);
                payment.setChecked(false);
                paymentList.set(i, payment);
            }
        }
        notifyDataSetChanged();
    }
}
