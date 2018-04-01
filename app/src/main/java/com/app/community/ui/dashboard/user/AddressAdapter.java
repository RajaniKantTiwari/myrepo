package com.app.community.ui.dashboard.user;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.community.R;
import com.app.community.databinding.AddressRowBinding;
import com.app.community.network.response.dashboard.user.UserAddress;
import com.app.community.utils.CommonUtils;
import com.app.community.widget.CustomTextView;

import java.util.List;


/**
 * Created by ashok on 25/12/17.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private AddressListener listener;
    private List<UserAddress> addressList;

    public interface AddressListener {
        void onDeleteClick(int position);

        void onSetDefaultClick(int position);
    }

    public AddressAdapter(AppCompatActivity activity, List<UserAddress> addressList, AddressListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener = listener;
        this.addressList = addressList;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AddressRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.address_row, parent, false);
        return new AddressViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        if (CommonUtils.isNotNull(addressList) && addressList.size() > 0) {
            UserAddress address = addressList.get(position);
            if (CommonUtils.isNotNull(address)) {
                holder.tvAddress.setText(address.getAddress());
                if (address.isDefault()) {
                    holder.tvDelete.setVisibility(View.GONE);
                    holder.tvDefault.setVisibility(View.GONE);
                } else {
                    holder.tvDelete.setVisibility(View.VISIBLE);
                    holder.tvDefault.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(addressList) ? addressList.size() : 0;
    }

    class AddressViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final AddressRowBinding mBinding;
        private final CustomTextView tvAddress;
        private final CustomTextView tvDelete;
        private final CustomTextView tvDefault;

        public AddressViewHolder(AddressRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            tvAddress = itemView.tvAddress;
            tvDelete = itemView.tvDelete;
            tvDefault = itemView.tvDefault;
            mBinding.tvDelete.setOnClickListener(this);
            mBinding.tvDefault.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (view == mBinding.tvDelete) {
                listener.onDeleteClick(getAdapterPosition());
            } else if (view == mBinding.tvDefault) {
                listener.onSetDefaultClick(getAdapterPosition());
            }
        }
    }
}
