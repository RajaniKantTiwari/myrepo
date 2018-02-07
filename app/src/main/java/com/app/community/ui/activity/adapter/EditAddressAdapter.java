package com.app.community.ui.activity.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.EditRowItemBinding;
import com.app.community.databinding.LatestNewsRowItemBinding;
import com.app.community.network.request.EditAddress;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class EditAddressAdapter extends RecyclerView.Adapter<EditAddressAdapter.EditViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<EditAddress> editList;
    private final AppCompatActivity activity;
    private EditRowItemBinding mBinding;
    private EditListener listener;
    public interface EditListener {

        void onItemClick(int adapterPosition);
    }
    public EditAddressAdapter(AppCompatActivity activity, ArrayList<EditAddress> editList, EditListener listener){
        mInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.editList=editList;
        this.listener=listener;
    }
    @Override
    public EditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.edit_row_item, parent, false);
        return new EditViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(EditViewHolder holder, int position) {
        if(CommonUtils.isNotNull(editList)&&editList.size()>position){
            holder.setData(editList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(editList)?editList.size():0;
    }
    class EditViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       private EditRowItemBinding itemView;
       public EditViewHolder(EditRowItemBinding itemView) {
           super(itemView.getRoot());
           this.itemView=itemView;
           itemView.tvAddress.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(listener!=null){
                listener.onItemClick(getAdapterPosition());
            }
        }

        public void setData(EditAddress editAddress) {
            itemView.tvAddress.setText(editAddress.getAddress());
            if(editAddress.isSelected()){
                itemView.tvAddress.setBackgroundColor(CommonUtils.getColor(activity,R.color.background));
            }else{
                itemView.tvAddress.setBackgroundColor(0);
            }
        }
    }
}
