package com.app.community.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.community.CommonApplication;
import com.app.community.R;
import com.app.community.ui.dashboard.DashBoardActivity;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.UserPreference;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(CommonApplication.isDebug){
                    ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, DashBoardActivity.class);
                }else{
                    if (UserPreference.isLogin()) {
                        ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, DashBoardActivity.class);
                    } else {
                        ExplicitIntent.getsInstance().navigateTo(SplashActivity.this, WelcomeScreenActivity.class);

                    }
                }

                finish();
            }
        }, GeneralConstant.SPLASH_TIME);
    }

}
