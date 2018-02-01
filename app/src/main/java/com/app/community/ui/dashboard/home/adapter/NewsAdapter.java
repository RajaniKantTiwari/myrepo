package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.LayoutNewsRowBinding;
import com.app.community.network.response.dashboard.home.News;
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
        /*mBinding.getRoot().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        height = mBinding.getRoot().getMeasuredHeight();*/
        return new LatestNewsHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LatestNewsHolder holder, int position) {
        if(CommonUtils.isNotNull(newsList)&&newsList.size()>position){
            holder.setData(newsList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(newsList)?newsList.size():0;
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
