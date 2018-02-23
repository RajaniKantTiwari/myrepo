package com.app.community.ui.dashboard.home.event;


import com.app.community.network.response.Order;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class MyOrderEvent {
    //use 1 for meeting 2 for event
    private int livePastOrder;
    private ArrayList<Order> orderList;

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public int getLivePastOrder() {
        return livePastOrder;
    }

    public void setLivePastOrder(int livePastOrder) {
        this.livePastOrder = livePastOrder;
    }
}
