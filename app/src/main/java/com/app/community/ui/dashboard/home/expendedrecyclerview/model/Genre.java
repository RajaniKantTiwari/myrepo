package com.app.community.ui.dashboard.home.expendedrecyclerview.model;

import java.util.List;

public class Genre extends ExpandableGroup<Artist> {
  private String title;

  private int iconResId;

  public Genre(String title, List<Artist> items, int iconResId) {
    super(title, items);
    this.iconResId = iconResId;
  }

  public Genre(String title, List<Artist> items) {
    super(title, items);
    this.title=title+items.size();

  }


 /* public int getIconResId() {
    return iconResId;
  }*/


  /*@Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Genre)) return false;

    Genre genre = (Genre) o;

    return getIconResId() == genre.getIconResId();

  }

  @Override
  public int hashCode() {
    return getIconResId();
  }*/

  public String getTitle() {
      return title;
    }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Genre)) return false;

    Genre genre = (Genre) o;

    return getTitle() == genre.getTitle();

  }

  @Override
  public int hashCode() {
    return title.length();
  }
}

