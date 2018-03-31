package com.app.community.ui.dashboard;

import android.app.Activity;

import com.app.community.network.DefaultApiObserver;
import com.app.community.network.Repository;
import com.app.community.network.request.DeviceTokenRequest;
import com.app.community.network.request.Feedback;
import com.app.community.network.request.cart.CartListRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.request.cart.CheckoutRequest;
import com.app.community.network.request.cart.DeleteCartRequest;
import com.app.community.network.request.dashboard.CheckCouponRequest;
import com.app.community.network.request.dashboard.MerchantCouponRequest;
import com.app.community.network.request.dashboard.MerchantOfferRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.NotificationRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.request.dashboard.ProfilePic;
import com.app.community.network.request.dashboard.ProfileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.coupon.MerchantCouponResponseData;
import com.app.community.network.response.coupon.ViewAllCouponResponseData;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.cart.ProductFullInformationData;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.home.AddressData;
import com.app.community.network.response.dashboard.home.MerchantCategoryData;
import com.app.community.network.response.dashboard.home.ProfilePicResponse;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.network.response.dashboard.home.WelcomeHomeData;
import com.app.community.network.response.dashboard.notification.NotificationResponseData;
import com.app.community.network.response.dashboard.offer.MerchantOfferData;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;
import com.app.community.network.response.dashboard.user.UserProfileData;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.base.Presenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.app.community.utils.AppConstants.ADDRESSES;
import static com.app.community.utils.AppConstants.PROFILEPIC;
import static com.app.community.utils.GeneralConstant.VIEW_CART;


public class DashboardPresenter implements Presenter<MvpView> {


    private MvpView mView;
    private Repository mRepository;


    @Override
    public void attachView(MvpView view) {
        mView = view;
    }

    @Inject
    public DashboardPresenter(Repository repository) {
        this.mRepository = repository;
    }

    public void getMerchantListBySearch(Activity activity, String search) {
        mView.showProgress();
        mRepository.getMerchantListBySearch(new MerchantSearchRequest(search)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<SearchResponseData>(activity) {
            @Override
            public void onResponse(SearchResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, 0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 0);
            }
        });
    }


    public void getCategory(Activity activity, CategoryRequest categoryRequest) {
        mView.showProgress();
        LogUtils.LOGD("", "Repos==" + mRepository);
        mRepository.getCategory(categoryRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<CategoryResponse>(activity) {
            @Override
            public void onResponse(CategoryResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 1);
            }
        });
    }

    public void addToCart(Activity activity, CartRequest cartRequest) {
        mView.showProgress();
        mRepository.addToCart(cartRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 1);
            }
        });
    }

    public void deleteAllFromCart(Activity activity) {
        mView.showProgress();
        mRepository.deleteAllCart().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.DELETE_CART);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, AppConstants.DELETE_CART);
            }
        });
    }

    public void viewCart(Activity activity) {
        mView.showProgress();
        mRepository.viewCart().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductDetailsData>(activity) {
            @Override
            public void onResponse(ProductDetailsData response) {
                mView.hideProgress();
                mView.onSuccess(response, VIEW_CART);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, VIEW_CART);
            }
        });
    }

    public void getWelcomeHomePage(Activity activity) {
        mView.showProgress();
        mRepository.getWelcomeHomePage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<WelcomeHomeData>(activity) {
            @Override
            public void onResponse(WelcomeHomeData response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 1);
            }
        });
    }

    public void getCategorySubCategoryRightDrawer(DashBoardActivity activity) {

        //mView.showProgress();
        mRepository.getCategorySubCategoryRightDrawer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductTypeData>(activity) {
            @Override
            public void onResponse(ProductTypeData response) {
                //mView.hideProgress();
                activity.onSuccess(response, AppConstants.RIGHT_DRAWER_RESPONSE);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                //mView.hideProgress();
                activity.onError(call, AppConstants.RIGHT_DRAWER_RESPONSE);
            }
        });
    }

    public void setDeviceToken(DashBoardActivity activity, DeviceTokenRequest token) {
        mRepository.setDeviceToken(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.onSuccess(response, AppConstants.DEVICE_TOKEN_RESPONSE);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.onError(call, AppConstants.DEVICE_TOKEN_RESPONSE);
            }
        });
    }

    public void getProductDetails(Activity activity, ProductRequest request) {
        mRepository.getProductDetail(request).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductFullInformationData>(activity) {
            @Override
            public void onResponse(ProductFullInformationData response) {
                mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 2);
            }
        });
    }

    public void logout(DashBoardActivity activity) {
        activity.showProgress();
        mRepository.logout().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.hideProgress();
                activity.onSuccess(response, AppConstants.LOGOUT);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.hideProgress();
                activity.onError(call, AppConstants.LOGOUT);
            }
        });
    }

    public void addForCartList(DashBoardActivity activity, CartListRequest request, MvpView mView) {
        if (mView == null) {
            activity.showProgress();
        } else {
            mView.showProgress();
        }

        mRepository.addForCartList(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {

                if (mView == null) {
                    activity.hideProgress();
                    activity.onSuccess(response, AppConstants.CARTADDED);
                } else {
                    mView.hideProgress();
                    mView.onSuccess(response, AppConstants.CARTADDED);
                }

            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {

                if (mView == null) {
                    activity.hideProgress();
                    activity.onError(call, AppConstants.CARTADDED);
                } else {
                    mView.hideProgress();
                    mView.onError(call, AppConstants.CARTADDED);
                }

            }
        });
    }

    public void checkout(Activity activity, CheckoutRequest checkoutRequest) {
        mView.showProgress();
        mRepository.checkout(checkoutRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 2);
            }
        });
    }

    public void deleteFromCart(Activity activity, DeleteCartRequest request, int pos) {
        //mView.showProgress();
        mRepository.deleteFromCart(request).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, pos);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, pos);
            }
        });
    }

    public void updateProfilePic(Activity activity, ProfilePic profilePicRequest) {
        mView.showProgress();
        mRepository.updateProfilePic(profilePicRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, GeneralConstant.PROFILE_PIC_RESPONSE);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, GeneralConstant.PROFILE_PIC_RESPONSE);
            }
        });
    }

    public void updateProfile(Activity activity, ProfileRequest profileRequest) {
        mView.showProgress();
        mRepository.updateProfile(profileRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 1);
            }
        });
    }

    public void submitFeedBack(Activity activity, Feedback feedback) {
        mView.showProgress();
        mRepository.submitFeedBack(feedback).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, GeneralConstant.FEEDBACK);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, GeneralConstant.FEEDBACK);
            }
        });
    }


    public void viewMerchantCoupon(DashBoardActivity activity, MerchantCouponRequest request) {
        mView.showProgress();
        mRepository.viewMerchantCoupon(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<MerchantCouponResponseData>(activity) {
            @Override
            public void onResponse(MerchantCouponResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, 1);
            }
        });
    }

    public void viewAllCoupon(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.viewAllCoupon().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ViewAllCouponResponseData>(activity) {
            @Override
            public void onResponse(ViewAllCouponResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.VIEW_COUPON);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(call, AppConstants.VIEW_COUPON);
            }
        });
    }

    public void getNotificationListPerUser(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getNotificationListPerUser().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<NotificationResponseData>(activity) {
                    @Override
                    public void onResponse(NotificationResponseData response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 1);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 1);
                    }
                });
    }

    public void clearAllNotification(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.clearAllNotification().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
                    @Override
                    public void onResponse(BaseResponse response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 2);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 2);
                    }
                });
    }

    public void deleteNotification(DashBoardActivity activity, NotificationRequest request) {
        mView.showProgress();
        mRepository.deleteNotification(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
                    @Override
                    public void onResponse(BaseResponse response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 3);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 3);
                    }
                });
    }

    public void readNotification(DashBoardActivity activity, NotificationRequest request) {
        mView.showProgress();
        mRepository.readNotification(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
                    @Override
                    public void onResponse(BaseResponse response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 4);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 4);
                    }
                });
    }

    public void checkCoupon(DashBoardActivity activity, CheckCouponRequest request) {
        mView.showProgress();
        mRepository.checkCoupon(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
                    @Override
                    public void onResponse(BaseResponse response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 2);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 2);
                    }
                });
    }

    public void viewUserProfile(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.viewUserProfile().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<UserProfileData>(activity) {
                    @Override
                    public void onResponse(UserProfileData response) {
                        mView.hideProgress();
                        mView.onSuccess(response, AppConstants.VIEW_PROFILE);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, AppConstants.VIEW_PROFILE);
                    }
                });
    }

    public void viewAllAddress(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.viewAllAddress().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<AddressData>(activity) {
                    @Override
                    public void onResponse(AddressData response) {
                        mView.hideProgress();
                        mView.onSuccess(response, ADDRESSES);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, ADDRESSES);
                    }
                });
    }

    public void getProfilePic(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getProfilePic().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<ProfilePicResponse>(activity) {
                    @Override
                    public void onResponse(ProfilePicResponse response) {
                        mView.hideProgress();
                        mView.onSuccess(response, PROFILEPIC);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, PROFILEPIC);
                    }
                });
    }

    public void deleteAddress(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.deleteAddress().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<AddressData>(activity) {
                    @Override
                    public void onResponse(AddressData response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 5);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 5);
                    }
                });
    }

    public void getMerchantCategory(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getMerchantCategory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<MerchantCategoryData>(activity) {
                    @Override
                    public void onResponse(MerchantCategoryData response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 2);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 2);
                    }
                });
    }

    public void getMerchantOffer(DashBoardActivity activity, MerchantOfferRequest request) {
        mView.showProgress();
        mRepository.getMerchantOffer(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<MerchantOfferData>(activity) {
                    @Override
                    public void onResponse(MerchantOfferData response) {
                        mView.hideProgress();
                        mView.onSuccess(response, 3);
                    }

                    @Override
                    public void onError(Throwable call, BaseResponse baseResponse) {
                        mView.hideProgress();
                        mView.onError(call, 3);
                    }
                });
    }
}
