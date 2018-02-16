package com.app.community.ui.dashboard.expandrecycleview.draweradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.app.community.R;
import com.app.community.network.response.dashboard.rightdrawer.Merchant;
import com.app.community.network.response.dashboard.rightdrawer.ProductSubCategory;
import com.app.community.ui.dashboard.expandrecycleview.ExpandableRecyclerAdapter;
import com.app.community.ui.dashboard.expandrecycleview.IngredientViewHolder;

import java.util.List;

public class DrawerAdapterRight extends ExpandableRecyclerAdapter<ProductSubCategory, Merchant, RecipeViewHolder, IngredientViewHolder> {

    private static final int PARENT_LEVEL = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_VEGETARIAN = 2;
    private static final int CHILD_NORMAL = 3;

    private LayoutInflater mInflater;
    private List<ProductSubCategory> mSubCatList;
    private ProductSubHolderListener listener;
    public interface ProductSubHolderListener{
        void onSubItemClicked(int parentPosition, int childPosition, Merchant merchant);
    }
    public DrawerAdapterRight(Context context, List<ProductSubCategory> subCatList, ProductSubHolderListener listener) {
        super(subCatList);
        mSubCatList = subCatList;
        this.listener=listener;
        mInflater = LayoutInflater.from(context);
    }

    @UiThread
    @NonNull
    @Override
    public RecipeViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                recipeView = mInflater.inflate(R.layout.list_item_products, parentViewGroup, false);
                break;
            case PARENT_LEVEL:
                recipeView = mInflater.inflate(R.layout.list_item_products_level, parentViewGroup, false);
                break;
        }
        return new RecipeViewHolder(recipeView);
    }

    @UiThread
    @NonNull
    @Override
    public IngredientViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                ingredientView = mInflater.inflate(R.layout.list_item_subproduct, childViewGroup, false);
                break;
                //Dont Remove It
            /*case CHILD_VEGETARIAN:
                ingredientView = mInflater.inflate(R.layout.list_item_subproduct, childViewGroup, false);
                break;*/
            //end
        }
        return new IngredientViewHolder(ingredientView);
    }

    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int parentPosition, @NonNull ProductSubCategory recipe) {
        recipeViewHolder.bind(recipe);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, final int parentPosition, final int childPosition, @NonNull Merchant merchant) {
        ingredientViewHolder.mIngredientTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSubItemClicked(parentPosition,childPosition,merchant);
            }
        });
        ingredientViewHolder.bind(merchant);
    }

    @Override
    public int getParentViewType(int parentPosition) {
        if (mSubCatList.get(parentPosition).isCategory()) {
            return PARENT_LEVEL;
        } else {
            return PARENT_NORMAL;
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        Merchant merchant = mSubCatList.get(parentPosition).getMerchant(childPosition);
        //Dont Remove It
       /* if (ingredient.isVegetarian()) {
            return CHILD_VEGETARIAN;
        } else {*/
       //end
            return CHILD_NORMAL;
        /*}*/
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_LEVEL || viewType == PARENT_NORMAL;
    }

}
