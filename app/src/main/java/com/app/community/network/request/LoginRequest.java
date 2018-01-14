package com.app.community.network.request;

/**
 * Created by on 23/12/17.
 */

public class LoginRequest {
    private String name;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginRequest(String name,String mobile) {
        this.mobile=mobile;
        this.name=name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
