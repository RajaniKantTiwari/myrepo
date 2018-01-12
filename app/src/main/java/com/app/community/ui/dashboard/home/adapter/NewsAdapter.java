package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.LayoutNewsRowBinding;
import com.app.community.utils.CommonUtils;

/**
 * Created by ashok on 25/12/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.LatestNewsHolder> {
    private final LayoutInflater mInflater;
    private final NewsListener newsListener;
    private LayoutNewsRowBinding mBinding;
    public interface NewsListener{

        void itemClick(int adapterPosition);
    }
    public NewsAdapter(AppCompatActivity activity,NewsListener newsListener){
        mInflater=LayoutInflater.from(activity);
        this.newsListener=newsListener;
    }
    @Override
    public LatestNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.layout_news_row, parent, false);
        return new LatestNewsHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(LatestNewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class LatestNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public LatestNewsHolder(LayoutNewsRowBinding binding) {
           super(binding.getRoot());
           mBinding.layoutNews.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
            if(CommonUtils.isNotNull(newsListener)){
                newsListener.itemClick(getAdapterPosition());
            }
        }
    }
}
