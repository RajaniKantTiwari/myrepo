package com.app.community.network;

import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.SearchResponseData;

import io.reactivex.Observable;


/**
 * Created by atul on 24/01/17.
 * Interface having all the api functions declaration.
 */

public interface Repository {
    Observable<LoginResponse> getLoginDetail(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);


    Observable<ProductDetailsData> getProductDetail(ProductRequest productRequest);

    Observable<MerchantResponseData> getMerchantDetail(MerchantRequest merchantRequest);

    Observable<SearchResponseData> getMerchantListBySearch(MerchantSearchRequest search);

    Observable<ReviewResponseData> getMerchantReviews(MerchantRequest merchantRequest);
}
