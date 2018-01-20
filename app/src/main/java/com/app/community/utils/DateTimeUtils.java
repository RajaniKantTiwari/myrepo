package com.app.community.utils;

import android.content.Context;


import com.app.community.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by neeraj on 25/12/17.
 */

public class DateTimeUtils {


    public static String convertMilliToDDMMYYYY(long millis, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.UK);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(new Date(millis));
    }


    public static String convertMilliToDDMMYYYYLocal(long millis, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(new Date(millis));
    }


    public static String convertTimeStampFormat(String timeStamp, String inputFormat, String outPutFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String convertedDate = "";
        try {
            Date value = formatter.parse(timeStamp);
            convertedDate = formatter.format(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }


    public static String convertTimeStampFormatLocal(String timeStamp, String inputFormat, String outPutFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(inputFormat);
        formatter.setTimeZone(TimeZone.getDefault());
        String convertedDate = "";
        try {
            Date value = formatter.parse(timeStamp);
            convertedDate = formatter.format(value);
        } catch (ParseException e) {
            LogUtils.LOGE(DateTimeUtils.class.getName(), e.toString());
        }

        return convertedDate;
    }


    /**
     * Convert Time from milli to D MMM format
     *
     * @param millis
     * @return
     */
    public static String convertMilliToDMMM(long millis) {
        //HH for 24 hour format hh for 12 hour
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM", Locale.UK);
        formatter.setTimeZone(TimeZone.getTimeZone(GeneralConstant.TIME_ZONE_UTC));
        return formatter.format(new Date(millis));
    }


    public static long getCurrenTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTime(new Date());
        return calendar.getTime().getTime();
    }

    public static long getCurrenTimeInMillisLocal() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTime(new Date());
        return calendar.getTime().getTime();
    }

    public static String convertSecToMinSec(int seconds) {
        StringBuilder builder = new StringBuilder();
        if (seconds > 60) {
            int min = seconds / 60;
            int sec = seconds % 60;
            builder.append(min);
            builder.append(":");
            builder.append(sec < 10 ? "0" : "");
            builder.append(sec);

        } else {
            builder.append("00:");
            builder.append(seconds < 10 ? "0" : "");
            builder.append(seconds);
        }
        return builder.toString();
    }


    public static String convertMillToLocalTimeDate(Context context, long mills) {
        Date date = new Date(mills);
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.h_mm_a), Locale.UK);

        sdf.setTimeZone(TimeZone.getDefault());
        String time = sdf.format(date);

        sdf = new SimpleDateFormat(context.getString(R.string.dd_MMM));
        sdf.setTimeZone(TimeZone.getDefault());
        String day = sdf.format(date);

        return time + " " + day;

    }

    public static String getCurrentDate(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setTimeZone(TimeZone.getDefault());
        return formatter.format(new Date());
    }
}
