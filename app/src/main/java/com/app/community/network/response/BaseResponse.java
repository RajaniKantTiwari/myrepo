package com.app.community.network.response;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by on 17/01/17.
 * To handle base common result.
 */

public class BaseResponse implements Parcelable {

    private String message;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BaseResponse(){

    }
    protected BaseResponse(Parcel in) {
        message = in.readString();
        status= in.readString();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(status);

    }
}
