package com.app.community.utils;

import com.app.community.network.response.dashboard.rightdrawer.ProductSubCategory;
import com.app.community.network.response.dashboard.rightdrawer.ProductType;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/01/18.
 */

public class DashBoardHelper {
    public static ArrayList<ProductSubCategory> setRightDrawerData(ProductTypeData response) {
        ArrayList<ProductSubCategory> subCategory=new ArrayList<>();
        if (CommonUtils.isNotNull(response)) {
            ArrayList<ProductType> listResponse = response.getData();
            if (CommonUtils.isNotNull(listResponse)) {
                for (int i = 0; i < listResponse.size(); i++) {
                    ProductType productType = listResponse.get(i);
                    if (CommonUtils.isNotNull(productType)) {
                        ProductSubCategory productSubCategory=new ProductSubCategory();
                        productSubCategory.setId(productType.getId());
                        productSubCategory.setSubcat(productType.getCategory());
                        productSubCategory.setDisplayorder(productType.getDisplayorder());
                        productSubCategory.setCategory(true);
                        subCategory.add(productSubCategory);
                        ArrayList<ProductSubCategory> subCat = productType.getSubcategory();
                        if(CommonUtils.isNotNull(subCat)&&subCat.size()>0){
                            subCategory.addAll(subCat);
                        }
                    }
                }
            }
        }
        return subCategory;
    }
}
