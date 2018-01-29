package com.app.community.network.response.dashboard.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 29/01/18.
 */

public class Emergency implements Parcelable {
    private String id;
    private String service_type;
    private String descr;
    private String subject;
    private String lat;
    private String lng;
    private String display_config;
    private String created_at;
    private String updated_at;
    private String isactive;
    private String display_order;
    private String phone_no;
    private String icon;
    private String email;
    private String distance;
    public Emergency(){

    }
    protected Emergency(Parcel in) {
        id = in.readString();
        service_type = in.readString();
        descr = in.readString();
        subject = in.readString();
        lat = in.readString();
        lng = in.readString();
        display_config = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
        display_order = in.readString();
        phone_no = in.readString();
        icon = in.readString();
        email = in.readString();
        distance = in.readString();
    }

    public static final Creator<Emergency> CREATOR = new Creator<Emergency>() {
        @Override
        public Emergency createFromParcel(Parcel in) {
            return new Emergency(in);
        }

        @Override
        public Emergency[] newArray(int size) {
            return new Emergency[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDisplay_config() {
        return display_config;
    }

    public void setDisplay_config(String display_config) {
        this.display_config = display_config;
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

    public String getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(String display_order) {
        this.display_order = display_order;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(service_type);
        dest.writeString(descr);
        dest.writeString(subject);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(display_config);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(isactive);
        dest.writeString(display_order);
        dest.writeString(phone_no);
        dest.writeString(icon);
        dest.writeString(email);
        dest.writeString(distance);
    }
}
