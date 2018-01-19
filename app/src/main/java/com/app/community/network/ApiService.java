package com.app.community.network;



import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.ProductSearchRequest;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.SearchResponseData;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.feed.MerchantDetailsData;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by arvind on 01/11/17.
 */

public interface ApiService {
    @POST("register/user")
    Observable<LoginResponse> getLoginDetail(@Body LoginRequest request);

    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @GET("merchant/showmerchant")
    Observable<ProductResponseData> getMerchantList();


    @POST("product/getproductdetails")
    Observable<ProductDetailsData> getProductDetail(@Body ProductRequest request);

    @POST("cart/addcart")
    Observable<ProductDetailsData> addToCart(@Body CartRequest request);

    @POST("cart/deletecart")
    Observable<ProductDetailsData> deleteCart();

    @POST("cart/viewcart")
    Observable<ProductDetailsData> viewCart(@Body CartRequest request);

    @POST("merchant/getmerchantdetails")
    Observable<MerchantDetailsData> getMerchantDetails(@Body MerchantRequest request);


    @POST("common/searchbytag")
    Observable<SearchResponseData> getProductDetailsBySearch(@Body ProductSearchRequest request);



}
