package com.app.community.network.response.dashboard;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 03/04/18.
 */

public class OrderDetailData extends BaseResponse {
    private ArrayList<OrderData> orderdetails;

    public ArrayList<OrderData> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(ArrayList<OrderData> orderdetails) {
        this.orderdetails = orderdetails;
    }
}
