package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityConfirmOrderBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseActivity;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class ConfirmOrderActivity extends BaseActivity {

 private ActivityConfirmOrderBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          mBinding=DataBindingUtil.setContentView(this,R.layout.activity_confirm_order);

    }



    @Override
    public void attachView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initializeData() {
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }
}
