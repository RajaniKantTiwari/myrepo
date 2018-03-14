package com.app.community.network.response.coupon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 14/03/18.
 */

public class MerchantCouponResponse implements Parcelable {
    private int id;
    private int merchant_id;
    private String offer_descr;
    private String offer_from;
    private String offer_to;
    private String offer_type;
    private String display_on_merchant_info;
    private String offer_bg_color;
    private String promo_code;
    private String max_amount;
    private String max_percentage;
    private String max_number;
    private String actual_code_used;
    private String max_per_customer;
    private String display_on_home_page;
    private String created_at;
    private String updated_at;
    private String isactive;

    public MerchantCouponResponse() {
    }

    protected MerchantCouponResponse(Parcel in) {
        id = in.readInt();
        merchant_id = in.readInt();
        offer_descr = in.readString();
        offer_from = in.readString();
        offer_to = in.readString();
        offer_type = in.readString();
        display_on_merchant_info = in.readString();
        offer_bg_color = in.readString();
        promo_code = in.readString();
        max_amount = in.readString();
        max_percentage = in.readString();
        max_number = in.readString();
        actual_code_used = in.readString();
        max_per_customer = in.readString();
        display_on_home_page = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
    }

    public static final Creator<MerchantCouponResponse> CREATOR = new Creator<MerchantCouponResponse>() {
        @Override
        public MerchantCouponResponse createFromParcel(Parcel in) {
            return new MerchantCouponResponse(in);
        }

        @Override
        public MerchantCouponResponse[] newArray(int size) {
            return new MerchantCouponResponse[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getOffer_descr() {
        return offer_descr;
    }

    public void setOffer_descr(String offer_descr) {
        this.offer_descr = offer_descr;
    }

    public String getOffer_from() {
        return offer_from;
    }

    public void setOffer_from(String offer_from) {
        this.offer_from = offer_from;
    }

    public String getOffer_to() {
        return offer_to;
    }

    public void setOffer_to(String offer_to) {
        this.offer_to = offer_to;
    }

    public String getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(String offer_type) {
        this.offer_type = offer_type;
    }

    public String getDisplay_on_merchant_info() {
        return display_on_merchant_info;
    }

    public void setDisplay_on_merchant_info(String display_on_merchant_info) {
        this.display_on_merchant_info = display_on_merchant_info;
    }

    public String getOffer_bg_color() {
        return offer_bg_color;
    }

    public void setOffer_bg_color(String offer_bg_color) {
        this.offer_bg_color = offer_bg_color;
    }

    public String getPromo_code() {
        return promo_code;
    }

    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }

    public String getMax_amount() {
        return max_amount;
    }

    public void setMax_amount(String max_amount) {
        this.max_amount = max_amount;
    }

    public String getMax_percentage() {
        return max_percentage;
    }

    public void setMax_percentage(String max_percentage) {
        this.max_percentage = max_percentage;
    }

    public String getMax_number() {
        return max_number;
    }

    public void setMax_number(String max_number) {
        this.max_number = max_number;
    }

    public String getActual_code_used() {
        return actual_code_used;
    }

    public void setActual_code_used(String actual_code_used) {
        this.actual_code_used = actual_code_used;
    }

    public String getMax_per_customer() {
        return max_per_customer;
    }

    public void setMax_per_customer(String max_per_customer) {
        this.max_per_customer = max_per_customer;
    }

    public String getDisplay_on_home_page() {
        return display_on_home_page;
    }

    public void setDisplay_on_home_page(String display_on_home_page) {
        this.display_on_home_page = display_on_home_page;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(merchant_id);
        parcel.writeString(offer_descr);
        parcel.writeString(offer_from);
        parcel.writeString(offer_to);
        parcel.writeString(offer_type);
        parcel.writeString(display_on_merchant_info);
        parcel.writeString(offer_bg_color);
        parcel.writeString(promo_code);
        parcel.writeString(max_amount);
        parcel.writeString(max_percentage);
        parcel.writeString(max_number);
        parcel.writeString(actual_code_used);
        parcel.writeString(max_per_customer);
        parcel.writeString(display_on_home_page);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(isactive);
    }
}
