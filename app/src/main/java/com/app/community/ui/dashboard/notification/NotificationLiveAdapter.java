package com.app.community.ui.dashboard.notification;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.community.R;
import com.app.community.databinding.NotificationRowItemBinding;
import com.app.community.network.response.dashboard.home.ReviewResponse;
import com.app.community.network.response.dashboard.notification.NotificationResponse;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class NotificationLiveAdapter extends RecyclerView.Adapter<NotificationLiveAdapter.LiveNotificationHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private int getTotalHeight;

    public int getGetTotalHeight() {
        return getTotalHeight;
    }

    public NotificationLiveAdapter(AppCompatActivity activity, ArrayList<NotificationResponse> liveList){
        mInflater=LayoutInflater.from(activity);

        this.activity=activity;
    }

    @Override
    public LiveNotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NotificationRowItemBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.notification_row_item, parent, false);
        return new LiveNotificationHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LiveNotificationHolder holder, int position) {

    }

    /*private void getItemWidthHeight(LiveNotificationHolder holder) {
        holder.itemView.layoutRow.post(new Runnable()
        {
            @Override
            public void run()
            {

                int cellWidth = holder.itemView.layoutRow.getWidth();// this will give you cell width dynamically
                int cellHeight = holder.itemView.layoutRow.getHeight();// this will give you cell height dynamically
                getTotalHeight=getTotalHeight+cellHeight;
                Log.e("WidthHeight","Width  "+cellWidth+"  Height "+cellHeight);

            }
        });
    }*/

    @Override
    public int getItemCount() {
        return 10;
    }

    class LiveNotificationHolder extends RecyclerView.ViewHolder{
        private final LinearLayout layoutRow;
        private NotificationRowItemBinding itemView;
       public LiveNotificationHolder(NotificationRowItemBinding itemView) {
           super(itemView.getRoot());
           this.itemView=itemView;
           layoutRow=itemView.layoutRow;
       }

        public void setResponse(ReviewResponse reviewResponse) {
            //itemView.setResponse(reviewResponse);
        }
    }
}
