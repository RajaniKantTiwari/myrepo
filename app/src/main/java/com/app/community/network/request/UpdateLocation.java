package com.app.community.network.request;

/**
 * Created by rajnikant on 10/02/18.
 */

public class UpdateLocation {
    private double lat;
    private double lng;

    public UpdateLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
