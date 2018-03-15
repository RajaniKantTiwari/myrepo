package com.app.community.network.response.dashboard.dashboardinside;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 26/01/18.
 */

public class ProductResponse implements Parcelable{
    private String productname;
    private String mrp;
    private int quantity;
    private int selling_price;
    private int product_mrp;
    private int tax;
    private String avg_time_to_deliver;
    private int shipping;
    private String tax_status;
    private int masterproductid;
    private int merchantlistid;
    private int merchantid;
    private String measure;
    private String icon;
    private String pricewithtax;
    private String pricewithouttax;

    public ProductResponse(){

    }

    protected ProductResponse(Parcel in) {
        productname = in.readString();
        mrp = in.readString();
        quantity = in.readInt();
        selling_price = in.readInt();
        product_mrp = in.readInt();
        tax = in.readInt();
        avg_time_to_deliver = in.readString();
        shipping = in.readInt();
        tax_status = in.readString();
        masterproductid = in.readInt();
        merchantlistid = in.readInt();
        merchantid = in.readInt();
        measure = in.readString();
        icon = in.readString();
        pricewithtax = in.readString();
        pricewithouttax = in.readString();
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

    public String getAvg_time_to_deliver() {
        return avg_time_to_deliver;
    }

    public void setAvg_time_to_deliver(String avg_time_to_deliver) {
        this.avg_time_to_deliver = avg_time_to_deliver;
    }

    public void setPricewithtax(String pricewithtax) {
        this.pricewithtax = pricewithtax;
    }

    public String getPricewithouttax() {
        return pricewithouttax;
    }

    public void setPricewithouttax(String pricewithouttax) {
        this.pricewithouttax = pricewithouttax;
    }

    public String getTax_status() {
        return tax_status;
    }

    public void setTax_status(String tax_status) {
        this.tax_status = tax_status;
    }

    public int getMasterproductid() {
        return masterproductid;
    }

    public void setMasterproductid(int masterproductid) {
        this.masterproductid = masterproductid;
    }

    public int getMerchantlistid() {
        return merchantlistid;
    }

    public void setMerchantlistid(int merchantlistid) {
        this.merchantlistid = merchantlistid;
    }

    public int getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(int merchantid) {
        this.merchantid = merchantid;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPricewithtax() {
        return pricewithtax;
    }



    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(int product_mrp) {
        this.product_mrp = product_mrp;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }



    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productname);
        parcel.writeString(mrp);
        parcel.writeInt(quantity);
        parcel.writeInt(selling_price);
        parcel.writeInt(product_mrp);
        parcel.writeInt(tax);
        parcel.writeString(avg_time_to_deliver);
        parcel.writeInt(shipping);
        parcel.writeString(tax_status);
        parcel.writeInt(masterproductid);
        parcel.writeInt(merchantlistid);
        parcel.writeInt(merchantid);
        parcel.writeString(measure);
        parcel.writeString(icon);
        parcel.writeString(pricewithtax);
        parcel.writeString(pricewithouttax);
    }
}
