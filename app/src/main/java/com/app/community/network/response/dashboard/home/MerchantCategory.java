package com.app.community.network.response.dashboard.home;

import com.app.community.network.response.BaseResponse;

/**
 * Created by rajnikant on 29/03/18.
 */

public class MerchantCategory {
    private String category_id;
    private String category_name;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
