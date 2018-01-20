package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.SearchRowItemBinding;
import com.app.community.network.response.dashboard.feed.MerchantResponse;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private final LayoutInflater mInflater;
    private final ArrayList<MerchantResponse> merchantList;
    private SearchRowItemBinding mBinding;
    private SearchListener searchListener;

    public interface SearchListener {

        void itemClicked(int adapterPosition);
    }

    public SearchAdapter(AppCompatActivity activity, ArrayList<MerchantResponse> merchantList, SearchListener searchListener) {
        mInflater = LayoutInflater.from(activity);
        this.searchListener = searchListener;
        this.merchantList = merchantList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.search_row_item, parent, false);
        return new SearchViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        if (CommonUtils.isNotNull(merchantList) && merchantList.size() > position) {
            holder.setData(merchantList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(merchantList) ? merchantList.size() : 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SearchRowItemBinding itemView;

        public SearchViewHolder(SearchRowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            itemView.tvSearchItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (CommonUtils.isNotNull(searchListener)) {
                searchListener.itemClicked(getAdapterPosition());
            }
        }
        public void setData(MerchantResponse merchantResponse) {
            itemView.setSearchData(merchantResponse);
        }
    }
}
