package com.app.community.network.response.coupon;

import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.Offer;

import java.util.ArrayList;

/**
 * Created by rajnikant on 14/03/18.
 */

public class ViewAllCouponResponseData extends BaseResponse {
    private ArrayList<Offer> info;

    public ArrayList<Offer> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<Offer> info) {
        this.info = info;
    }
}
