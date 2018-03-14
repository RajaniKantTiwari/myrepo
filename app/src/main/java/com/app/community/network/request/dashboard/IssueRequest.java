package com.app.community.network.request.dashboard;

/**
 * Created by rajnikant on 14/03/18.
 */

public class IssueRequest {
    private int issueid;
    private long orderid;
    private String comments;

    public int getIssueid() {
        return issueid;
    }

    public void setIssueid(int issueid) {
        this.issueid = issueid;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
