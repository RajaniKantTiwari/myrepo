package com.app.community.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityEditAddressBinding;
import com.app.community.databinding.ActivityUpdateProfileBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.MvpView;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.ImagePickerUtils;
import com.app.community.utils.LogUtils;

/**
 * Created by arvind on 06/11/17.
 */

public class EditAddressActivity extends CommonActivity implements MvpView, View.OnClickListener, ImagePickerUtils.OnImagePickerListener {

    ActivityEditAddressBinding mBinding;
    private static String TAG = EditAddressActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_address);
        initializeData();
        setListener();
    }

    public void setListener() {
    }

    public void initializeData() {

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
        //GlideUtils.loadImageProfilePic(this, path, mBinding.ivProfile, mBinding.progressBar, 0);
    }

    @Override
    public void fail(String message) {
        LogUtils.LOGD(TAG, message);
    }

}
