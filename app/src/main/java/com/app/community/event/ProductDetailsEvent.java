package com.app.community.event;

import com.app.community.network.response.dashboard.home.MerchantResponse;

/**
 * Created by rajnikant on 03/02/18.
 */

public class ProductDetailsEvent {
    private MerchantResponse merchant;

    public ProductDetailsEvent(MerchantResponse merchant) {
        this.merchant = merchant;
    }

    public MerchantResponse getMerchant() {
        return merchant;
    }

}
