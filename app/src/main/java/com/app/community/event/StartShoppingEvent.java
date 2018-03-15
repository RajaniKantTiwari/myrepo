package com.app.community.event;

/**
 * Created by rajnikant on 15/03/18.
 */

public class StartShoppingEvent {
    private final String merchant_id;
    public StartShoppingEvent(String merchant_id) {
        this.merchant_id=merchant_id;
    }
    public String getMerchant_id() {
        return merchant_id;
    }
}
