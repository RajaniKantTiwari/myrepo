package com.app.community.network.response.dashboard.feed;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 19/01/18.
 */

public class MerchantDetailsData extends BaseResponse {
   private ArrayList<MerchantResponse> info;

    public ArrayList<MerchantResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<MerchantResponse> info) {
        this.info = info;
    }
}
