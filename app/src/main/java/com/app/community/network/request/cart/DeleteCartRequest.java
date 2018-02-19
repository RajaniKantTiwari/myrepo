package com.app.community.network.request.cart;

/**
 * Created by rajnikant on 11/01/18.
 */

public class DeleteCartRequest {
    private int merchant_id;
    private int masterproductid;

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public int getMasterproductid() {
        return masterproductid;
    }

    public void setMasterproductid(int masterproductid) {
        this.masterproductid = masterproductid;
    }
}
