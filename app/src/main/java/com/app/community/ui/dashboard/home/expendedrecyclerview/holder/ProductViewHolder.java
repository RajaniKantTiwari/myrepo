package com.app.community.ui.dashboard.home.expendedrecyclerview.holder;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;

import com.app.community.R;
import com.app.community.databinding.ListItemProductsBinding;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.ExpandableGroup;
import com.app.community.ui.dashboard.home.expendedrecyclerview.model.Genre;
import com.app.community.utils.CommonUtils;

import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;
import static com.app.community.utils.GeneralConstant.ANIMATION_FROM_DEGREES;
import static com.app.community.utils.GeneralConstant.ANIMATION_TO_DEGREE;

public class ProductViewHolder extends GroupViewHolder {

  private ListItemProductsBinding mBinding;
  public ProductViewHolder(ListItemProductsBinding mBinding, AppCompatActivity activity) {
    super(mBinding,activity);
    this.mBinding=mBinding;
  }

  public void setGenreTitle(ExpandableGroup genre) {
    if (genre instanceof Genre) {
      List list = genre.getItems();
      if(list==null||list.isEmpty()){
        mBinding.ivArrow.setVisibility(View.INVISIBLE);
        mBinding.layoutCategory.setBackgroundColor(CommonUtils.getColor(mBinding.getRoot().getContext(), R.color.white));
        mBinding.tvProductName.setTextColor(CommonUtils.getColor(mBinding.getRoot().getContext(), R.color.dark_black_color));
      }else {
        mBinding.layoutCategory.setBackgroundColor(CommonUtils.getColor(mBinding.getRoot().getContext(), R.color.dark_black_color));
        mBinding.ivArrow.setVisibility(View.VISIBLE);
        mBinding.tvProductName.setTextColor(CommonUtils.getColor(mBinding.getRoot().getContext(), R.color.white));
      }
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
