package com.app.community.ui.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.community.CommonApplication;
import com.app.community.injector.component.AuthenticationComponent;
import com.app.community.injector.component.DaggerAuthenticationComponent;
import com.app.community.injector.module.AuthenticationModule;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.base.MvpView;


/**
 * Created by arvind on 01/11/17.
 */
/*
Parent Activity of Authorization to give functionality to all Activity
*/
public abstract class AuthenticationActivity extends BaseActivity implements MvpView,BaseFragment.Callback{
    private AuthenticationComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent= DaggerAuthenticationComponent.builder().
                authenticationModule(new AuthenticationModule(this)).
                applicationComponent(((CommonApplication) getApplication()).
                        getApplicationComponent()).build();
        attachView();
    }

    public AuthenticationComponent getActivityComponent() {
        return mActivityComponent;
    }
}