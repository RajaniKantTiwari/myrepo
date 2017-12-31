package com.app.community.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.community.utils.ApiConstants.FRAGMENTS.CHECKOUT_FRAGMENT;

/**
 * Created by arvind on 01/11/17.
 */

public interface ApiConstants {
   String AUTHORIZATION ="Authorization";
    String SUCCESS = "success";

    @IntDef({CHECKOUT_FRAGMENT})
   @Retention(RetentionPolicy.SOURCE)
    @interface FRAGMENTS {
        int CHECKOUT_FRAGMENT=0;

    }
}
