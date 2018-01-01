package com.app.community.ui.dashboard.home.expendedrecyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.community.ui.dashboard.home.expendedrecyclerview.listeners.OnSubGroupClickListener;


/**
 * ViewHolder for
 */
public class ChildViewHolder extends RecyclerView.ViewHolder implements OnSubGroupClickListener {

  public ChildViewHolder(View itemView) {
    super(itemView);
  }

  @Override
  public boolean onSubGroupClick(int groupPos, int subGroupPos) {
    return false;
  }
}
