package com.app.community.network.response.dashboard.home;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by rajnikant on 19/01/18.
 */

public class MerchantResponse implements Parcelable,Observable {
    private PropertyChangeRegistry registry = new PropertyChangeRegistry();

    private String id;
    private String image;
    private String name;
    private String address;
    private String city;
    private String opentime;
    private String closetime;
    private String logo;
    private String category;
    private String distance;
    private String pincode;
    private String latitude;
    private String longitude;
    private String banner_image;
    private String store_descr;
    private String rating;
    private String minorder;
    private String averagetime;
    private String offer;
    private String from;
    private String to;
    private String background_color;
    private String max_percentage;
    private String preferred_payment_options;
    private String store_name;


    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    private ArrayList<StoreImages> storeimages;

    public MerchantResponse() {
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getMax_percentage() {
        return max_percentage;
    }

    public void setMax_percentage(String max_percentage) {
        this.max_percentage = max_percentage;
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Bindable
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Bindable
    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }
    @Bindable
    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }
    @Bindable
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    @Bindable
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Bindable
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
    @Bindable
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    @Bindable
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    @Bindable
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    @Bindable
    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }
    @Bindable
    public String getStore_descr() {
        return store_descr;
    }

    public void setStore_descr(String store_descr) {
        this.store_descr = store_descr;
    }
    @Bindable
    public String getPreferred_payment_options() {
        return preferred_payment_options;
    }

    public void setPreferred_payment_options(String preferred_payment_options) {
        this.preferred_payment_options = preferred_payment_options;
    }
    @Bindable
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    @Bindable
    public String getMinorder() {
        return minorder;
    }

    public void setMinorder(String minorder) {
        this.minorder = minorder;
    }
    @Bindable
    public String getAveragetime() {
        return averagetime;
    }
    @Bindable
    public void setAveragetime(String averagetime) {
        this.averagetime = averagetime;
    }
    @Bindable
    public ArrayList<StoreImages> getStoreimages() {
        return storeimages;
    }

    public void setStoreimages(ArrayList<StoreImages> storeimages) {
        this.storeimages = storeimages;
    }

    protected MerchantResponse(Parcel in) {
        id = in.readString();
        image = in.readString();
        name = in.readString();
        address = in.readString();
        city = in.readString();
        opentime = in.readString();
        closetime = in.readString();
        logo = in.readString();
        category = in.readString();
        distance = in.readString();
        pincode = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        banner_image = in.readString();
        store_descr = in.readString();
        preferred_payment_options = in.readString();
        rating = in.readString();
        minorder = in.readString();
        averagetime = in.readString();
        offer=in.readString();
        from=in.readString();
        to=in.readString();
        background_color=in.readString();
        max_percentage=in.readString();
        storeimages = in.createTypedArrayList(StoreImages.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(opentime);
        dest.writeString(closetime);
        dest.writeString(logo);
        dest.writeString(category);
        dest.writeString(distance);
        dest.writeString(pincode);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(banner_image);
        dest.writeString(store_descr);
        dest.writeString(preferred_payment_options);
        dest.writeString(rating);
        dest.writeString(offer);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(background_color);
        dest.writeString(max_percentage);
        dest.writeString(minorder);
        dest.writeString(averagetime);
        dest.writeTypedList(storeimages);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MerchantResponse> CREATOR = new Creator<MerchantResponse>() {
        @Override
        public MerchantResponse createFromParcel(Parcel in) {
            return new MerchantResponse(in);
        }

        @Override
        public MerchantResponse[] newArray(int size) {
            return new MerchantResponse[size];
        }
    };
    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        registry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        registry.remove(callback);
    }
}
