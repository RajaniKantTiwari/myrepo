package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.app.community.R;
import com.app.community.databinding.ItemImageBinding;
import com.app.community.databinding.ReviewRowItemBinding;
import com.app.community.network.response.dashboard.home.ReviewResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<ReviewResponse> reviewList;
    private final AppCompatActivity activity;

    public ReviewAdapter(AppCompatActivity activity, ArrayList<ReviewResponse> reviewList){
        mInflater=LayoutInflater.from(activity);
        this.activity=activity;
        this.reviewList=reviewList;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReviewRowItemBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.review_row_item, parent, false);
        return new ReviewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
       if(CommonUtils.isNotNull(reviewList)&&reviewList.size()>position){
           holder.setResponse(reviewList.get(position));
           GlideUtils.loadImageProfilePic(activity,reviewList.get(position).getAvatar_base_url()+"/"+reviewList.get(position).getAvatar_path(),holder.ivReview,null,R.drawable.avatar);
           holder.postDate.setText(CommonUtils.getCreatedDate("2017-12-24 23:55:08"));
           holder.ratingBar.setRating(CommonUtils.setRating(reviewList.get(position).getRating()));
       }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(reviewList)?reviewList.size():0;
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        private final ImageView ivReview;
        private final RatingBar ratingBar;
        private final CustomTextView postDate;
        private ReviewRowItemBinding itemView;
       public ReviewHolder(ReviewRowItemBinding itemView) {
           super(itemView.getRoot());
           this.itemView=itemView;
           ivReview=itemView.ivReview;
           ratingBar=itemView.ratingBar;
           postDate=itemView.postDate;
       }

        public void setResponse(ReviewResponse reviewResponse) {
            itemView.setResponse(reviewResponse);
            try {
                itemView.ratingBar.setRating(Float.parseFloat(CommonUtils.twoDecimalPlaceString(reviewResponse.getRating())));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
