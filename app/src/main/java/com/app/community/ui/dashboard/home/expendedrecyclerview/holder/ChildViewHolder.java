package com.app.community.ui.dashboard.home.expendedrecyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.community.ui.dashboard.home.expendedrecyclerview.adapter.ExpandableRecyclerViewAdapter;
import com.app.community.ui.dashboard.home.expendedrecyclerview.listeners.OnSubGroupClickListener;


/**
 * ViewHolder for
 */
public class ChildViewHolder extends RecyclerView.ViewHolder {

  private OnSubGroupClickListener listener;

  public ChildViewHolder(View itemView) {
    super(itemView);
  }
  public void onSubGroupClick(OnSubGroupClickListener listener) {
        this.listener=listener;
  }
}
