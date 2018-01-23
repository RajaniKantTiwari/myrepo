package com.app.community.ui.dashboard.expandrecycleview;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.network.response.dashboard.drawerresponse.Ingredient;

public class IngredientViewHolder extends ChildViewHolder {

    public TextView mIngredientTextView;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        mIngredientTextView = (TextView) itemView.findViewById(R.id.tvSubProductName);
    }

    public void bind(@NonNull Ingredient ingredient) {
        mIngredientTextView.setText(ingredient.getName());
    }
}
