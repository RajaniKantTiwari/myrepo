package com.app.community.network.response.dashboard.user;

import com.app.community.network.response.BaseResponse;

import java.util.ArrayList;

/**
 * Created by rajnikant on 19/03/18.
 */

public class UserProfileData extends BaseResponse{
    private ArrayList<UserProfile> prifiledata;

    public ArrayList<UserProfile> getPrifiledata() {
        return prifiledata;
    }

    public void setPrifiledata(ArrayList<UserProfile> prifiledata) {
        this.prifiledata = prifiledata;
    }
}
