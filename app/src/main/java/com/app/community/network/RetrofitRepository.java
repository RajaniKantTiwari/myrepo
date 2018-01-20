package com.app.community.network;

import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.ProductSearchRequest;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.SearchResponseData;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.feed.MerchantResponseData;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by atul on 24/01/17.
 * Interface having all the api functions declaration.
 */

public class RetrofitRepository implements Repository {
    private ApiService apiService;

    public RetrofitRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<LoginResponse> getLoginDetail(LoginRequest request) {
        return apiService.getLoginDetail(request);
    }

    @Override
    public Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest request) {
        return apiService.verifyMobileNumber(request);
    }

    @Override
    public Observable<MerchantResponseData> getMerchantList() {
        return apiService.getMerchantList();
    }

    @Override
    public Observable<ProductDetailsData> getProductDetail(ProductRequest productRequest) {
        return apiService.getProductDetail(productRequest);
    }

    @Override
    public Observable<MerchantResponseData> getMerchantDetail(MerchantRequest merchantRequest) {
        return apiService.getMerchantDetails(merchantRequest);
    }

    @Override
    public Observable<SearchResponseData> searchProductList(ProductSearchRequest search) {
        return apiService.getProductDetailsBySearch(search);
    }
}
