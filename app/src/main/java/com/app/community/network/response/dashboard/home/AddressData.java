package com.app.community.network.response.dashboard.home;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 19/03/18.
 */

public class AddressData extends BaseResponse {
    private ArrayList<UserAddress> data;

    public ArrayList<UserAddress> getData() {
        return data;
    }

    public void setData(ArrayList<UserAddress> data) {
        this.data = data;
    }
}
