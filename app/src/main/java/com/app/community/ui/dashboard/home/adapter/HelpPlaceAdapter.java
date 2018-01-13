package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.HelpPlaceRowBinding;
import com.app.community.databinding.HelpSupportRowBinding;
import com.app.community.utils.CommonUtils;

/**
 * Created by ashok on 25/12/17.
 */

public class HelpPlaceAdapter extends RecyclerView.Adapter<HelpPlaceAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private HelpPlaceRowBinding mBinding;
    private HelpListener listener;
    public interface HelpListener{

        void itemHelpClicked(int adapterPosition);
    }
    public HelpPlaceAdapter(AppCompatActivity activity,HelpListener listener){
        this.listener=listener;
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.help_place_row, parent, false);
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

        public CartHolder(HelpPlaceRowBinding itemView) {
           super(itemView.getRoot());
           itemView.ivPlace.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(CommonUtils.isNotNull(listener)){
                listener.itemHelpClicked(getAdapterPosition());
            }
        }
    }
}
