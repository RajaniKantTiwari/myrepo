package com.app.community.network;

import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by atul on 24/01/17.
 * Interface having all the api functions declaration.
 */

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
    public Observable<ProductResponseData> getMerchantList() {
        return apiService.getMerchantList();
    }
}
