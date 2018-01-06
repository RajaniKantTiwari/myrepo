package com.app.community.ui.dashboard.home.expendedrecyclerview.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.app.community.R;
import com.app.community.databinding.ListItemProductsBinding;
import com.app.community.databinding.ListItemSubproductBinding;
import com.app.community.ui.dashboard.home.expendedrecyclerview.holder.ProductViewHolder;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.Artist;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.ExpandableGroup;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.Genre;

import java.util.List;

public class DrawerViewAdapter extends ExpandableRecyclerViewAdapter<ProductViewHolder, ProductSublistViewHolder> {

  private final AppCompatActivity activity;
  private final LayoutInflater mInflator;
  private ProductSubHolder listener;
  public interface ProductSubHolder{
      void onSubItemClicked();
  }
  public DrawerViewAdapter(List<? extends ExpandableGroup> groups, AppCompatActivity activity) {
    super(groups);
    this.activity=activity;
    mInflator=LayoutInflater.from(activity);
  }

  @Override
  public ProductViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
    ListItemProductsBinding mBinding =DataBindingUtil.inflate(mInflator,R.layout.list_item_products, parent, false);
    return new ProductViewHolder(mBinding,activity);
  }

  @Override
  public ProductSublistViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
    ListItemSubproductBinding mBinding =DataBindingUtil.inflate(mInflator,R.layout.list_item_subproduct, parent, false);
    return new ProductSublistViewHolder(mBinding);
  }

  @Override
  public void onBindChildViewHolder(ProductSublistViewHolder holder, int flatPosition,
                                    ExpandableGroup group, int childIndex) {
    final Artist artist = ((Genre) group).getItems().get(childIndex);
    holder.setArtistName(artist.getName());
  }

  @Override
  public void onBindGroupViewHolder(ProductViewHolder holder, int flatPosition,
                                    ExpandableGroup group) {

    holder.setGenreTitle(group);
  }
}
