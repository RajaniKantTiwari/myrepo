package com.app.community.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityLoginBinding;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.ApiConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.UserPreference;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import javax.inject.Inject;

public class LoginActivity extends CommonActivity implements MvpView, View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding mBinding;
    @Inject
    CommonPresenter presenter;
    private String mobileNumber;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        setListener();
    }

    public void setListener() {
        mBinding.tvGetOtp.setOnClickListener(this);
        mBinding.tvSignupForAccount.setOnClickListener(this);
    }

    public void initializeData() {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);

            /*String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);*/

            //Log.i(TAG, "GCM Registration Token: " + token);

        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
        }
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if(isNotNull(response)){
            if(response instanceof LoginResponse){
                LoginResponse loginResponse=(LoginResponse)response;
                if(isNotNull(loginResponse)){
                    String type=loginResponse.getType();
                    if(type.equals(ApiConstants.SUCCESS)){
                        UserPreference.setUserName(userName);
                        Bundle bundle=new Bundle();
                        bundle.putString(GeneralConstant.USER_NAME,userName);
                        bundle.putString(GeneralConstant.MOBILE_NUMBER,mobileNumber);
                        ExplicitIntent.getsInstance().navigateTo(this,VerifyAccountActivity.class,bundle);
                    }
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view==mBinding.tvGetOtp){
            CommonUtils.clicked(mBinding.tvGetOtp);
           if(isValid()){
               if(isNetworkConnected()){
                   presenter.getLoginDetail(this,new LoginRequest(userName,mobileNumber,
                           UserPreference.getLatitude(), UserPreference.getLongitude()));
               }
           }
        }else if(view==mBinding.tvSignupForAccount){
            CommonUtils.clicked(mBinding.tvSignupForAccount);
        }
    }

    private boolean isValid() {
        mobileNumber=mBinding.edMobileNumber.getText().toString();
        userName=mBinding.edName.getText().toString();
        if((isNotNull(mobileNumber)&&mobileNumber.trim().length()>0)&&(isNotNull(userName)&&userName.trim().length()>0)){
            return true;
        }else if(isNull(userName)||userName.trim().length()==0){
            showToast(getResources().getString(R.string.please_enter_name));
            return false;
        }else{
            showToast(getResources().getString(R.string.please_enter_mobile_number));
            return false;
        }

    }


}
