package com.app.community.network.response.dashboard.meeting;


import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by Amul on 28/12/17.
 */

public class ProductResponseData extends BaseResponse {
    private ArrayList<ProductResponse> info;

    public ArrayList<ProductResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<ProductResponse> info) {
        this.info = info;
    }
}
