package com.app.community.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.app.community.R;
import com.app.community.databinding.ActivityLoginBinding;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.presenter.AuthenticationPresenter;
import com.app.community.utils.ApiConstants;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtil;

import javax.inject.Inject;

public class LoginActivity extends AuthenticationActivity implements MvpView, View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    ActivityLoginBinding mBinding;
    @Inject
    AuthenticationPresenter presenter;
    private String mobileNumber;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    public void setListener() {
        mBinding.tvGetOtp.setOnClickListener(this);
    }

    public void initializeData() {
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
                        PreferenceUtil.setUserName(userName);
                        Bundle bundle=new Bundle();
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
           if(isValid()){
               presenter.getLoginDetail(this,new LoginRequest(mobileNumber));
           }
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
