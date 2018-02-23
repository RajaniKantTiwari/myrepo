package com.app.community.ui.dashboard.notification;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.NotificationOldRowItemBinding;
import com.app.community.network.response.dashboard.home.ReviewResponse;
import com.app.community.network.response.dashboard.notification.NotificationResponse;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class NotificationPastAdapter extends RecyclerView.Adapter<NotificationPastAdapter.PastNotificationHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private int getTotalHeight;
    public int getGetTotalHeight() {
        return getTotalHeight;
    }
    public NotificationPastAdapter(AppCompatActivity activity, ArrayList<NotificationResponse> pastList){
        mInflater=LayoutInflater.from(activity);

        this.activity=activity;
    }

    @Override
    public PastNotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NotificationOldRowItemBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.notification_old_row_item, parent, false);
        return new PastNotificationHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(PastNotificationHolder holder, int position) {
        getItemWidthHeight(holder);
    }
    private void getItemWidthHeight(PastNotificationHolder holder) {
        holder.itemView.getRoot().post(new Runnable()
        {
            @Override
            public void run()
            {

                int cellWidth = holder.itemView.getRoot().getWidth();// this will give you cell width dynamically
                int cellHeight = holder.itemView.getRoot().getHeight();// this will give you cell height dynamically
                getTotalHeight=getTotalHeight+cellHeight;
                Log.e("WidthHeight","Width  "+cellWidth+"  Height "+cellHeight);

            }
        });
    }
    @Override
    public int getItemCount() {
        return 10;
    }

    class PastNotificationHolder extends RecyclerView.ViewHolder{
        private NotificationOldRowItemBinding itemView;
       public PastNotificationHolder(NotificationOldRowItemBinding itemView) {
           super(itemView.getRoot());
           this.itemView=itemView;

       }

        public void setResponse(ReviewResponse reviewResponse) {
            //itemView.setResponse(reviewResponse);
        }
    }
}
