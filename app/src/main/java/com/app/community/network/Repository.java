package com.app.community.network;

import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;

import io.reactivex.Observable;


/**
 * Created by atul on 24/01/17.
 * Interface having all the api functions declaration.
 */

public interface Repository {
    Observable<LoginResponse> getLoginDetail(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);

    Observable<ProductResponseData> getMerchantList();
}
