package com.app.community.ui.dashboard;

import android.app.Activity;


import com.app.community.network.DefaultApiObserver;
import com.app.community.network.Repository;
import com.app.community.network.request.dashboard.ProductSearchRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.SearchResponseData;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.base.Presenter;

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

    /*public void getMerchantList(Activity activity) {
        mView.showProgress();
        mRepository.getMerchantList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductResponseData>(activity) {
            @Override
            public void onResponse(ProductResponseData response) {
                mView.hideProgress();
                        mView.onSuccess(response,0);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMessage(),0);
            }
        });
    }*/

    public void searchProductList(Activity activity,ProductSearchRequest productSearchRequest) {
        mRepository.searchProductList(productSearchRequest).
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
                mView.onError(baseResponse.getMessage(), 1);
            }
        });
    }
}
