package com.app.community.network.response.dashboard.feed;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajnikant on 19/01/18.
 */

public class StoreImages implements Parcelable{
    private String id;
    private String merchantstore_id;
    private String path;
    private String status;
    private String created_at;
    private String updated_at;
    private String isactive;

    public StoreImages() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantstore_id() {
        return merchantstore_id;
    }

    public void setMerchantstore_id(String merchantstore_id) {
        this.merchantstore_id = merchantstore_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    protected StoreImages(Parcel in) {
        id = in.readString();
        merchantstore_id = in.readString();
        path = in.readString();
        status = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        isactive = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(merchantstore_id);
        dest.writeString(path);
        dest.writeString(status);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(isactive);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreImages> CREATOR = new Creator<StoreImages>() {
        @Override
        public StoreImages createFromParcel(Parcel in) {
            return new StoreImages(in);
        }

        @Override
        public StoreImages[] newArray(int size) {
            return new StoreImages[size];
        }
    };
}
