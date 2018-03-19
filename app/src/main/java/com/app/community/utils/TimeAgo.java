package com.app.community.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.app.community.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeAgo {

    private static final int minute = 60;
    private static final int hour = minute * 60;
    private static final int day = hour * 24;
    private static final int week = day * 7;
    private static final int month = day * 30;
    private static final int year = month * 12;

    @SuppressLint("SimpleDateFormat")
    public static String getAgoTime(Context context, String strDate) {
        long diff;
        long fromDate=0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = sdf.parse(strDate);
            fromDate=date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long ms2 = CommonUtils.localToGMT().getTime();

        // get difference in milli seconds
        diff = ms2 - fromDate;
        if (diff < 0) {
            return 0 + " " + context.getString(R.string.second_ago);
        }

        int second = 1000;
        int diffInSec = Math.abs((int) (diff / (second)));
        String difference;
        int temp;
        if (diffInSec < minute) {

            difference = diffInSec + " " + context.getString(diffInSec <= 1 ? R.string.second_ago : R.string.seconds_ago);
        } else if ((diffInSec / hour) < 1) {

            temp = (diffInSec / minute);
            difference = temp + " " + context.getString(temp <= 1 ? R.string.minute_ago : R.string.minutes_ago);
        } else if ((diffInSec / day) < 1) {

            temp = (diffInSec / hour);
            difference = temp + " " + context.getString(temp <= 1 ? R.string.hour_ago : R.string.hours_ago);
        } else if ((diffInSec / week) < 1) {

            temp = (diffInSec / day);
            difference = temp + " " + context.getString(temp <= 1 ? R.string.day_ago : R.string.days_ago);
        } else if ((diffInSec / month) < 1) {

            temp = (diffInSec / week);
            difference = temp + " " + context.getString(temp <= 1 ? R.string.week_ago : R.string.weeks_ago);
        } else if ((diffInSec / year) < 1) {

            temp = (diffInSec / month);
            difference = temp + " " + context.getString(temp <= 1 ? R.string.month_ago : R.string.months_ago);
        } else {
            temp = diffInSec / year;
            difference = temp + " " + context.getString(temp <= 1 ? R.string.year_ago : R.string.years_ago);
        }


        LogUtils.LOGD("time difference is: ", "" + difference);
        return difference;
    }

}