package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.LayoutNewsRowBinding;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.ui.dashboard.notification.NotificationLiveAdapter;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.LatestNewsHolder> {
    private final LayoutInflater mInflater;
    private final NewsListener newsListener;
    private final AppCompatActivity activity;
    private ArrayList<News> newsList;
    private int getTotalHeight;

    public int getGetTotalHeight() {
        return getTotalHeight;
    }

    public interface NewsListener{

        void newsItemClick(int position);
    }
    public NewsAdapter(AppCompatActivity activity, ArrayList<News> newsList, NewsListener newsListener){
        mInflater=LayoutInflater.from(activity);
        this.newsListener=newsListener;
        this.newsList=newsList;
        this.activity=activity;
    }
    @Override
    public LatestNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutNewsRowBinding mBinding=DataBindingUtil.inflate(mInflater, R.layout.layout_news_row, parent, false);
        return new LatestNewsHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LatestNewsHolder holder, int position) {
        if(CommonUtils.isNotNull(newsList)&&newsList.size()>position){
            holder.setData(newsList.get(position));
        }
        getItemWidthHeight(holder);
    }
    private void getItemWidthHeight(LatestNewsHolder holder) {
        holder.mBinding.getRoot().post(new Runnable()
        {
            @Override
            public void run()
            {

                int cellWidth = holder.mBinding.getRoot().getWidth();// this will give you cell width dynamically
                int cellHeight = holder.mBinding.getRoot().getHeight();// this will give you cell height dynamically
                getTotalHeight=getTotalHeight+cellHeight;
                Log.e("NewsWidthHeight","Width  "+cellWidth+"  Height "+cellHeight);

            }
        });
    }
    @Override
    public int getItemCount() {
        return 10;
    }
    class LatestNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final LayoutNewsRowBinding mBinding;

        public LatestNewsHolder(LayoutNewsRowBinding binding) {
           super(binding.getRoot());
           mBinding=binding;
           binding.layoutNews.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(CommonUtils.isNotNull(newsListener)){
                newsListener.newsItemClick(getAdapterPosition());
            }
        }

        public void setData(News news) {
            mBinding.setNews(news);
        }
    }
}
