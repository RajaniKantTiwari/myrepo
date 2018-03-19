package com.app.community.network.response.dashboard.notification;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 18/03/18.
 */

public class NotificationResponseData extends BaseResponse {
    private ArrayList<NotificationResponse> message;

    public ArrayList<NotificationResponse> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<NotificationResponse> message) {
        this.message = message;
    }
}
