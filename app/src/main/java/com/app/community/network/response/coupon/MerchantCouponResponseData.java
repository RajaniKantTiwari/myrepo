package com.app.community.network.response.coupon;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 14/03/18.
 */

public class MerchantCouponResponseData extends BaseResponse {
    private ArrayList<MerchantCouponResponse> info;

    public ArrayList<MerchantCouponResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<MerchantCouponResponse> info) {
        this.info = info;
    }
}
