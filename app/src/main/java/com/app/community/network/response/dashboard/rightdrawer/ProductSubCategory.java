package com.app.community.network.response.dashboard.rightdrawer;

import com.app.community.network.response.dashboard.drawerresponse.Ingredient;
import com.app.community.ui.dashboard.expandrecycleview.model.Parent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajnikant on 29/01/18.
 */

public class ProductSubCategory implements Parent<Merchant> {
    private String id;
    private String subcat;
    private String displayorder;
    private boolean isCategory;
    private ArrayList<Merchant> merchantname;

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    public boolean isCategory() {
        return isCategory;
    }

    public void setCategory(boolean category) {
        isCategory = category;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public ArrayList<Merchant> getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(ArrayList<Merchant> merchantname) {
        this.merchantname = merchantname;
    }

    @Override
    public List<Merchant> getChildList() {
        return merchantname;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public Merchant getMerchant(int childPosition) {
        return merchantname.get(childPosition);
    }
}
