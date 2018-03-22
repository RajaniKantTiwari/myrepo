package com.app.community.utils;

import com.app.community.network.response.dashboard.rightdrawer.Merchant;
import com.app.community.network.response.dashboard.rightdrawer.ProductSubCategory;
import com.app.community.network.response.dashboard.rightdrawer.ProductType;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;

import java.util.ArrayList;

/**
 * Created by rajnikant on 31/01/18.
 */

public class DashBoardHelper {
    public static ArrayList<ProductSubCategory> setRightDrawerData(ProductTypeData response) {
        ArrayList<ProductSubCategory> subCategory = new ArrayList<>();
        if (CommonUtils.isNotNull(response)) {
            ArrayList<ProductType> listResponse = response.getData();
            if (CommonUtils.isNotNull(listResponse)) {
                for (int i = 0; i < listResponse.size(); i++) {
                    ProductType productType = listResponse.get(i);
                    //to add type of category such as product and service
                    if (CommonUtils.isNotNull(productType)) {

                        ProductSubCategory productSubCategory = new ProductSubCategory();
                        productSubCategory.setId(productType.getId());
                        productSubCategory.setSubcat(productType.getCategory());
                        productSubCategory.setDisplayorder(productType.getDisplayorder());
                        productSubCategory.setCategory(true);

                        subCategory.add(productSubCategory);
                        ArrayList<ProductSubCategory> subCat = productType.getSubcategory();
                        //to add type of product name and inside merchant
                        if (CommonUtils.isNotNull(subCat) && subCat.size() > 0) {
                            for (int j = 0; j < subCat.size(); j++) {
                                if (CommonUtils.isNotNull(subCat.get(j)) && CommonUtils.isNotNull(subCat.get(j).
                                        getMerchantname()) && subCat.get(j).getMerchantname().size() > 0) {
                                    ProductSubCategory productSubCat = subCat.get(j);
                                    ArrayList<Merchant> merchants = productSubCat.getMerchantname();
                                    for (int k = 0; k < merchants.size(); k++) {
                                        Merchant merchant = merchants.get(k);
                                        merchant.setCategoryType(productType.getCategory());
                                        merchants.set(k, merchant);
                                    }
                                    productSubCat.setMerchantname(merchants);
                                    subCategory.add(productSubCat);
                                }
                            }
                        }
                    }
                }
            }
        }
        return subCategory;
    }
}
