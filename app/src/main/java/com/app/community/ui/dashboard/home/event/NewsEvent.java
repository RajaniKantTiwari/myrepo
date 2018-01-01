package com.app.community.ui.dashboard.home.event;


import com.app.community.network.response.dashboard.meeting.ProductResponse;

import java.util.ArrayList;

/**
 * Created by Amul on 27/12/17.
 */

public class NewsEvent {
    //use 1 for meeting 2 for event
    private int recentCategory;


    public int getRecentCategory() {
        return recentCategory;
    }

    public void setRecentCategory(int recentCategory) {
        this.recentCategory = recentCategory;
    }
}
