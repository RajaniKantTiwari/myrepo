package com.app.community.network.response.dashboard;

import com.app.community.network.response.BaseResponse;

/**
 * Created by rajnikant on 08/04/18.
 */

public class DeliveryData extends BaseResponse {
    private int avgtime;

    public int getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(int avgtime) {
        this.avgtime = avgtime;
    }
}
