package com.app.community.ui.dashboard.home.expendedrecyclerview.holder;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.app.community.R;
import com.app.community.databinding.ListItemProductsBinding;
import com.app.community.ui.dashboard.home.expendedrecyclerview.listeners.OnGroupClickListener;
import com.app.community.utils.CommonUtils;


/**
 * ViewHolder for the
 *
 * The current implementation does now allow for sub {@link View} of the parent view to trigger
 * a collapse / expand. *Only* click events on the parent {@link View} will trigger a collapse or
 * expand
 */
public abstract class GroupViewHolder extends RecyclerView.ViewHolder {

  private OnGroupClickListener listener;

  public GroupViewHolder(ListItemProductsBinding mBinding, AppCompatActivity activity) {
    super(mBinding.getRoot());
    mBinding.layoutView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if (listener != null) {
          mBinding.layoutView.setBackgroundColor(CommonUtils.getColor(activity, R.color.tab_selected));
          listener.onGroupClick(getAdapterPosition());
        }
      }
    });
  }


  public void setOnGroupClickListener(OnGroupClickListener listener) {
    this.listener = listener;
  }

  public void expand() {}

  public void collapse() {}
}
