package com.app.community.network.request.dashboard;

import com.app.community.network.response.dashboard.home.Offer;

/**
 * Created by rajnikant on 14/03/18.
 */

public class CheckCouponRequest {
    private int merchant_id;
    private String coupon;

    public CheckCouponRequest(int merchantId, String coupon) {
       this.merchant_id=merchant_id;
       this.coupon=coupon;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }
}
