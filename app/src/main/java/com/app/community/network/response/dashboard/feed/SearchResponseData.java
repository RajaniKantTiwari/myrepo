package com.app.community.network.response.dashboard.feed;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 19/01/18.
 */

public class SearchResponseData extends BaseResponse {
    private ArrayList<MerchantResponse> data;

    public ArrayList<MerchantResponse> getData() {
        return data;
    }

    public void setData(ArrayList<MerchantResponse> data) {
        this.data = data;
    }
}
