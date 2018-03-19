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
import com.app.community.network.request.dashboard.NotificationRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
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
import com.app.community.network.response.dashboard.home.AddressData;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ProfilePicResponse;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.network.response.dashboard.home.WelcomeHomeData;
import com.app.community.network.response.dashboard.notification.NotificationResponseData;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;
import com.app.community.network.response.dashboard.user.UserProfileData;

import io.reactivex.Observable;
import retrofit2.Retrofit;



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
    public Observable<ProductFullInformationData> getProductDetail(ProductRequest productRequest) {
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
    public Observable<BaseResponse> deleteCart(DeleteCartRequest request) {
        return apiService.deleteCart(request);
    }

    @Override
    public Observable<BaseResponse> deleteAllCart() {
        return apiService.deleteAllCart();
    }

    @Override
    public Observable<ProductDetailsData> viewCart() {
        return apiService.viewCart();
    }

    @Override
    public Observable<WelcomeHomeData> getWelcomeHomePage() {
        return apiService.getWelcomeHomePage();
    }

    @Override
    public Observable<ProductTypeData> getCategorySubCategoryRightDrawer() {
        return apiService.getCategorySubCategoryRightDrawer();
    }

    @Override
    public Observable<BaseResponse> setDeviceToken(DeviceTokenRequest token) {
        return apiService.setDeviceToken(token);
    }

    @Override
    public Observable<BaseResponse> logout() {
        return apiService.logout();
    }

    @Override
    public Observable<BaseResponse> updateLocation(UpdateLocation updateLocation) {
        return apiService.updateLocation(updateLocation);
    }

    @Override
    public Observable<BaseResponse> addForCartList(CartListRequest request) {
        return apiService.addForCartList(request);
    }

    @Override
    public Observable<BaseResponse> checkout(CheckoutRequest checkoutRequest) {
        return apiService.checkout(checkoutRequest);
    }

    @Override
    public Observable<BaseResponse> updateProfile(ProfileRequest profileRequest) {
        return apiService.updateProfile(profileRequest);
    }

    @Override
    public Observable<BaseResponse> deleteFromCart(DeleteCartRequest request) {
        return apiService.deleteCart(request);
    }

    @Override
    public Observable<MyOrderData> getMyOrder() {
        return apiService.getMyOrder();
    }

    @Override
    public Observable<BaseResponse> updateProfilePic(ProfilePic profilePicRequest) {
        return apiService.profilePic(profilePicRequest);
    }

    @Override
    public Observable<BaseResponse> submitFeedBack(Feedback feedback) {
        return apiService.submitFeedBack(feedback);
    }

    @Override
    public Observable<MerchantCouponResponseData> viewMerchantCoupon(MerchantCouponRequest request) {
        return apiService.viewMerchantCoupon(request);
    }

    @Override
    public Observable<ViewAllCouponResponseData> viewAllCoupon() {
        return apiService.viewAllCoupon();
    }

    @Override
    public Observable<NotificationResponseData> getNotificationListPerUser() {
        return apiService.getNotificationListPerUser();
    }

    @Override
    public Observable<BaseResponse> clearAllNotification() {
        return apiService.clearAllNotification();
    }

    @Override
    public Observable<BaseResponse> deleteNotification(NotificationRequest request) {
        return apiService.deleteNotification(request);
    }

    @Override
    public Observable<BaseResponse> readNotification(NotificationRequest request) {
        return apiService.readNotification(request);
    }

    @Override
    public Observable<BaseResponse> checkCoupon(CheckCouponRequest request) {
        return apiService.checkCoupon(request);
    }

    @Override
    public Observable<UserProfileData> viewUserProfile() {
        return apiService.viewUserProfile();
    }

    @Override
    public Observable<AddressData> viewAllAddress() {
        return apiService.viewAllAddress();
    }

    @Override
    public Observable<ProfilePicResponse> getProfilePic() {
        return apiService.getProfilePic();
    }
}
