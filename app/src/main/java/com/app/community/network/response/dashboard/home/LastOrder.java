package com.app.community.network.response.dashboard.home;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 01/02/18.
 */

public class LastOrder implements Parcelable,Observable{
    private String productname;
    private String icon;
    private String measure;
    private String rating;
    PropertyChangeRegistry registry=new PropertyChangeRegistry();

    protected LastOrder(Parcel in) {
        productname = in.readString();
        icon = in.readString();
        measure = in.readString();
        rating = in.readString();
    }

    public static final Creator<LastOrder> CREATOR = new Creator<LastOrder>() {
        @Override
        public LastOrder createFromParcel(Parcel in) {
            return new LastOrder(in);
        }

        @Override
        public LastOrder[] newArray(int size) {
            return new LastOrder[size];
        }
    };

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productname);
        dest.writeString(icon);
        dest.writeString(measure);
        dest.writeString(rating);
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        registry.remove(onPropertyChangedCallback);
    }
}
