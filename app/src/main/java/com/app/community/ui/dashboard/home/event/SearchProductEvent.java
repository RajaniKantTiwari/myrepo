package com.app.community.ui.dashboard.home.event;

/**
 * Created by rajnikant on 13/01/18.
 */

public class SearchProductEvent {
    private String searchString;
    public SearchProductEvent(String searchString){
       this.searchString=searchString;
    }

    public String getSearchString() {
        return searchString;
    }
}
