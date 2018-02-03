package com.app.community.utils;

import com.orhanobut.hawk.Hawk;

/**
 * Created by arvind on 01/11/17.
 */

public class UserPreference {
    public UserPreference() {
    }

    public static void setAuthToken(String token) {
        Hawk.put(PreferenceConstants.TOKEN, token);
    }

    public static String getAuthToken() {
        return Hawk.get(PreferenceConstants.TOKEN);

    }

    public static void setUserName(String userName) {
        Hawk.put(PreferenceConstants.USER_NAME, userName);
    }
    public static String getUserName() {
       return Hawk.get(PreferenceConstants.USER_NAME, null);
    }

    public static void setLatitude(double latitude) {
        Hawk.put(PreferenceConstants.USER_LATITUDE, latitude);
    }

    public static void setLongitude(double longitude) {
        Hawk.put(PreferenceConstants.USER_LONGITUDE, longitude);
    }
    public static double getLatitude() {
       return Hawk.get(PreferenceConstants.USER_LATITUDE, 0.0);
    }

    public static double getLongitude() {
       return Hawk.get(PreferenceConstants.USER_LONGITUDE, 0.0);
    }

    public static void setUserId(int userId) {
        Hawk.put(PreferenceConstants.USER_ID, userId);
    }
    public static int getUserId() {
       return Hawk.get(PreferenceConstants.USER_ID,0);
    }

    public static void setDeviceToken(String token) {
        Hawk.put(PreferenceConstants.DEVICE_TOKEN, token);
    }
    public static String getDeviceToken() {
        return Hawk.get(PreferenceConstants.DEVICE_TOKEN,null);
    }

    public static void setLogin(boolean isLogin) {
        Hawk.put(PreferenceConstants.IS_LOGIN, isLogin);
    }
    public static boolean isLogin() {
       return Hawk.get(PreferenceConstants.IS_LOGIN, false);
    }

    public static void setUserMono(String userMono) {
        Hawk.put(PreferenceConstants.USER_MONO, userMono);
    }
    public static String getUserMono() {
       return Hawk.get(PreferenceConstants.USER_MONO, null);
    }
}
