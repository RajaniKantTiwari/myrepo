package com.app.community.network;



import com.app.community.network.request.DeviceTokenRequest;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.network.response.dashboard.home.WelcomeHomeData;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;



public interface ApiService {
    @POST("register/user")
    Observable<LoginResponse> getLoginDetail(@Body LoginRequest request);

    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @POST("product/getproductdetails")
    Observable<ProductDetailsData> getProductDetail(@Body ProductRequest request);

    @POST("cart/addcart")
    Observable<BaseResponse> addToCart(@Body CartRequest request);

    @GET("cart/deletecart")
    Observable<BaseResponse> deleteCart();

    @POST("cart/viewcart")
    Observable<ProductDetailsData> viewCart(@Body CartRequest request);

    @POST("merchant/getmerchantdetails")
    Observable<MerchantResponseData> getMerchantDetails(@Body MerchantRequest request);

    @POST("common/searchbytag")
    Observable<SearchResponseData> getMerchantListBySearch(@Body MerchantSearchRequest request);

    @POST("merchant/getmerchantreviews")
    Observable<ReviewResponseData> getMerchantReviews(@Body MerchantRequest merchantRequest);

    @POST("product/getallproducts")
    Observable<CategoryResponse> getProducts(@Body CategoryRequest request);

    @GET("home/homepage")
    Observable<WelcomeHomeData> getWelcomeHomePage();

    @GET("common/menulist")
    Observable<ProductTypeData> getCategorySubCategoryRightDrawer();

    @POST("push/gettoken")
    Observable<BaseResponse> setDeviceToken(@Body DeviceTokenRequest token);
}
