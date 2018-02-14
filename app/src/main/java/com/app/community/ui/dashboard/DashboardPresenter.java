package com.app.community.ui.dashboard;

import android.app.Activity;


import com.app.community.network.DefaultApiObserver;
import com.app.community.network.Repository;
import com.app.community.network.request.DeviceTokenRequest;
import com.app.community.network.request.cart.CartListRequest;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.request.cart.CategoryRequest;
import com.app.community.network.request.cart.CheckoutRequest;
import com.app.community.network.request.dashboard.MerchantSearchRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.CategoryResponse;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.home.SearchResponseData;
import com.app.community.network.response.dashboard.home.WelcomeHomeData;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.base.Presenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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
                        mView.onSuccess(response,0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),0);
            }
        });
    }


    public void getCategory(Activity activity, CategoryRequest categoryRequest) {
        mView.showProgress();
        LogUtils.LOGD("","Repos=="+mRepository);
        mRepository.getCategory(categoryRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<CategoryResponse>(activity) {
            @Override
            public void onResponse(CategoryResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }

    public void addToCart(Activity activity, CartRequest cartRequest) {
        mView.showProgress();
        mRepository.addToCart(cartRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }

    public void deleteFromCart(Activity activity) {
        mView.showProgress();
        mRepository.deleteCart().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }
    public void viewCart(Activity activity, CartRequest cartRequest) {
        mView.showProgress();
        mRepository.viewCart(cartRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductDetailsData>(activity) {
            @Override
            public void onResponse(ProductDetailsData response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
            }
        });
    }

    public void getWelcomeHomePage(Activity activity) {
        mView.showProgress();
        mRepository.getWelcomeHomePage().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<WelcomeHomeData>(activity) {
            @Override
            public void onResponse(WelcomeHomeData response) {
                mView.hideProgress();
                mView.onSuccess(response,1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),1);
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
                activity.onError(baseResponse.getMsg(),AppConstants.RIGHT_DRAWER_RESPONSE);
            }
        });
    }

    public void setDeviceToken(DashBoardActivity activity, DeviceTokenRequest token) {
        mRepository.setDeviceToken(token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.onSuccess(response,AppConstants.DEVICE_TOKEN_RESPONSE);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.onError(baseResponse.getMsg(),AppConstants.DEVICE_TOKEN_RESPONSE);
            }
        });
    }

    public void getProductDetails(Activity activity, ProductRequest request) {
        mRepository.getProductDetail(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),2);
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
                activity.onError(baseResponse.getMsg(),AppConstants.LOGOUT);
            }
        });
    }

    public void addForCartList(DashBoardActivity activity, CartListRequest request) {
        activity.showProgress();
        mRepository.addForCartList(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                activity.hideProgress();
                activity.onSuccess(response, AppConstants.CARTADDED);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                activity.hideProgress();
                activity.onError(baseResponse.getMsg(),AppConstants.CARTADDED);
            }
        });
    }

    public void checkout(Activity activity,CheckoutRequest checkoutRequest) {
        mRepository.checkout(checkoutRequest).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response,2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(),2);
            }
        });
    }
}
