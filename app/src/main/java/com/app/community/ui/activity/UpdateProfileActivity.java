package com.app.community.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;


import com.app.community.R;
import com.app.community.databinding.ActivityUpdateProfileBinding;
import com.app.community.event.EncodedBitmap;
import com.app.community.event.UpdateProfileEvent;
import com.app.community.network.request.LoginRequest;
import com.app.community.network.request.PaymentOption;
import com.app.community.network.request.dashboard.ProfilePic;
import com.app.community.network.request.dashboard.ProfileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.activity.uploadfile.Upload;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.ImagePickerUtils;
import com.app.community.utils.LogUtils;
import com.app.community.utils.PreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import net.alhazmy13.mediapicker.Image.ImagePicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * Created by arvind on 06/11/17.
 */

public class UpdateProfileActivity extends CommonActivity implements MvpView, View.OnClickListener, ImagePickerUtils.OnImagePickerListener {

    ActivityUpdateProfileBinding mBinding;
    private static String TAG = UpdateProfileActivity.class.getSimpleName();
    private PaymentAdapter paymentAdapter;
    private List<PaymentOption> paymentList = new ArrayList<>();
    private String profilePicFilePath;

    @Inject
    CommonPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);
        CommonUtils.register(this);
        initializeData();
        setHeader();
        setListener();
    }

    private void setHeader() {
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.update_profile));
    }

    public void setListener() {
        mBinding.ivProfile.setOnClickListener(this);
        mBinding.imgEditPic.setOnClickListener(this);
        mBinding.tvUpdate.setOnClickListener(this);
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }

    public void initializeData() {
        mBinding.edName.setText(PreferenceUtils.getUserName());
        mBinding.tvMobile.setText(PreferenceUtils.getUserMono());
        mBinding.edEmail.setText(PreferenceUtils.getUserMono());
        mBinding.edCreditDetails.setText(PreferenceUtils.getUserMono());
        CommonUtils.showCursorEnd(mBinding.edName);
        CommonUtils.showCursorEnd(mBinding.edEmail);
        CommonUtils.showCursorEnd(mBinding.edCreditDetails);
        mBinding.edName.setCursorVisible(true);
        GlideUtils.loadImageProfilePic(this, PreferenceUtils.getImage(), mBinding.ivProfile, null, R.drawable.avatar);
        setPaymentOption();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvPayment.setLayoutManager(layoutManager);
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
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == GeneralConstant.PROFILE_PIC_RESPONSE && response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
                showToast(getResources().getString(R.string.profile_pic_updated_successfully));
            }

        }

    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (mBinding.ivProfile == view || mBinding.imgEditPic == view) {
            showImageChooserDialog();
        } else if (mBinding.tvUpdate == view) {
            PreferenceUtils.setImage(profilePicFilePath);
            PreferenceUtils.setUserName(mBinding.edName.getText().toString());
            PreferenceUtils.setEmail(mBinding.edEmail.getText().toString());
            PreferenceUtils.setCardNumber(mBinding.edCreditDetails.getText().toString());
            updateProfile();
            EventBus.getDefault().post(new UpdateProfileEvent());
            finish();
        } else if (mBinding.layoutHeader.ivBack == view) {
            finish();
        }
    }


    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.profile_dialog_layout);
        View layoutCamera = dialog.findViewById(R.id.layoutCamera);
        View layoutGallery = dialog.findViewById(R.id.layoutGallery);
        View layoutCancel = dialog.findViewById(R.id.layoutCancel);
        layoutCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openImageCamera();
            }
        });
        layoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openImageGallery();
            }
        });
        layoutCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                clearImage();
            }
        });
        dialog.show();

    }

    private void clearImage() {
        profilePicFilePath = "";
    }

    /**
     * Open camera to capture image
     */
    private void openImageCamera() {
        new ImagePicker.Builder(this)
                .mode(ImagePicker.Mode.CAMERA)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.JPG)
                .scale(512, 512)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }

    /**
     * Open camera to capture image
     */
    private void openImageGallery() {
        new ImagePicker.Builder(this)
                .mode(ImagePicker.Mode.GALLERY)
                .compressLevel(ImagePicker.ComperesLevel.HARD)
                .directory(ImagePicker.Directory.DEFAULT)
                .extension(ImagePicker.Extension.JPG)
                .scale(512, 512)
                .allowMultipleImages(false)
                .enableDebuggingMode(true)
                .build();
    }

    @Override
    public void success(String name, String path, int type) {
        GlideUtils.loadImageProfilePic(this, path, mBinding.ivProfile, null, 0);
        PreferenceUtils.setImage(path);
        EventBus.getDefault().post(new UpdateProfileEvent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ImagePicker.IMAGE_PICKER_REQUEST_CODE) {
                List<String> mPaths = (List<String>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_PATH);
                profilePicFilePath = mPaths.get(0);
                profilePicFilePath = "file:///" + profilePicFilePath;
                gotoCropper(Uri.parse(profilePicFilePath));
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                LogUtils.LOGD(TAG, "CROP");
                //handleCropResult(data);

                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri imageUri = result.getUri();
                String path = imageUri.getPath();
                setImageFromLocal(path);

            }
        }
    }

    private void setImageFromLocal(String filePath) {
        profilePicFilePath = filePath;
        File f = new File(filePath);
        if (f.exists()) {
            GlideUtils.loadImageProfilePic(this, filePath, mBinding.ivProfile, null, R.drawable.avatar);
        }
    }

    private void gotoCropper(Uri sourceUri) {
        CropImage.activity(sourceUri).setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(this);
    }

    /*private void updateProfile() {
        try {
            //int age = Integer.parseInt(mBinding.edtAge.getText().toString().trim());
            //String name = mBinding.edtName.getText().toString().trim();
            //String phoneNumber = mBinding.edtPhone.getText().toString().trim();
            if (TextUtils.isEmpty(profilePicFilePath)) {
                // presenter.updateProfile(this, name, phoneNumber, age, currentGender, null);
            } else {
                MultipartBody.Part body = CommonUtils.createMultipart(profilePicFilePath, GeneralConstant.PROFILE_UPDATE_PARAMETER);
                if (body != null) {
                    // presenter.updateProfile(this, name, phoneNumber, age, currentGender, body);
                } else {
                    //presenter.updateProfile(this, name, phoneNumber, age, currentGender, null);
                }

            }

        } catch (Exception e) {
            LogUtils.LOGE("ProfileUpdate", e.toString());
        }


    }*/


    private void updateProfile() {
        try {
            Bitmap bitmap = ((RoundedBitmapDrawable) mBinding.ivProfile.getDrawable()).getBitmap();
            Upload postImage = new Upload(this, bitmap);
            postImage.execute();
        } catch (Exception e) {
            LogUtils.LOGE("ProfileUpdate", e.toString());
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);
    }

    @Override
    public void fail(String message) {
        LogUtils.LOGD(TAG, message);
    }

    @Subscribe
    public void onUploadProfile(EncodedBitmap event) {
        ProfilePic profilePicRequest = new ProfilePic();
        if (CommonUtils.isNotNull(profilePicFilePath) && profilePicFilePath.length() > 0) {
            String encodedImage = event.getEncodeImage();
            profilePicRequest.setImage(encodedImage);
            if (isNetworkConnected()) {
                presenter.updateProfilePic(this, profilePicRequest);
            }
        }
       /* profileRequest.setUserid(PreferenceUtils.getUserId());
        profileRequest.setName(PreferenceUtils.getUserName());
        profileRequest.setAddress(PreferenceUtils.getAddress());
        profileRequest.setCity(PreferenceUtils.getCity());
        profileRequest.setEmail(PreferenceUtils.getEmail());*/
        if (isNetworkConnected()) {
            //presenter.updateProfile(this,profileRequest);
        }

    }
}
