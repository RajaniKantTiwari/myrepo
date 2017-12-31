package com.app.community.network.request.dashboard;

/**
 * Created by Amul on 28/12/17.
 */

public class MeetingEventRequest {
    private double lat;
    private double lng;
    private int page;
    private int size;


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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
