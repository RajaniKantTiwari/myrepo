package com.app.community.network.response.dashboard.user;

/**
 * Created by rajnikant on 24/02/18.
 */

public class UserAddress {
    private boolean isDefault;
    private String address;

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
