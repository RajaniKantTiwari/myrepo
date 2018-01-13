package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.OrderRowItemBinding;
import com.app.community.databinding.SearchRowItemBinding;
import com.app.community.utils.CommonUtils;

/**
 * Created by ashok on 25/12/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CartHolder> {
    private final LayoutInflater mInflater;
    private SearchRowItemBinding mBinding;
    private SearchListener searchListener;
    public interface SearchListener{

        void itemClicked(int adapterPosition);
    }

    public SearchAdapter(AppCompatActivity activity,SearchListener searchListener){
        mInflater=LayoutInflater.from(activity);
        this.searchListener=searchListener;
    }
    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.search_row_item, parent, false);
        return new CartHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class CartHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public CartHolder(SearchRowItemBinding itemView) {
           super(itemView.getRoot());
           itemView.tvSearchItem.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
          if(CommonUtils.isNotNull(searchListener)) {
              searchListener.itemClicked(getAdapterPosition());
          }
        }
    }
}
