package com.app.community.network.response.dashboard.notification;

/**
 * Created by rajnikant on 23/02/18.
 */

public class NotificationResponse {
    //msgstatus 0 for unread notification  1 for read
    private int id;
    private String message;
    private int msgstatus;
    private String senttime;

    public String getSenttime() {
        return senttime;
    }

    public void setSenttime(String senttime) {
        this.senttime = senttime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMsgstatus() {
        return msgstatus;
    }

    public void setMsgstatus(int msgstatus) {
        this.msgstatus = msgstatus;
    }
}
