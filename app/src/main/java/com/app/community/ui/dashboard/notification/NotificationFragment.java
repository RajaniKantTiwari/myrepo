package com.app.community.ui.dashboard.notification;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.community.R;
import com.app.community.databinding.FragmentNotificationBinding;
import com.app.community.network.request.dashboard.NotificationRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.notification.NotificationResponse;
import com.app.community.network.response.dashboard.notification.NotificationResponseData;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashok on 13/11/17.
 */

public class NotificationFragment extends DashboardFragment implements NotificationAdapter.NotificationListener {

    @Inject
    CommonPresenter presenter;
    private FragmentNotificationBinding mBinding;
    private NotificationAdapter mNotificationAdapter;
    private ArrayList<NotificationResponse> newNotificationList = new ArrayList<>();
    private ArrayList<NotificationResponse> oldNotificationList = new ArrayList<>();
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        getDashboardActivity().setHeaderTitle(getString(R.string.notification));
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        //for New Notification
        LinearLayoutManager notificationManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvNotification.setLayoutManager(notificationManager);
        mNotificationAdapter = new NotificationAdapter(getDashboardActivity(), this, newNotificationList, oldNotificationList);
        mBinding.rvNotification.setAdapter(mNotificationAdapter);
        getPresenter().getNotificationListPerUser(getDashboardActivity());

    }


    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return NotificationFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == 1) {
                NotificationResponseData data = (NotificationResponseData) response;
                setData(data);
            } else if (requestCode == 2) {
                newNotificationList.clear();
                oldNotificationList.clear();
                mNotificationAdapter.notifyDataSetChanged();
            } else if (requestCode == 3) {
                if (position > (newNotificationList.size() + 1)) {
                    oldNotificationList.remove(position - (newNotificationList.size() + 2));
                } else {
                    newNotificationList.remove(position - 1);
                }
                mNotificationAdapter.notifyDataSetChanged();
            } else if (requestCode == 4) {
                NotificationResponse res = newNotificationList.get(position - 1);
                res.setMsgstatus(1);
                oldNotificationList.add(res);
                newNotificationList.remove(position - 1);
                mNotificationAdapter.notifyDataSetChanged();
            }

        }
    }

    private void setData(NotificationResponseData data) {
        if (CommonUtils.isNotNull(data)) {
            newNotificationList.clear();
            oldNotificationList.clear();
            ArrayList<NotificationResponse> responseArrayList = data.getMessage();
            if (CommonUtils.isNotNull(responseArrayList)) {
                for (NotificationResponse response : responseArrayList) {
                    if (response.getMsgstatus() == 0) {
                        newNotificationList.add(response);
                    } else {
                        oldNotificationList.add(response);
                    }
                }
            }
        }
        mNotificationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void clearNotification() {
        getPresenter().clearAllNotification(getDashboardActivity());
    }

    @Override
    public void deleteNotification(int position) {
        this.position = position;
        if (position > (newNotificationList.size() + 1)) {
            getPresenter().deleteNotification(getDashboardActivity(), new NotificationRequest(oldNotificationList.get(position - (newNotificationList.size() + 2)).getId()));
        } else {
            getPresenter().deleteNotification(getDashboardActivity(), new NotificationRequest(newNotificationList.get(position - 1).getId()));
        }
    }

    @Override
    public void readNotification(int position) {
        this.position = position;
        if (position <= newNotificationList.size()) {
            getPresenter().readNotification(getDashboardActivity(), new NotificationRequest(newNotificationList.get(position - 1).getId()));
        }
    }
}
