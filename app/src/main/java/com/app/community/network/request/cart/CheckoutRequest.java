package com.app.community.network.request.cart;

/**
 * Created by rajnikant on 14/02/18.
 */

public class CheckoutRequest {
    private int response;
    private String deliverytype;
    private String deliveryaddress;


    public void setResponse(int response) {
        this.response = response;
    }

    public String getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(String deliverytype) {
        this.deliverytype = deliverytype;
    }

    public int getResponse() {
        return response;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }
}
