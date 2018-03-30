package com.app.community.ui.authentication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.community.R;
import com.app.community.databinding.ActivityLoginBinding;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.LoginResponse;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtils;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

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
        Firebase.setAndroidContext(this);
        setListener();
    }

    public void setListener() {
        mBinding.tvGetOtp.setOnClickListener(this);
        mBinding.tvSignupForAccount.setOnClickListener(this);
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
                    if(type.equals(AppConstants.SUCCESS)){
                        PreferenceUtils.setUserName(userName);
                        PreferenceUtils.setUserMono(mobileNumber);
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
                           PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
                   loginOnFireBase();
               }
           }
        }else if(view==mBinding.tvSignupForAccount){
            CommonUtils.clicked(mBinding.tvSignupForAccount);
        }
    }

    private void loginOnFireBase() {

        String url = AppConstants.FIREBASE_BASE_URL+"/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase(AppConstants.FIREBASE_BASE_URL+"/users");

                if(s.equals("null")) {
                    reference.child(mobileNumber).child("password").setValue(userName);
                    //Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();

                }
                else {
                    try {
                        JSONObject obj = new JSONObject(s);

                        if (!obj.has(mobileNumber)) {
                            reference.child(mobileNumber).child("password").setValue(userName);
                            //Toast.makeText(LoginActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                        } else {
                            //Toast.makeText(LoginActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError );
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(request);
    }

    private boolean isValid() {
        mobileNumber=mBinding.edMobileNumber.getText().toString();
        userName=mBinding.edName.getText().toString();
        if(isNull(userName)||userName.trim().length()==0){
            showToast(getResources().getString(R.string.please_enter_name));
            return false;
        }else if(!CommonUtils.checkValidName(userName)){
            showToast(getResources().getString(R.string.please_enter_valid_name));
            return false;
        }else if(isNull(mobileNumber)||mobileNumber.trim().length()==0){
            showToast(getResources().getString(R.string.please_enter_mobile_number));
            return false;
        }else if(mobileNumber.length()!=10){
            showToast(getResources().getString(R.string.please_enter_valid_mobile_number));
            return false;
        }
        return true;
    }


}
