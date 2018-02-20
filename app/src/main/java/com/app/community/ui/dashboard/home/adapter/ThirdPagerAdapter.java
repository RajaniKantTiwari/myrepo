package com.app.community.ui.dashboard.home.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.app.community.R;
import com.app.community.databinding.ItemNewsWebRowBinding;
import com.app.community.ui.dashboard.CustomWebView;

public class ThirdPagerAdapter extends PagerAdapter {

    private final LayoutInflater mInflator;
    private Context mContext;
    private ItemNewsWebRowBinding itemBinding;

    public ThirdPagerAdapter(Context mContext) {
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
        itemBinding = DataBindingUtil.inflate(mInflator, R.layout.item_news_web_row, container, false);
        itemBinding.webView.getSettings().setJavaScriptEnabled(true);
        //itemBinding.webView.getSettings().setUseWideViewPort(true);
        //itemBinding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        //itemBinding.webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        itemBinding.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        itemBinding.webView.setWebViewClient(new CustomWebView(mContext,itemBinding.progressBar));
        ObjectAnimator anim = ObjectAnimator.ofInt(itemBinding.webView, "scrollY", itemBinding.webView.getScrollY(), 0);
        anim.setDuration(500).start();
        //itemBinding.webView .loadUrl("http://www.google.com");
        container.addView(itemBinding.getRoot());
        return itemBinding.getRoot();
    }


    public void setWebUrl(String url) {
        itemBinding.webView .loadUrl(url);
    }
}