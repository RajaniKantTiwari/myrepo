package com.app.community.ui.dashboard.home.event;


import com.app.community.network.response.dashboard.home.MerchantResponse;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class ProductEvent {
    //use 1 for meeting 2 for event
    private int meetingEvent;
    //use 1 for list 2 for map
    private int listMap;

    private ArrayList<MerchantResponse> productList;

    public ArrayList<MerchantResponse> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<MerchantResponse> productList) {
        this.productList = productList;
    }

    public int getListMap() {
        return listMap;
    }

    public void setListMap(int listMap) {
        this.listMap = listMap;
    }

    public int getMeetingEvent() {
        return meetingEvent;
    }

    public void setMeetingEvent(int meetingEvent) {
        this.meetingEvent = meetingEvent;
    }

}
