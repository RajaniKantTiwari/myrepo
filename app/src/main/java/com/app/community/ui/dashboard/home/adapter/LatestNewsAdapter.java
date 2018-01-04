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

    public LatestNewsAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public LatestNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.latest_news_row_item, parent, false);
        return new LatestNewsHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(LatestNewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class LatestNewsHolder extends RecyclerView.ViewHolder{
       public LatestNewsHolder(View itemView) {
           super(itemView);
       }
   }
}
