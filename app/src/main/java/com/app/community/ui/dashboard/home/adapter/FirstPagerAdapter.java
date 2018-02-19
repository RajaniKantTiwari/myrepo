package com.app.community.ui.dashboard.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentCategoryBinding;

public class FirstPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private final LayoutInflater mInflator;
    private Context mContext;
    private FragmentCategoryBinding itemBinding;

    public FirstPagerAdapter(Context mContext) {
        this.mContext = mContext;
        mInflator = LayoutInflater.from(mContext);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        itemBinding = DataBindingUtil.inflate(mInflator, R.layout.fragment_category, container, false);
        setListener();
        container.addView(itemBinding.getRoot());
        return itemBinding.getRoot();
    }

    private void setListener() {
        itemBinding.layoutPolitics.setOnClickListener(this);
        itemBinding.layoutHealth.setOnClickListener(this);
        itemBinding.layoutBusiness.setOnClickListener(this);
        itemBinding.layoutSports.setOnClickListener(this);
        itemBinding.layoutTravel.setOnClickListener(this);
        itemBinding.layoutTechnology.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (itemBinding.layoutPolitics == view) {

        } else if (itemBinding.layoutHealth == view) {

        } else if (itemBinding.layoutBusiness == view) {

        } else if (itemBinding.layoutSports == view) {

        } else if (itemBinding.layoutTravel == view) {

        } else if (itemBinding.layoutTechnology == view) {

        }
    }
}