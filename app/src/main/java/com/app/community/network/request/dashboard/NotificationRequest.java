package com.app.community.network.request.dashboard;

/**
 * Created by rajnikant on 19/03/18.
 */

public class NotificationRequest {
    private int notid;

    public NotificationRequest(int notid) {
        this.notid=notid;
    }

    public int getNotid() {
        return notid;
    }
}
