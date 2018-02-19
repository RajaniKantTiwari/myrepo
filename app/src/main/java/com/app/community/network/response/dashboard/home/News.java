package com.app.community.network.response.dashboard.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 29/01/18.
 */

public class News implements Parcelable {
    private String id;
    private String statement;
    private String full_article;
    private String news_title;
    private String news_descr;
    private String from;
    private String display_image;
    private String is_global;
    private String latitude;
    private String longitude;
    private String category_name;
    private String distance;
    private String newsUrl="http://oimedia.in/index.php/author/webmaster/";
    public News(){

    }
    protected News(Parcel in) {
        id = in.readString();
        statement = in.readString();
        full_article = in.readString();
        news_title = in.readString();
        news_descr = in.readString();
        from = in.readString();
        display_image = in.readString();
        is_global = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        category_name = in.readString();
        distance = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getFull_article() {
        return full_article;
    }

    public void setFull_article(String full_article) {
        this.full_article = full_article;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_descr() {
        return news_descr;
    }

    public void setNews_descr(String news_descr) {
        this.news_descr = news_descr;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDisplay_image() {
        return display_image;
    }

    public void setDisplay_image(String display_image) {
        this.display_image = display_image;
    }

    public String getIs_global() {
        return is_global;
    }

    public void setIs_global(String is_global) {
        this.is_global = is_global;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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
        dest.writeString(statement);
        dest.writeString(full_article);
        dest.writeString(news_title);
        dest.writeString(news_descr);
        dest.writeString(from);
        dest.writeString(display_image);
        dest.writeString(is_global);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(category_name);
        dest.writeString(distance);
    }
}
