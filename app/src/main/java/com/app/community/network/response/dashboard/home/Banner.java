package com.app.community.network.response.dashboard.home;

/**
 * Created by rajnikant on 29/01/18.
 */

public class Banner {
    private String id;
    private String bannerimage;
    private String offer;
    private String lat;
    private String lng;
    private String created_at;
    private String distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBannerimage() {
        return bannerimage;
    }

    public void setBannerimage(String bannerimage) {
        this.bannerimage = bannerimage;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
