package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ItemImageBinding;
import com.app.community.databinding.ReviewRowItemBinding;

/**
 * Created by ashok on 25/12/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private final LayoutInflater mInflater;
    private ReviewRowItemBinding mBinding;

    public ReviewAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
    }
    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.review_row_item, parent, false);
        return new ReviewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
       public ReviewHolder(View itemView) {
           super(itemView);
       }
   }
}
