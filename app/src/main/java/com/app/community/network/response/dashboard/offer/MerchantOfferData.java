package com.app.community.network.response.dashboard.offer;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/03/18.
 */

public class MerchantOfferData extends BaseResponse {
    private ArrayList<MerchantOffer> data;

    public ArrayList<MerchantOffer> getData() {
        return data;
    }

    public void setData(ArrayList<MerchantOffer> data) {
        this.data = data;
    }
}
