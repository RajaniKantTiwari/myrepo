package com.app.community.network.response.dashboard.rightdrawer;

import java.util.ArrayList;

/**
 * Created by rajnikant on 29/01/18.
 */

public class ProductSubCategory {
    private String id;
    private String subcat;
    private ArrayList<Merchant> merchantname;

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
}
