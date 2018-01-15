package com.app.community.ui.dashboard.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsRowBinding;

/**
 * Created by rizvan on 12/13/16.
 */

public class VerticlePagerAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;

    public VerticlePagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       FragmentNewsRowBinding mBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.fragment_news_row, container, false);
        if (position % 2 == 0) {
            mBinding.ivNews.setImageResource(R.drawable.order_detail);
           mBinding.tvHeading.setText("However, South Africa's crisis man, AB de Villiers, was at again. In company of a feisty Dean Elgar, de Villiers steadied the ship for his team. At tea, SA (60/2) had extended their lead to 88, which in");
            mBinding.tvDate.setText("23 may 2017");
            mBinding.tvDescription.setText(mContext.getResources().getString(R.string.lorem_ipsum));
        }
        else{
            mBinding.ivNews.setImageResource(R.drawable.ambulance);
            mBinding.ivNews.setBackground( mContext.getResources().getDrawable(R.drawable.ambulance));
            mBinding.tvHeading.setText(mContext.getResources().getString(R.string.lorem_ipsum));
            mBinding.tvDate.setText("15 jan 2018");
            mBinding.tvDescription.setText(mContext.getResources().getString(R.string.slide_1_desc));

        }
        container.addView(mBinding.getRoot());

        return mBinding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
