package com.app.community.network;

import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.SearchResponseData;

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
    public Observable<ProductDetailsData> getProductDetail(ProductRequest productRequest) {
        return apiService.getProductDetail(productRequest);
    }

    @Override
    public Observable<MerchantResponseData> getMerchantDetail(MerchantRequest merchantRequest) {
        return apiService.getMerchantDetails(merchantRequest);
    }

    @Override
    public Observable<SearchResponseData> getMerchantListBySearch(MerchantSearchRequest search) {
        return apiService.getMerchantListBySearch(search);
    }

    @Override
    public Observable<ReviewResponseData> getMerchantReviews(MerchantRequest merchantRequest) {
        return apiService.getMerchantReviews(merchantRequest);
    }

    @Override
    public Observable<CategoryResponse> getCategory(CategoryRequest productRequest) {
        return apiService.getProducts(productRequest);
    }

    @Override
    public Observable<BaseResponse> addToCart(CartRequest cartRequest) {
        return apiService.addToCart(cartRequest);
    }

    @Override
    public Observable<BaseResponse> deleteCart() {
        return apiService.deleteCart();
    }

    @Override
    public Observable<ProductDetailsData> viewCart(CartRequest cartRequest) {
        return apiService.viewCart(cartRequest);
    }
}
