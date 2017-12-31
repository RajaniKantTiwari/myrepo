package com.app.community.network;



import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.network.response.dashboard.meeting.ProductResponseData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by arvind on 01/11/17.
 */

public interface ApiService {
    @POST("register/user")
    Observable<LoginResponse> getLoginDetail(@Body LoginRequest request);


    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @GET("merchant/showmerchant")
    Observable<ProductResponseData> getMerchantList();


}
