package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.LatestNewsRowItemBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.LatestNewsHolder> {
    private final LayoutInflater mInflater;
    private LatestNewsRowItemBinding mBinding;
    private LatestNewsListener listener;
    public interface LatestNewsListener{

        void onItemClick(int adapterPosition);
    }
    public LatestNewsAdapter(AppCompatActivity activity,LatestNewsListener listener){
        mInflater=LayoutInflater.from(activity);
        this.listener=listener;
    }
    @Override
    public LatestNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.latest_news_row_item, parent, false);
        return new LatestNewsHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LatestNewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
    class LatestNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public LatestNewsHolder(LatestNewsRowItemBinding itemView) {
           super(itemView.getRoot());
           itemView.cvTrend.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(listener!=null){
                listener.onItemClick(getAdapterPosition());
            }
        }
    }
}
