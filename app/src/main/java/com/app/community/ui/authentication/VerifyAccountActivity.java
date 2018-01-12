package com.app.community.ui.authentication;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.app.community.CommonApplication;
import com.app.community.R;
import com.app.community.databinding.ActivityVerifyAccountBinding;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.VerifyMobileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.network.response.VerifyMobileResponse;
import com.app.community.ui.dashboard.DashBoardActivity;
import com.app.community.ui.dialogfragment.CustomDialogFragment;
import com.app.community.ui.presenter.AuthenticationPresenter;
import com.app.community.utils.ApiConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtil;

import javax.inject.Inject;

/**
 * Created by on 23/12/17.
 */

public class VerifyAccountActivity extends AuthenticationActivity implements TextWatcher, View.OnKeyListener,CustomDialogFragment.CustomDialogListener {
    private ActivityVerifyAccountBinding mBinding;
    StringBuilder otpNumber = new StringBuilder();
    @Inject
    AuthenticationPresenter presenter;
    private String mobileNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify_account);
        showKeyboard();
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void setListener() {
        mBinding.edFirst.addTextChangedListener(this);
        mBinding.edSecond.addTextChangedListener(this);
        mBinding.edThird.addTextChangedListener(this);
        mBinding.edFourth.addTextChangedListener(this);
        mBinding.edFirst.setOnKeyListener(this);
        mBinding.edSecond.setOnKeyListener(this);
        mBinding.edThird.setOnKeyListener(this);
        mBinding.edFourth.setOnKeyListener(this);

        mBinding.tvResend.setOnClickListener(this);
        mBinding.tvChange.setOnClickListener(this);

    }

    @Override
    public void initializeData() {
        Intent intent = getIntent();
        if (isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (isNotNull(bundle)) {
                mobileNumber = bundle.getString(GeneralConstant.MOBILE_NUMBER);
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvResend) {
            CommonUtils.clicked(mBinding.tvResend);
            presenter.getLoginDetail(this,new LoginRequest(mobileNumber));
        } else if (view == mBinding.tvChange) {
            //CommonUtils.clicked(mBinding.tvChange);
            Bundle bundle=new Bundle();
            bundle.putString(GeneralConstant.TITLE,getResources().getString(R.string.please_enter_mobile_number));
            bundle.putBoolean(GeneralConstant.VISIBLE,true);
            CommonUtils.showDialog(this,bundle,this);
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == 1) {
            gotoNext(response);
        } else if (requestCode == 2) {
            getOtp(response);
        }
        if(CommonApplication.isDebug){
            ExplicitIntent.getsInstance().navigateTo(this, DashBoardActivity.class);
        }
    }

    private void getOtp(BaseResponse response) {
        if(isNotNull(response)){
            if(response instanceof LoginResponse){
                LoginResponse loginResponse=(LoginResponse)response;
                if(isNotNull(loginResponse)){
                    String type=loginResponse.getType();
                    if(type.equals(ApiConstants.SUCCESS)){
                        showToast(getResources().getString(R.string.otp_has_been_send));
                    }
                }
            }
        }
    }

    private void gotoNext(BaseResponse response) {try {
            if (isNotNull(response)) {
                if (response instanceof VerifyMobileResponse) {
                    VerifyMobileResponse verifyMobileResponse = (VerifyMobileResponse) response;
                    if (isNotNull(verifyMobileResponse)) {
                        String status = verifyMobileResponse.getStatus();
                        if (status.equals(ApiConstants.SUCCESS)) {
                            showToast("Success");
                        } else {
                            showToast(getResources().getString(R.string.server_error));
                        }
                    }
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String message, int requestCode) {
        showToast("Error");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.e("CharSequence", "" + otpNumber.length());
        if (otpNumber.length() == 0 && mBinding.edFirst.length() == 1) {
            otpNumber.append(s);
            mBinding.edFirst.clearFocus();
            mBinding.edSecond.requestFocus();
            mBinding.edSecond.setCursorVisible(true);
        } else if (otpNumber.length() == 1 && mBinding.edSecond.length() == 1) {
            otpNumber.append(s);
            mBinding.edSecond.clearFocus();
            mBinding.edThird.requestFocus();
            mBinding.edThird.setCursorVisible(true);
        } else if (otpNumber.length() == 2 && mBinding.edThird.length() == 1) {
            otpNumber.append(s);
            mBinding.edThird.clearFocus();
            mBinding.edFourth.requestFocus();
            mBinding.edFourth.setCursorVisible(true);
        } else if (otpNumber.length() == 3 && mBinding.edFourth.length() == 1) {
            otpNumber.append(s);
            mBinding.edThird.clearFocus();
            mBinding.edFourth.requestFocus();
            mBinding.edFourth.setCursorVisible(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (otpNumber.length() == 4) {
            presenter.verifyMobileNumber(this, new VerifyMobileRequest(mobileNumber, Integer.parseInt(otpNumber.toString())));
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (otpNumber.length() == 1) {
                mBinding.edSecond.clearFocus();
                mBinding.edFirst.requestFocus();
                otpNumber.deleteCharAt(0);
            } else if (otpNumber.length() == 2) {
                mBinding.edSecond.clearFocus();
                mBinding.edFirst.requestFocus();
                otpNumber.deleteCharAt(1);
            } else if (otpNumber.length() == 3) {
                mBinding.edThird.clearFocus();
                mBinding.edSecond.requestFocus();
                otpNumber.deleteCharAt(2);
            } else if (otpNumber.length() == 4) {
                mBinding.edFourth.clearFocus();
                mBinding.edThird.requestFocus();
                otpNumber.deleteCharAt(3);
            }
        }
        return false;
    }

    @Override
    public void ok(String str) {
        mobileNumber=str;
        presenter.getLoginDetail(this,new LoginRequest(mobileNumber));
    }

    @Override
    public void cancel() {

    }
}
