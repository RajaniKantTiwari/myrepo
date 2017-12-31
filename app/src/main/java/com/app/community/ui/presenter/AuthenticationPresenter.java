package com.app.community.ui.presenter;

import android.app.Activity;

import com.app.community.network.DefaultApiObserver;
import com.app.community.network.Repository;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.ui.authentication.VerifyAccountActivity;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.base.Presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arvind on 09/11/17.
 */

public class AuthenticationPresenter implements Presenter<MvpView> {

    private final Repository mRepository;
    private MvpView mView;


    public AuthenticationPresenter(Repository repository) {
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
                mView.onError(baseResponse.getMessage(), 2);
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
                mView.onError(baseResponse.getMessage(), 1);
            }
        });
    }
}
