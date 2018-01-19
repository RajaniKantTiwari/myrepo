package com.app.community.network.request.dashboard;

/**
 * Created by rajnikant on 19/01/18.
 */

public class MerchantRequest {
    private int merchant_id;

    public MerchantRequest(int merchant_id) {
      this.merchant_id=merchant_id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }
}
