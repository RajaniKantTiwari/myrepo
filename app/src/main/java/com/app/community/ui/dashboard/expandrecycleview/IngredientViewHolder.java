package com.app.community.ui.dashboard.expandrecycleview;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.network.response.dashboard.drawerresponse.Ingredient;
import com.app.community.network.response.dashboard.rightdrawer.Merchant;
import com.app.community.utils.CommonUtils;

public class IngredientViewHolder extends ChildViewHolder {

    public TextView mIngredientTextView;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        mIngredientTextView =itemView.findViewById(R.id.tvSubProductName);
    }

    public void bind(@NonNull Merchant merchant) {
        mIngredientTextView.setText(merchant.getStorename());
    }
}
