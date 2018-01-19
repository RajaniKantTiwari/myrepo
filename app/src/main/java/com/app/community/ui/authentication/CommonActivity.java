package com.app.community.ui.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.community.CommonApplication;
import com.app.community.injector.component.CommonComponent;
import com.app.community.injector.component.DaggerCommonComponent;
import com.app.community.injector.module.CommonModule;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.base.MvpView;


/**
 * Created by arvind on 01/11/17.
 */
/*
Parent Activity of Authorization to give functionality to all Activity
*/
public abstract class CommonActivity extends BaseActivity implements MvpView,BaseFragment.Callback{
    private CommonComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent= DaggerCommonComponent.builder().
                commonModule(new CommonModule(this)).
                applicationComponent(((CommonApplication) getApplication()).
                        getApplicationComponent()).build();
        attachView();
    }

    public CommonComponent getActivityComponent() {
        return mActivityComponent;
    }
}