package com.app.community.event;

/**
 * Created by rajnikant on 15/03/18.
 */

public class StartShoppingEvent {
    private final String merchant_id;
    private final String address;
    private final String image;
    private final String color;


    public StartShoppingEvent(String merchant_id, String address, String image, String color) {
        this.merchant_id = merchant_id;
        this.address = address;
        this.image = image;
        this.color = color;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }
}
