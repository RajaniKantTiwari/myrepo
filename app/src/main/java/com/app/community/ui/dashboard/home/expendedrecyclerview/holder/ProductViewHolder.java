package com.app.community.ui.dashboard.home.expendedrecyclerview.holder;

import android.view.animation.RotateAnimation;

import com.app.community.databinding.ListItemProductsBinding;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.ExpandableGroup;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.Genre;

import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static com.app.community.utils.GeneralConstant.ANIMATION_FROM_DEGREES;
import static com.app.community.utils.GeneralConstant.ANIMATION_TO_DEGREE;

public class ProductViewHolder extends GroupViewHolder {

  private ListItemProductsBinding mBinding;
  public ProductViewHolder(ListItemProductsBinding mBinding) {
    super(mBinding.getRoot());
    this.mBinding=mBinding;
  }

  public void setGenreTitle(ExpandableGroup genre) {
    if (genre instanceof Genre) {
      mBinding.tvProductName.setText(genre.getTitle());
    }
  }

  @Override
  public void expand() {
    animateExpand();
  }

  @Override
  public void collapse() {
    animateCollapse();
  }

  private void animateExpand() {
    RotateAnimation rotate =
        new RotateAnimation(ANIMATION_FROM_DEGREES, ANIMATION_TO_DEGREE, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    mBinding.ivArrow.setAnimation(rotate);
  }

  private void animateCollapse() {
    RotateAnimation rotate =
        new RotateAnimation(ANIMATION_TO_DEGREE, ANIMATION_FROM_DEGREES, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
    rotate.setDuration(300);
    rotate.setFillAfter(true);
    mBinding.ivArrow.setAnimation(rotate);
  }
}
