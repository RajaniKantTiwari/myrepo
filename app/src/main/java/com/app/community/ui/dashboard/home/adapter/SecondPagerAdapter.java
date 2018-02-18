package com.app.community.ui.dashboard.home.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ItemNewsRowBinding;
import com.app.community.event.NewsEventDetail;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.ui.dashboard.home.event.NewsEvent;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class SecondPagerAdapter extends PagerAdapter {

    private final ArrayList<News> newsList;
    private final LayoutInflater mInflator;
    private Context mContext;

    public SecondPagerAdapter(Context mContext, ArrayList<News> newsList) {
        this.mContext = mContext;
        this.newsList = newsList;
        mInflator = LayoutInflater.from(mContext);
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ItemNewsRowBinding itemBinding = DataBindingUtil.inflate(mInflator, R.layout.item_news_row, container, false);
        if (CommonUtils.isNotNull(newsList) && newsList.size() > position) {
            GlideUtils.loadImage(mContext, newsList.get(position).getDisplay_image(), itemBinding.ivNews, null, R.drawable.background_placeholder);
            itemBinding.setNews(newsList.get(position));
        }
        itemBinding.tvDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new NewsEventDetail("http://oimedia.in/index.php/author/webmaster/"));
            }
        });
        container.addView(itemBinding.getRoot());
        return itemBinding.getRoot();
    }


}