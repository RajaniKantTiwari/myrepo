package com.app.community.network.response.dashboard.dashboardinside;


import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.ProductData;

import java.util.ArrayList;

/**
 * Created by Amul on 28/12/17.
 */

public class ProductDetailsData extends BaseResponse {
private ArrayList<ProductResponse> product;

    public ArrayList<ProductResponse> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<ProductResponse> product) {
        this.product = product;
    }
}
