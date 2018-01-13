package com.app.community.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.app.community.BuildConfig;
import com.app.community.R;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dialogfragment.ContactDialogFragment;
import com.app.community.ui.dialogfragment.ContactImpPlaceDialogFragment;
import com.app.community.ui.dialogfragment.CustomDialogFragment;
import com.app.community.ui.dialogfragment.OrderDialogFragment;
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
    public static void showContactDialog(AppCompatActivity activity, Bundle bundle,ContactDialogFragment.ContactDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        ContactDialogFragment alertdFragment = new ContactDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }


    public static void showOrderDialog(AppCompatActivity activity, Bundle bundle,OrderDialogFragment.OrderDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        OrderDialogFragment alertdFragment = new OrderDialogFragment();
        alertdFragment.addListener(listener);
        alertdFragment.setArguments(bundle);
        // Show Alert CustomDialogFragment
        alertdFragment.show(fm, "");
    }
    public static void showContactImpDialog(AppCompatActivity activity, Bundle bundle,ContactImpPlaceDialogFragment.ContactDialogListener listener) {
        FragmentManager fm = activity.getSupportFragmentManager();
        ContactImpPlaceDialogFragment alertdFragment = new ContactImpPlaceDialogFragment();
        alertdFragment.addListener(listener);
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
    public static int getColor(Context context, int color) {
        return ContextCompat.getColor(context, color);
    }

    public static void clicked(View view){
        final Animation animation = new AlphaAnimation(1.0f, 0.7f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        view.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Animation animation = new AlphaAnimation(0.7f, 1.0f);
                animation.setDuration(200);
                animation.setFillAfter(true);
                view.startAnimation(animation);
            }
        },200);
    }

    public static void setDialog(Dialog dialog) {
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
    }

    public static void setPadding(Dialog dialog,Activity activity) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, convertDpToPx(10,activity));
        dialog.getWindow().setBackgroundDrawable(inset);
    }
}
