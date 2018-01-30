package com.app.community.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.community.utils.GeneralConstant.FRAGMENTS.CATEGORY_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.CONFIRM_ORDER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.HOME_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.LIVEORDER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.NEWS_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.NEWS_TAB_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PASTORDER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_LIST;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_MAP_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_SUBPRODUCT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.RECENT_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.SUBSCRIPTION_DETAIL_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.SUBSCRIPTION_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.WELCOME_HOME_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.ZOOMIMAGE_FRAGMENT;

/**
 * Created by arvind on 21/12/17.
 */

public interface GeneralConstant {

    String ARGS_INSTANCE = "com.app.constants";
    int PERMISSIONS_REQUEST_LOCATION = 99;
    long SPLASH_TIME = 800;
    String TITLE = "title";
    String USER_NAME = "user_name";
    String VISIBLE = "visible";
    String MOBILE_NUMBER = "mobile_number";
    int LIST_PRODUCT = 1;
    int MAP_PRODUCT = 2;
    String TAG = "";
    int ADDRESS_MAX_LENGTH = 100;
    int MAX_LENGTH = 50;
    float MAX_ZOOM = 17;
    float TILT = 90;
    float BEARING = 45;
    int REQUEST_CALL = 1;

    int RECENT = 1;
    int CATEGORY = 2;

    int LIVEORDER = 1;
    int PASTORDER = 2;
    String PRODUCT_INFO = "product_info";
    long DELAYTIME = 70;
    String RESPONSE = "response";
    long API_SERVICE = 500;
    String TIME_ZONE_UTC = "UTC";
    String SEARCH_STRING = "searchString";
    int PRODUCT = 1;
    int SERVICE = 2;

    int SUBSCRIPTION = 1;
    int SUBSCRIPTION_DETAIL = 2;
    int PAYMENT_HEIGHT = 40;
    String IMAGE_LIST = "image_list";
    String POSITION = "position";
    long ANIMATION_TIME = 500;
    String EMERGENCY = "emergency";
    String NEWS = "news";
    String NEWSLIST = "newslist";
    String DEVICETYPE="1";




    @IntDef({PRODUCT_MAP_FRAGMENT, PRODUCT_LIST, RECENT_FRAGMENT, CATEGORY_FRAGMENT,
            LIVEORDER_FRAGMENT, PASTORDER_FRAGMENT, WELCOME_HOME_FRAGMENT, NEWS_TAB_FRAGMENT
            , HOME_FRAGMENT, NEWS_FRAGMENT, CONFIRM_ORDER_FRAGMENT, PRODUCT_SUBPRODUCT, SUBSCRIPTION_DETAIL_FRAGMENT
            , SUBSCRIPTION_FRAGMENT,ZOOMIMAGE_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int PRODUCT_MAP_FRAGMENT = 1;
        int PRODUCT_LIST = 2;
        int RECENT_FRAGMENT = 3;
        int CATEGORY_FRAGMENT = 4;
        int LIVEORDER_FRAGMENT = 5;
        int PASTORDER_FRAGMENT = 6;
        int WELCOME_HOME_FRAGMENT = 7;
        int NEWS_TAB_FRAGMENT = 8;
        int HOME_FRAGMENT = 9;
        int NEWS_FRAGMENT = 10;
        int CONFIRM_ORDER_FRAGMENT = 11;
        int PRODUCT_SUBPRODUCT = 12;
        int SUBSCRIPTION_DETAIL_FRAGMENT = 13;
        int SUBSCRIPTION_FRAGMENT = 14;
        int ZOOMIMAGE_FRAGMENT=15;
    }
}
