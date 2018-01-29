package com.app.community.network.response.dashboard.rightdrawer;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 29/01/18.
 */

public class ProductTypeData extends BaseResponse {
    private ArrayList<ProductType> data;

    public ArrayList<ProductType> getData() {
        return data;
    }

    public void setData(ArrayList<ProductType> data) {
        this.data = data;
    }
}
