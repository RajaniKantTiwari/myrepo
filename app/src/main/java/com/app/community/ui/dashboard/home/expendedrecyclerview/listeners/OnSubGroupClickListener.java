package com.app.community.ui.dashboard.home.expendedrecyclerview.listeners;

public interface OnSubGroupClickListener {

  /**
   * @param groupPos the flat position (raw index within the list of visible items in the
   * RecyclerView of a GroupViewHolder)
   * @return false if click expanded group, true if click collapsed group,subGroupPos gives value of cliking subGeoup
   */
  void onSubGroupClick(int groupPos,int subGroupPos);
}