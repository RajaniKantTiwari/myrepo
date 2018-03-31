package com.app.community.network.response.dashboard.offer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 31/03/18.
 */

public class MerchantOffer implements Parcelable {
    private String id;
    private String image;
    private String name;
    private String nationality;
    private String company_id;
    private String address;
    private String city;
    private String pincode;
    private String phoneno;
    private String latitude;
    private String longitude;
    private String logo;
    private String rating;
    private String status;
    private String store_name;
    private String banner_image;
    private String open_timings;
    private String net_total_reviews;
    private String close_timings;
    private String m_category_IDs;
    private String m_category_names;
    private String merchant_paid;
    private String created_at;
    private String updated_at;
    private String userid;
    private String username;
    private String email;
    private String min_order;
    private String avg_time_to_deliver;
    private String shipping;
    private String store_descr;
    private String preferred_payment_options;
    private String country;
    private String state;
    private String product_name;
    private String product_color_code;
    private String product_icon;
    private String product_mrp;
    private String selling_price;
    private String distance;
    private String offer_percent;

    protected MerchantOffer(Parcel in) {
        id = in.readString();
        image = in.readString();
        name = in.readString();
        nationality = in.readString();
        company_id = in.readString();
        address = in.readString();
        city = in.readString();
        pincode = in.readString();
        phoneno = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        logo = in.readString();
        rating = in.readString();
        status = in.readString();
        store_name = in.readString();
        banner_image = in.readString();
        open_timings = in.readString();
        net_total_reviews = in.readString();
        close_timings = in.readString();
        m_category_IDs = in.readString();
        m_category_names = in.readString();
        merchant_paid = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        userid = in.readString();
        username = in.readString();
        email = in.readString();
        min_order = in.readString();
        avg_time_to_deliver = in.readString();
        shipping = in.readString();
        store_descr = in.readString();
        preferred_payment_options = in.readString();
        country = in.readString();
        state = in.readString();
        product_name = in.readString();
        product_color_code = in.readString();
        product_icon = in.readString();
        product_mrp = in.readString();
        selling_price = in.readString();
        distance = in.readString();
        offer_percent = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getOpen_timings() {
        return open_timings;
    }

    public void setOpen_timings(String open_timings) {
        this.open_timings = open_timings;
    }

    public String getNet_total_reviews() {
        return net_total_reviews;
    }

    public void setNet_total_reviews(String net_total_reviews) {
        this.net_total_reviews = net_total_reviews;
    }

    public String getClose_timings() {
        return close_timings;
    }

    public void setClose_timings(String close_timings) {
        this.close_timings = close_timings;
    }

    public String getM_category_IDs() {
        return m_category_IDs;
    }

    public void setM_category_IDs(String m_category_IDs) {
        this.m_category_IDs = m_category_IDs;
    }

    public String getM_category_names() {
        return m_category_names;
    }

    public void setM_category_names(String m_category_names) {
        this.m_category_names = m_category_names;
    }

    public String getMerchant_paid() {
        return merchant_paid;
    }

    public void setMerchant_paid(String merchant_paid) {
        this.merchant_paid = merchant_paid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMin_order() {
        return min_order;
    }

    public void setMin_order(String min_order) {
        this.min_order = min_order;
    }

    public String getAvg_time_to_deliver() {
        return avg_time_to_deliver;
    }

    public void setAvg_time_to_deliver(String avg_time_to_deliver) {
        this.avg_time_to_deliver = avg_time_to_deliver;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getStore_descr() {
        return store_descr;
    }

    public void setStore_descr(String store_descr) {
        this.store_descr = store_descr;
    }

    public String getPreferred_payment_options() {
        return preferred_payment_options;
    }

    public void setPreferred_payment_options(String preferred_payment_options) {
        this.preferred_payment_options = preferred_payment_options;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_color_code() {
        return product_color_code;
    }

    public void setProduct_color_code(String product_color_code) {
        this.product_color_code = product_color_code;
    }

    public String getProduct_icon() {
        return product_icon;
    }

    public void setProduct_icon(String product_icon) {
        this.product_icon = product_icon;
    }

    public String getProduct_mrp() {
        return product_mrp;
    }

    public void setProduct_mrp(String product_mrp) {
        this.product_mrp = product_mrp;
    }

    public String getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(String selling_price) {
        this.selling_price = selling_price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getOffer_percent() {
        return offer_percent;
    }

    public void setOffer_percent(String offer_percent) {
        this.offer_percent = offer_percent;
    }

    public static final Creator<MerchantOffer> CREATOR = new Creator<MerchantOffer>() {
        @Override
        public MerchantOffer createFromParcel(Parcel in) {
            return new MerchantOffer(in);
        }

        @Override
        public MerchantOffer[] newArray(int size) {
            return new MerchantOffer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(nationality);
        parcel.writeString(company_id);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(pincode);
        parcel.writeString(phoneno);
        parcel.writeString(latitude);
        parcel.writeString(longitude);
        parcel.writeString(logo);
        parcel.writeString(rating);
        parcel.writeString(status);
        parcel.writeString(store_name);
        parcel.writeString(banner_image);
        parcel.writeString(open_timings);
        parcel.writeString(net_total_reviews);
        parcel.writeString(close_timings);
        parcel.writeString(m_category_IDs);
        parcel.writeString(m_category_names);
        parcel.writeString(merchant_paid);
        parcel.writeString(created_at);
        parcel.writeString(updated_at);
        parcel.writeString(userid);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(min_order);
        parcel.writeString(avg_time_to_deliver);
        parcel.writeString(shipping);
        parcel.writeString(store_descr);
        parcel.writeString(preferred_payment_options);
        parcel.writeString(country);
        parcel.writeString(state);
        parcel.writeString(product_name);
        parcel.writeString(product_color_code);
        parcel.writeString(product_icon);
        parcel.writeString(product_mrp);
        parcel.writeString(selling_price);
        parcel.writeString(distance);
        parcel.writeString(offer_percent);
    }
}
