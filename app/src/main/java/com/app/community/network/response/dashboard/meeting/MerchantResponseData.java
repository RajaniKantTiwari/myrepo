package com.app.community.network.response.dashboard.meeting;


import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by Amul on 28/12/17.
 */

public class MerchantResponseData extends BaseResponse {
    private ArrayList<MerchantResponse> info;

    public ArrayList<MerchantResponse> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<MerchantResponse> info) {
        this.info = info;
    }
}
