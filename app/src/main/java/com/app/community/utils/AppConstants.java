package com.app.community.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.community.utils.AppConstants.FRAGMENTS.CHECKOUT_FRAGMENT;

/**
 * Created by arvind on 01/11/17.
 */

public interface AppConstants {
    String AUTHORIZATION = "Authorization";
    String SUCCESS = "success";
    int DEVICE_TOKEN_RESPONSE = 1;
    int RIGHT_DRAWER_RESPONSE = 2;
    String FORBIDDEN = "403";

    @IntDef({CHECKOUT_FRAGMENT})
    @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int CHECKOUT_FRAGMENT = 0;
    }

    int HOME = 0;
    int ORDER = 1;
    int YOURADDRESS = 2;
    int YOURCREDIT = 3;
    int NOTIFICATION = 4;
    int ABOUTUS = 5;
    int HELPSUPPORT = 6;
}
