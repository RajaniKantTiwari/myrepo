package com.app.community.network.response.dashboard.user;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 01/04/18.
 */

public class UserAddressData extends BaseResponse {
   private ArrayList<UserAddress> user_addresses;

    public ArrayList<UserAddress> getUser_addresses() {
        return user_addresses;
    }

    public void setUser_addresses(ArrayList<UserAddress> user_addresses) {
        this.user_addresses = user_addresses;
    }
}
