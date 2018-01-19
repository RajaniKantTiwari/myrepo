package com.app.community.ui.dashboard;

import android.app.Activity;

import com.app.community.network.DefaultApiObserver;
import com.app.community.network.Repository;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.ProductRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.dashboardinside.ProductDetailsData;
import com.app.community.network.response.dashboard.feed.MerchantDetailsData;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.base.Presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DashboardInsidePresenter implements Presenter<MvpView> {
    private MvpView mView;
    private Repository mRepository;


    @Override
    public void attachView(MvpView view) {
        mView = view;
    }

    @Inject
    public DashboardInsidePresenter(Repository repository) {
        this.mRepository = repository;
    }

    public void getProductDetails(Activity activity,ProductRequest productRequest) {
        mView.showProgress();
        mRepository.getProductDetail(productRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductDetailsData>(activity) {
            @Override
            public void onResponse(ProductDetailsData response) {
                mView.hideProgress();
                mView.onSuccess(response,0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMessage(),0);
            }
        });
    }

    public void getMerchantDetails(Activity activity, MerchantRequest merchantRequest) {
        mView.showProgress();
        mRepository.getMerchantDetail(merchantRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<MerchantDetailsData>(activity) {
            @Override
            public void onResponse(MerchantDetailsData response) {
                mView.hideProgress();
                mView.onSuccess(response,0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMessage(),0);
            }
        });
    }
}
