package com.app.community.network.response;

import java.util.ArrayList;

/**
 * Created by rajnikant on 20/02/18.
 */

public class MyOrder extends BaseResponse {
    private ArrayList<Order>  recent;
    private ArrayList<Order>  past;

    public ArrayList<Order> getRecent() {
        return recent;
    }

    public void setRecent(ArrayList<Order> recent) {
        this.recent = recent;
    }

    public ArrayList<Order> getPast() {
        return past;
    }

    public void setPast(ArrayList<Order> past) {
        this.past = past;
    }
}
