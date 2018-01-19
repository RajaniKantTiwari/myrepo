package com.app.community.network.request.dashboard;

/**
 * Created by rajnikant on 19/01/18.
 */

public class ProductSearchRequest {
    private String tag;

    public ProductSearchRequest(String tag) {
        this.tag=tag;
    }

    public String getTag() {
        return tag;
    }

}
