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
import com.app.community.network.request.dashboard.MerchantCouponRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.NotificationRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.ProfilePic;
import com.app.community.network.request.dashboard.ProfileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.MyOrderData;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.coupon.MerchantCouponResponseData;
import com.app.community.network.response.coupon.ViewAllCouponResponseData;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.cart.ProductFullInformationData;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.home.WelcomeHomeData;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.network.response.dashboard.notification.NotificationResponseData;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;

import io.reactivex.Completable;
import io.reactivex.Observable;




public interface Repository {
    Observable<LoginResponse> getLoginDetail(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);


    Observable<ProductFullInformationData> getProductDetail(ProductRequest productRequest);

    Observable<MerchantResponseData> getMerchantDetail(MerchantRequest merchantRequest);

    Observable<SearchResponseData> getMerchantListBySearch(MerchantSearchRequest search);

    Observable<ReviewResponseData> getMerchantReviews(MerchantRequest merchantRequest);

    Observable<CategoryResponse> getCategory(CategoryRequest productRequest);

    Observable<BaseResponse> addToCart(CartRequest cartRequest);

    Observable<BaseResponse> deleteCart(DeleteCartRequest request);

    Observable<BaseResponse> deleteAllCart();

    Observable<ProductDetailsData> viewCart();

    Observable<WelcomeHomeData> getWelcomeHomePage();

    Observable<ProductTypeData> getCategorySubCategoryRightDrawer();

    Observable<BaseResponse> setDeviceToken(DeviceTokenRequest token);

    Observable<BaseResponse> logout();

    Observable<BaseResponse> updateLocation(UpdateLocation updateLocation);

    Observable<BaseResponse> addForCartList(CartListRequest request);

    Observable<BaseResponse> checkout(CheckoutRequest checkoutRequest);

    Observable<BaseResponse> updateProfile(ProfileRequest profileRequest);

    Observable<BaseResponse> deleteFromCart(DeleteCartRequest request);

    Observable<MyOrderData> getMyOrder();

    Observable<BaseResponse> updateProfilePic(ProfilePic profilePicRequest);

    Observable<BaseResponse> submitFeedBack(Feedback feedback);

    Observable<MerchantCouponResponseData> viewMerchantCoupon(MerchantCouponRequest request);

    Observable<ViewAllCouponResponseData> viewAllCoupon();

    Observable<NotificationResponseData> getNotificationListPerUser();

    Observable<BaseResponse> clearAllNotification();

    Observable<BaseResponse> deleteNotification(NotificationRequest request);

    Observable<BaseResponse> readNotification(NotificationRequest request);

    Observable<BaseResponse> checkCoupon(CheckCouponRequest request);
}
