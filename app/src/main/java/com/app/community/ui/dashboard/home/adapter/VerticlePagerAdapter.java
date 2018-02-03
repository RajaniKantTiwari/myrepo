package com.app.community.ui.dashboard.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsRowBinding;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

/**
 * Created by rizvan on 12/13/16.
 */

public class VerticlePagerAdapter extends PagerAdapter {
    private final ArrayList<News> newsList;
    private final int position;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public VerticlePagerAdapter(Context context, ArrayList<News> newsList, int position) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.newsList = newsList;
        this.position = position;
    }

    @Override
    public int getCount() {
        return CommonUtils.isNotNull(newsList) ? newsList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FragmentNewsRowBinding mBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.fragment_news_row, container, false);
        if (CommonUtils.isNotNull(newsList) && newsList.size() > position) {
            News news = newsList.get(position);
            GlideUtils.loadImage(mContext, news.getDisplay_image(), mBinding.ivNews, null, R.drawable.background_placeholder);
            mBinding.setNews(news);
        }
        container.addView(mBinding.getRoot());

        return mBinding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
