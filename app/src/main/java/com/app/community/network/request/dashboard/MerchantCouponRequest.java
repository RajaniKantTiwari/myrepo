package com.app.community.network.request.dashboard;

/**
 * Created by rajnikant on 14/03/18.
 */

public class MerchantCouponRequest {
    private int merchant_id;

    public MerchantCouponRequest(int merchantId) {
       this.merchant_id=merchantId;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

}
