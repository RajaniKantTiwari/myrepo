package com.app.community.network.response.dashboard.rightdrawer;


import java.util.ArrayList;

/**
 * Created by rajnikant on 29/01/18.
 */

public class ProductType {
    private String id;
    private String category;
    private String displayorder;
    private ArrayList<ProductSubCategory> subcategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    public ArrayList<ProductSubCategory> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ArrayList<ProductSubCategory> subcategory) {
        this.subcategory = subcategory;
    }
}
