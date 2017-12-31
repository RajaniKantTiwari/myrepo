package com.app.community.widget.bottomnavigation;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.community.R;

import java.util.ArrayList;
import java.util.List;


public class BottomNavigationBar implements View.OnClickListener {

    public static final int MENU_BAR_1 = 0;
    public static final int MENU_BAR_2 = 1;
    public static final int MENU_BAR_3 = 2;
    public static final int MENU_BAR_4 = 3;
    public int CURRENT_TAB;

    private List<NavigationPage> mNavigationPageList = new ArrayList<>();

    private Context mContext;
    private AppCompatActivity mActivity;
    private BottomNavigationMenuClickListener mListener;

    private RelativeLayout mLLBar1, mLLBar2, mLLBar3, mLLBar4;
    private View mViewBar1, mViewBar2, mViewBar3, mViewBar4;
    private AppCompatImageView mImageViewBar1, mImageViewBar2, mImageViewBar3, mImageViewBar4;

    public BottomNavigationBar(Context mContext, List<NavigationPage> pages, BottomNavigationMenuClickListener listener) {

        // initialize variables
        this.mContext = mContext;
        this.mActivity = (AppCompatActivity) mContext;
        this.mListener = listener;
        this.mNavigationPageList = pages;

        // getting reference to bar linear layout viewgroups
        this.mLLBar1 = mActivity.findViewById(R.id.linearLayoutBar1);
        this.mLLBar2 = mActivity.findViewById(R.id.linearLayoutBar2);
        this.mLLBar3 = mActivity.findViewById(R.id.linearLayoutBar3);
        this.mLLBar4 = mActivity.findViewById(R.id.linearLayoutBar4);

        // getting reference to bar upper highlight
        this.mViewBar1 = mActivity.findViewById(R.id.viewBar1);
        this.mViewBar2 = mActivity.findViewById(R.id.viewBar2);
        this.mViewBar3 = mActivity.findViewById(R.id.viewBar3);
        this.mViewBar4 = mActivity.findViewById(R.id.viewBar4);

        // getting reference to bar icons
        this.mImageViewBar1 = mActivity.findViewById(R.id.imageViewBar1);
        this.mImageViewBar2 = mActivity.findViewById(R.id.imageViewBar2);
        this.mImageViewBar3 = mActivity.findViewById(R.id.imageViewBar3);
        this.mImageViewBar4 = mActivity.findViewById(R.id.imageViewBar4);

        // setting the icons
        this.mImageViewBar1.setImageDrawable(mNavigationPageList.get(0).getIcon());
        this.mImageViewBar2.setImageDrawable(mNavigationPageList.get(1).getIcon());
        this.mImageViewBar3.setImageDrawable(mNavigationPageList.get(2).getIcon());
        this.mImageViewBar4.setImageDrawable(mNavigationPageList.get(3).getIcon());

        // setting click listeners
        this.mLLBar1.setOnClickListener(this);
        this.mLLBar2.setOnClickListener(this);
        this.mLLBar3.setOnClickListener(this);
        this.mLLBar4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // setting clicked bar as highlighted view


        // triggering click listeners
        if (view.getId() == R.id.linearLayoutBar1) {
            setView(MENU_BAR_1);
            mListener.onTabSelected(MENU_BAR_1);
            return;
        } else if (view.getId() == R.id.linearLayoutBar2) {
            setView(MENU_BAR_2);
            mListener.onTabSelected(MENU_BAR_2);
            return;
        } else if (view.getId() == R.id.linearLayoutBar3) {
            setView(MENU_BAR_3);
            mListener.onTabSelected(MENU_BAR_3);
            return;
        } else if (view.getId() == R.id.linearLayoutBar4) {
            setView(MENU_BAR_4);
            mListener.onTabSelected(MENU_BAR_4);
            return;
        } else {
            return;
        }

    }


    public void selectItem(int index) {
        setView(index);
    }


    private void setSelectedItem(View view) {
        if (view != null) {
            view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white_e2e2e2));
        }
    }

    /**
     * sets the clicked view as selected, resets other views
     *
     * @param index
     */

    private boolean setView(int index) {

//        if (index == CURRENT_TAB) return false;
        CURRENT_TAB = index;
        // seting all highlight bar as invisible
        this.mViewBar1.setVisibility(View.INVISIBLE);
        this.mViewBar2.setVisibility(View.INVISIBLE);
        this.mViewBar3.setVisibility(View.INVISIBLE);
        this.mViewBar4.setVisibility(View.INVISIBLE);
        this.mLLBar1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorBackgroundNav));
        this.mLLBar2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorBackgroundNav));
        this.mLLBar3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorBackgroundNav));
        this.mLLBar4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorBackgroundNav));

        // resetting colors of all icons
//        this.mImageViewBar1.setColorFilter(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mImageViewBar2.setColorFilter(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mImageViewBar3.setColorFilter(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mImageViewBar4.setColorFilter(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mImageViewBar5.setColorFilter(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));

        // resetting colors of all titles
//        this.mTextViewBar1.setTextColor(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mTextViewBar2.setTextColor(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mTextViewBar3.setTextColor(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mTextViewBar4.setTextColor(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));
//        this.mTextViewBar5.setTextColor(ContextCompat.getColor(mContext, R.color.colorNavAccentUnselected));


        switch (index) {
            case MENU_BAR_1:
                this.mViewBar1.setVisibility(View.VISIBLE);
                setSelectedItem(mLLBar1);
                break;

            case MENU_BAR_2:
                this.mViewBar2.setVisibility(View.VISIBLE);
                setSelectedItem(mLLBar2);
                break;

            case MENU_BAR_3:
                this.mViewBar3.setVisibility(View.VISIBLE);
                setSelectedItem(mLLBar3);
                break;

            case MENU_BAR_4:
                this.mViewBar4.setVisibility(View.VISIBLE);
                setSelectedItem(mLLBar4);
                break;

        }
        return true;

    }

    public interface BottomNavigationMenuClickListener {
        void onTabSelected(int menuType);
    }

}
