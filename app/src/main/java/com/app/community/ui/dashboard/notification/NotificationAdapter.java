package com.app.community.ui.dashboard.notification;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.app.community.R;
import com.app.community.databinding.NotificationRowItemBinding;
import com.app.community.databinding.ReviewRowItemBinding;
import com.app.community.network.response.dashboard.home.ReviewResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ReviewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;

    public NotificationAdapter(AppCompatActivity activity){
        mInflater=LayoutInflater.from(activity);
        this.activity=activity;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NotificationRowItemBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.notification_row_item, parent, false);
        return new ReviewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        private NotificationRowItemBinding itemView;
       public ReviewHolder(NotificationRowItemBinding itemView) {
           super(itemView.getRoot());
           this.itemView=itemView;

       }

        public void setResponse(ReviewResponse reviewResponse) {
            //itemView.setResponse(reviewResponse);
        }
    }
}
