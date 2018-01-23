package com.app.community.network.response.dashboard.cart;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;


public class CategoryResponse  extends BaseResponse {
//    private String status;
    private ArrayList<CategoryData> info;


//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    public ArrayList<CategoryData> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<CategoryData> info) {
        this.info = info;
    }
}


