package com.app.community.network;



import com.app.community.network.request.DeviceTokenRequest;
import com.app.community.network.request.Feedback;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.UpdateLocation;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.cart.CartListRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.request.cart.CheckoutRequest;
import com.app.community.network.request.cart.DeleteCartRequest;
import com.app.community.network.request.dashboard.CheckCouponRequest;
import com.app.community.network.request.dashboard.IssueRequest;
import com.app.community.network.request.dashboard.MerchantCouponRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.OrderDetailsRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.ProfilePic;
import com.app.community.network.request.dashboard.ProfileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.MyOrderData;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.cart.ProductFullInformationData;
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
    Observable<ProductFullInformationData> getProductDetail(@Body ProductRequest request);

    @POST("cart/addcart")
    Observable<BaseResponse> addToCart(@Body CartRequest request);

    @GET("cart/deleteallcart")
    Observable<BaseResponse> deleteAllCart();

    @POST("cart/deletecart")
    Observable<BaseResponse> deleteCart(@Body DeleteCartRequest request);


    @GET("cart/viewcart")
    Observable<ProductDetailsData> viewCart();

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

    @GET("register/logout")
    Observable<BaseResponse> logout();

    @POST("register/updatelocation")
    Observable<BaseResponse> updateLocation(@Body UpdateLocation updateLocation);

    @POST("cart/addalltocart")
    Observable<BaseResponse> addForCartList(@Body CartListRequest request);

    @POST("cart/checkoutcart")
    Observable<BaseResponse> checkout(@Body CheckoutRequest request);

    @POST("register/updateprofile")
    Observable<BaseResponse> updateProfile(@Body ProfileRequest request);

    @GET("cart/myorder")
    Observable<MyOrderData> getMyOrder();

    @POST("register/profilepic")
    Observable<BaseResponse> profilePic(@Body ProfilePic request);

    @POST("customer/getfeedback")
    Observable<BaseResponse> submitFeedBack(@Body Feedback request);

    @POST("cartorder/orderdetails")
    Observable<BaseResponse> orderDetails(@Body OrderDetailsRequest request);

    @GET("custom/detailhelp")
    Observable<BaseResponse> detailHelp();

    @POST("custom/getissue")
    Observable<BaseResponse> orderDetails(@Body IssueRequest request);

    @POST("offer/checkcoupon")
    Observable<BaseResponse> checkCoupon(@Body CheckCouponRequest request);

    @GET("offer/viewallcoupon")
    Observable<BaseResponse> viewAllCoupon();

    @POST("offer/getcoupondescription")
    Observable<BaseResponse> getCouponDescription(@Body CheckCouponRequest request);

    @POST("offer/viewmerchantcoupon")
    Observable<MerchantResponseData> viewMerchantCoupon(@Body MerchantCouponRequest request);

    @POST("offer/getmerchantcoupondescription")
    Observable<BaseResponse> merchantCouponDescription(@Body MerchantCouponRequest request);




}
