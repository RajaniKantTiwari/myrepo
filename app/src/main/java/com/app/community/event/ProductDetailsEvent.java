package com.app.community.event;

/**
 * Created by rajnikant on 03/02/18.
 */

public class ProductDetailsEvent {
    private String merchantId;

    public ProductDetailsEvent(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

}
