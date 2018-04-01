package com.app.community.network.response.dashboard.user;

import com.app.community.network.response.BaseResponse;

/**
 * Created by rajnikant on 01/04/18.
 */

public class UserDefaultAddressData extends BaseResponse{
    private UserAddress default_address;

    public UserAddress getDefault_address() {
        return default_address;
    }

    public void setDefault_address(UserAddress default_address) {
        this.default_address = default_address;
    }
}
