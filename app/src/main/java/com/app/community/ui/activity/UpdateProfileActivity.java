package com.app.community.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;


import com.app.community.R;
import com.app.community.databinding.ActivityUpdateProfileBinding;
import com.app.community.network.request.PaymentOption;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.MvpView;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.ImagePickerUtils;
import com.app.community.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvind on 06/11/17.
 */

public class UpdateProfileActivity extends CommonActivity implements MvpView, View.OnClickListener, ImagePickerUtils.OnImagePickerListener {

    ActivityUpdateProfileBinding mBinding;
    private static String TAG = UpdateProfileActivity.class.getSimpleName();
    private PaymentAdapter paymentAdapter;
    private List<PaymentOption> paymentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);
        initializeData();
        setListener();
    }

    public void setListener() {
        mBinding.ivProfile.setOnClickListener(this);
    }

    public void initializeData() {
        setPaymentOption();
        paymentAdapter = new PaymentAdapter(this, paymentList);
        mBinding.rvPayment.setAdapter(paymentAdapter);
        CommonUtils.setRecyclerViewHeight(mBinding.rvPayment, paymentList, GeneralConstant.PAYMENT_HEIGHT);
    }
    private void setPaymentOption() {
        PaymentOption option1 = new PaymentOption();
        option1.setPaymentString(getResources().getString(R.string.cash_on_delivery));
        PaymentOption option2 = new PaymentOption();
        option2.setPaymentString(getResources().getString(R.string.credit_debit_card));
        PaymentOption option3 = new PaymentOption();
        option3.setPaymentString(getResources().getString(R.string.paytm));
        paymentList.add(option1);
        paymentList.add(option2);
        paymentList.add(option3);

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivProfile:
                showImageChooserDialog();
                break;
        }
    }

    private void showImageChooserDialog() {
        ImagePickerUtils.add(getSupportFragmentManager(), this);
    }

    @Override
    public void success(String name, String path, int type) {
        GlideUtils.loadImageProfilePic(this, path, mBinding.ivProfile, null, 0);
    }

    @Override
    public void fail(String message) {
        LogUtils.LOGD(TAG, message);
    }

}
