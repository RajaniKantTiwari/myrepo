package com.app.community.network.request;

/**
 * Created by on 23/12/17.
 */

public class LoginRequest {
    private String mobile;

    public LoginRequest(String mobile) {
        this.mobile=mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
