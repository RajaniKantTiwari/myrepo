package com.app.community.network.response.dashboard.home;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 29/03/18.
 */

public class MerchantCategoryData extends BaseResponse {
private ArrayList<MerchantCategory> info;

    public ArrayList<MerchantCategory> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<MerchantCategory> info) {
        this.info = info;
    }
}
