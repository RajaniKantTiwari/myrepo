package com.app.community.utils;

import com.orhanobut.hawk.Hawk;

/**
 * Created by arvind on 01/11/17.
 */

public class PreferenceUtil {
    public PreferenceUtil() {
    }

    public static void setToken(String token) {
        Hawk.put(PreferenceConstants.TOKEN, token);
    }

    public static String getToken() {
        return Hawk.get(PreferenceConstants.TOKEN);

    }

    public static void setUserName(String userName) {
        Hawk.put(PreferenceConstants.USER_NAME, userName);
    }
    public static void getUserName() {
        Hawk.get(PreferenceConstants.USER_NAME, PreferenceConstants.DEFAULT);
    }

    public static void setLatitude(double latitude) {
        Hawk.put(PreferenceConstants.USER_LATITUDE, latitude);
    }

    public static void setLongitude(double longitude) {
        Hawk.put(PreferenceConstants.USER_LONGITUDE, longitude);
    }
    public static void getLatitude() {
        Hawk.get(PreferenceConstants.USER_LATITUDE, 0);
    }

    public static void getLongitude() {
        Hawk.get(PreferenceConstants.USER_LONGITUDE, 0);
    }
}
