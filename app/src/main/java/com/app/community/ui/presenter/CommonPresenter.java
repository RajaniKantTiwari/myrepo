package com.app.community.ui.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.app.community.network.DefaultApiObserver;
import com.app.community.network.Repository;
import com.app.community.network.request.Feedback;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.UpdateLocation;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.OrderDetailsRequest;
import com.app.community.network.request.dashboard.ProfilePic;
import com.app.community.network.request.dashboard.ProfileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.MyOrderData;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.OrderDetailData;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.ui.WelcomeScreenActivity;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.base.Presenter;
import com.app.community.ui.dashboard.home.fragment.MyOrderActivity;
import com.app.community.utils.AppConstants;
import com.app.community.utils.GeneralConstant;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arvind on 09/11/17.
 */

public class CommonPresenter implements Presenter<MvpView> {

    private final Repository mRepository;
    private MvpView mView;


    public CommonPresenter(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(MvpView view) {
        this.mView = view;
    }

    public void getLoginDetail(Activity activity, LoginRequest loginRequest) {
        mView.showProgress();
        mRepository.getLoginDetail(loginRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<LoginResponse>(activity) {
            @Override
            public void onResponse(LoginResponse response) {
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

    public void verifyMobileNumber(Activity activity, VerifyMobileRequest verifyMobileRequest) {
        mView.showProgress();
        mRepository.verifyMobileNumber(verifyMobileRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<VerifyMobileResponse>(activity) {
            @Override
            public void onResponse(VerifyMobileResponse response) {
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

    public void getMerchantListBySearch(Activity activity,MerchantSearchRequest productSearchRequest) {
        mRepository.getMerchantListBySearch(productSearchRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<SearchResponseData>(activity) {
            @Override
            public void onResponse(SearchResponseData response) {
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
    public void get(Activity activity, LoginRequest loginRequest) {
        mView.showProgress();
        mRepository.getLoginDetail(loginRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<LoginResponse>(activity) {
            @Override
            public void onResponse(LoginResponse response) {
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

    public void updateLocation(WelcomeScreenActivity activity, UpdateLocation updateLocation) {
        activity.showProgress();
        mRepository.updateLocation(updateLocation).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.hideProgress();
                activity.onSuccess(response, 0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.hideProgress();
                activity.onError(call, 0);
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
                mView.onError(call,  AppConstants.DELETE_CART);
            }
        });
    }



    public void getMyOrder(MyOrderActivity activity) {
        activity.showProgress();
        mRepository.getMyOrder().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<MyOrderData>(activity) {
            @Override
            public void onResponse(MyOrderData response) {
                activity.hideProgress();
                activity.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.hideProgress();
                activity.onError(call, 1);
            }
        });
    }


    public void submitFeedBack(Activity activity,Feedback feedback) {
        mView.showProgress();
        mRepository.submitFeedBack(feedback).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
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
    public void orderDetails(FragmentActivity activity, OrderDetailsRequest request) {
        mView.showProgress();
        mRepository.orderDetails(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(new DefaultApiObserver<OrderDetailData>(activity) {
                    @Override
                    public void onResponse(OrderDetailData response) {
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
    public void getMerchantDetails(Activity activity, MerchantRequest merchantRequest) {
        mView.showProgress();
        mRepository.getMerchantDetail(merchantRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<MerchantResponseData>(activity) {
            @Override
            public void onResponse(MerchantResponseData response) {
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
}
