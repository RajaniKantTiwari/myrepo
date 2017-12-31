package com.app.community.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.community.CommonApplication;
import com.app.community.injector.component.DaggerDashboardComponent;
import com.app.community.injector.component.DashboardComponent;
import com.app.community.injector.module.DashboardModule;
import com.app.community.ui.base.BaseActivity;

/**
 * Created by rajnikant on 31/12/17.
 */

public abstract class DashboardInsideActivity extends BaseActivity {
    private DashboardComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent= DaggerDashboardComponent.builder().
                dashboardModule(new DashboardModule(this)).
                applicationComponent(((CommonApplication) getApplication()).
                        getApplicationComponent()).build();
        attachView();
    }

    public DashboardComponent getActivityComponent() {
        return mActivityComponent;
    }
}
