package com.app.community.ui.dashboard.notification;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.network.response.dashboard.notification.NotificationResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.TimeAgo;
import com.app.community.widget.CustomTextView;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<NotificationResponse> newNotificationList;
    private final LayoutInflater mInflator;
    private final List<NotificationResponse> oldNotificationList;
    private final Activity activity;
    private NotificationListener mListener;

    public interface NotificationListener {

        void clearNotification();

        void deleteNotification(int position);

        void readNotification(int position);
    }

    public NotificationAdapter(Activity activity, NotificationListener mListener, ArrayList<NotificationResponse> newNotificationList, List<NotificationResponse> oldNotificationList) {
        this.activity = activity;
        mInflator = LayoutInflater.from(activity);
        this.mListener = mListener;
        this.newNotificationList = newNotificationList;
        this.oldNotificationList = oldNotificationList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || ((newNotificationList.size() + 1 == position) && oldNotificationList.size() > 0))
            return 0;
        else
            return 1;
    }

    @Override
    public int getItemCount() {
        if (newNotificationList.size() > 0 && oldNotificationList.size() > 0) {
            return newNotificationList.size() + oldNotificationList.size() + 2;
        } else if (newNotificationList.size() > 0) {
            return newNotificationList.size() + 1;
        } else if (oldNotificationList.size() > 0) {
            return oldNotificationList.size() + 2;
        } else {
            return 0;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
                view = mInflator.inflate(R.layout.notification_header, parent, false);
                return new NotificationHeaderHolder(view);
            case 1:
                view = mInflator.inflate(R.layout.notification_row_item, parent, false);
                return new NotificationHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 0:
                NotificationHeaderHolder headerHolder = (NotificationHeaderHolder) holder;
                setData(headerHolder, position);
                break;
            case 1:
                NotificationHolder notificationHolder = (NotificationHolder) holder;
                setData(notificationHolder, position);
                break;
        }
    }

    private void setData(NotificationHolder notificationHolder, int position) {
        NotificationResponse response=null;
        if (newNotificationList.size() >= position) {
             response = newNotificationList.get(position-1);
            if (CommonUtils.isNotNull(response)) {
                notificationHolder.tvHeading.setTextColor(CommonUtils.getColor(activity, R.color.color_notifi));
            }
        } else {
             response = oldNotificationList.get(position - (newNotificationList.size() + 2));
            if (CommonUtils.isNotNull(response)) {
                notificationHolder.tvHeading.setTextColor(CommonUtils.getColor(activity, R.color.black));
            }
        }
        if(CommonUtils.isNotNull(response)){
            notificationHolder.tvMessage.setText(response.getMessage());
            notificationHolder.tvTime.setText(TimeAgo.getAgoTime(activity, response.getSenttime()));
        }
    }

    private void setData(NotificationHeaderHolder headerHolder, int position) {
        if (position == 0) {
            headerHolder.tvClear.setVisibility(View.VISIBLE);
            headerHolder.tvNotification.setText(activity.getResources().getString(R.string.New));
            headerHolder.tvNotification.setTextColor(CommonUtils.getColor(activity, R.color.color_notifi));

        } else {
            headerHolder.tvClear.setVisibility(View.GONE);
            headerHolder.tvNotification.setText(activity.getResources().getString(R.string.old));
            headerHolder.tvNotification.setTextColor(CommonUtils.getColor(activity, R.color.black));
        }
    }

    class NotificationHeaderHolder extends RecyclerView.ViewHolder {
        private final CustomTextView tvNotification;
        private final CustomTextView tvClear;

        public NotificationHeaderHolder(View itemView) {
            super(itemView);
            tvNotification = itemView.findViewById(R.id.tvNotification);
            tvClear = itemView.findViewById(R.id.tvClear);
            tvClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CommonUtils.isNotNull(mListener)) {
                        mListener.clearNotification();
                    }
                }
            });
        }
    }

    class NotificationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CustomTextView tvMessage;
        private final CustomTextView tvHeading;
        private final CustomTextView tvDelete;
        private final CustomTextView tvTime;

        public NotificationHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvDelete = itemView.findViewById(R.id.tvDelete);
            tvTime=itemView.findViewById(R.id.tvTime);

            itemView.findViewById(R.id.layoutRow).setOnClickListener(this);
            tvDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tvDelete:
                    if (CommonUtils.isNotNull(mListener)) {
                        mListener.deleteNotification(getAdapterPosition());
                    }
                    break;
                case R.id.layoutRow:
                    if (CommonUtils.isNotNull(mListener)) {
                        mListener.readNotification(getAdapterPosition());
                    }
                    break;
            }
        }
    }
}