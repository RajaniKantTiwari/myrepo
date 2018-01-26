package com.app.community.network.response.dashboard.dashboardinside;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 26/01/18.
 */

public class ProductResponse implements Parcelable{
    private String productname;
    private String mrp;
    private String quantity;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    protected ProductResponse(Parcel in) {
        productname = in.readString();
        mrp = in.readString();
        quantity = in.readString();
    }

    public static final Creator<ProductResponse> CREATOR = new Creator<ProductResponse>() {
        @Override
        public ProductResponse createFromParcel(Parcel in) {
            return new ProductResponse(in);
        }

        @Override
        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productname);
        dest.writeString(mrp);
        dest.writeString(quantity);
    }
}
