package com.app.community.network.request;

import com.app.community.network.DeviceToken;



public class DeviceTokenRequest {
    private int userid;
    private DeviceToken info;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public DeviceToken getInfo() {
        return info;
    }

    public void setInfo(DeviceToken info) {
        this.info = info;
    }
}
