package com.app.community.network.response.dashboard.home;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 29/01/18.
 */

public class WelcomeHomeData extends BaseResponse {
    private ArrayList<Banner> banner;
    private ArrayList<News> news;
    private ArrayList<Offer>   offer;
    private ArrayList<Emergency> emergency;

    public ArrayList<Banner> getBanner() {
        return banner;
    }

    public void setBanner(ArrayList<Banner> banner) {
        this.banner = banner;
    }

    public ArrayList<News> getNews() {
        return news;
    }

    public void setNews(ArrayList<News> news) {
        this.news = news;
    }

    public ArrayList<Offer> getOffer() {
        return offer;
    }

    public void setOffer(ArrayList<Offer> offer) {
        this.offer = offer;
    }

    public ArrayList<Emergency> getEmergency() {
        return emergency;
    }

    public void setEmergency(ArrayList<Emergency> emergency) {
        this.emergency = emergency;
    }
}
