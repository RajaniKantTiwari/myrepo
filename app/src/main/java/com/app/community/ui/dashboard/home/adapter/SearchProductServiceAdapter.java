package com.app.community.ui.dashboard.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ProductServiceRowItemBinding;
import com.app.community.utils.CommonUtils;

/**
 * Created by ashok on 25/12/17.
 */

public class SearchProductServiceAdapter extends RecyclerView.Adapter<SearchProductServiceAdapter.ProductServiceHolder> {
    private final LayoutInflater mInflater;
    //1 for product 2 for service
    private final int type;
    private ProductServiceRowItemBinding mBinding;
    private SearchProductServiceListener listener;
    public interface SearchProductServiceListener {

        void onProductServiceItemClick(int position, int adapterPosition);
    }

    public SearchProductServiceAdapter(AppCompatActivity activity, SearchProductServiceListener listener, int type){
        mInflater=LayoutInflater.from(activity);
        this.listener=listener;
        this.type=type;
    }
    @Override
    public ProductServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding=DataBindingUtil.inflate(mInflater, R.layout.product_service_row_item, parent, false);
        return new ProductServiceHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(ProductServiceHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
    class ProductServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public ProductServiceHolder(ProductServiceRowItemBinding itemView) {
           super(itemView.getRoot());
           itemView.tvSearchItem.setOnClickListener(this);
       }

        @Override
        public void onClick(View view) {
         if(CommonUtils.isNotNull(listener)){
             listener.onProductServiceItemClick(getAdapterPosition(),type);
         }
        }
    }
}
