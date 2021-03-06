package com.app.community.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.app.community.BuildConfig;
import com.app.community.R;
import com.app.community.ui.WelcomeScreenActivity;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dialogfragment.CustomDialogFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.app.community.utils.GeneralConstant.TAG;

/**
 * Created by arvind on 01/11/17.
 */

public class CommonUtils {
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static int convertDpToPx(int dp, Context context) {
        return Math.round(dp * (context.getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }
    /**
     * Show toast message for long time
     *
     * @param context
     * @param msg
     */
    public static void showToastLong(Context context, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }
    //Check object is null
    public static boolean isNull(Object obj) {
        return obj == null;
    }
    public static void showDialog(AppCompatActivity activity, Bundle bundle,CustomDialogFragment.CustomDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        CustomDialogFragment alertdFragment = new CustomDialogFragment();
        alertdFragment.DialogListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }

    public static boolean isNotNull(Object object) {
        return object!=null;
    }
    public static  boolean checkService(BaseActivity activity){
        if (!BuildConfig.DEBUG && CommonUtils.isSimulator()) {
            activity.showToast(activity.getResources().getString(R.string.google_service_not_present));
            return false;
        }
        if (!BuildConfig.DEBUG && !CommonUtils.checkGooglePlaySevices(activity)) {
            activity.showToast(activity.getResources().getString(R.string.google_service_not_present));
            return false;
        }
        return true;
    }
    public static boolean isSimulator() {
        boolean isSimulator = "google_sdk".equals(Build.PRODUCT)
                || "vbox86p".equals(Build.PRODUCT)
                || "sdk".equals(Build.PRODUCT);
        LogUtils.LOGD(TAG, "Build.PRODUCT= " + Build.PRODUCT + "  isSimulator= "
                + isSimulator);

        return isSimulator;
    }
    public static boolean checkGooglePlaySevices(final Activity activity) {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(activity);
        if(result != ConnectionResult.SUCCESS) {
            if(googleAPI.isUserResolvableError(result)) {
                Dialog dialog=googleAPI.getErrorDialog(activity, result,
                        PLAY_SERVICES_RESOLUTION_REQUEST);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
                dialog.show();
            }
            return false;
        }

        return true;
    }
    /**
     * Convert Time from milli to DD/MM/YYYY format
     *
     * @param millis
     * @return
     */
    public static String convertMilliToEEEMMMDYYYY(long millis) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d, yyyy hh:mm aaa");
        return formatter.format(new Date(millis));
    }
    public static String convertMilliToTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aaa");

        return formatter.format(new Date(time));
    }
}
