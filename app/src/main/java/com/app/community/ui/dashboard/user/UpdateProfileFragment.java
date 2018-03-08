package com.app.community.ui.dashboard.user;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentUpdateProfileBinding;
import com.app.community.event.EncodedBitmap;
import com.app.community.event.UpdateProfileEvent;
import com.app.community.network.request.dashboard.ProfilePic;
import com.app.community.network.request.dashboard.ProfileRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.user.UserAddress;
import com.app.community.ui.activity.uploadfile.Upload;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.dashboard.DashboardFragment;
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

import static android.app.Activity.RESULT_OK;

/**
 * Created by arvind on 06/11/17.
 */

public class UpdateProfileFragment extends DashboardFragment implements MvpView, View.OnClickListener,
        ImagePickerUtils.OnImagePickerListener,AddressAdapter.AddressListener {

    FragmentUpdateProfileBinding mBinding;
    private static String TAG = UpdateProfileFragment.class.getSimpleName();
    private List<UserAddress> addressList = new ArrayList<>();
    private String profilePicFilePath;

    private AddressAdapter addressAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_profile,container,false);
        CommonUtils.register(this);
        getDashboardActivity().setHeaderTitle(getResources().getString(R.string.your_profile));
        return mBinding.getRoot();
    }


    public void setListener() {
        mBinding.ivProfile.setOnClickListener(this);
        mBinding.imgEditPic.setOnClickListener(this);
        //mBinding.tvUpdate.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return UpdateProfileFragment.class.getSimpleName();
    }
    @Override
    public void initializeData() {
        mBinding.edName.setText(PreferenceUtils.getUserName());
        mBinding.tvMobile.setText(PreferenceUtils.getUserMono());
        mBinding.edEmail.setText(PreferenceUtils.getUserMono());
        CommonUtils.showCursorEnd(mBinding.edName);
        CommonUtils.showCursorEnd(mBinding.edEmail);
        mBinding.edName.setCursorVisible(true);
        GlideUtils.loadImageProfilePic(getContext(), PreferenceUtils.getImage(), mBinding.ivProfile, null, R.drawable.avatar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvAddress.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter(getDashboardActivity(), addressList,this);
        mBinding.rvAddress.setAdapter(addressAdapter);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == GeneralConstant.PROFILE_PIC_RESPONSE && response.getStatus().equalsIgnoreCase(AppConstants.SUCCESS)) {
                getDashboardActivity().showToast(getResources().getString(R.string.profile_pic_updated_successfully));
            }

        }

    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);
    }

    @Override
    public void onClick(View view) {
        if (mBinding.ivProfile == view || mBinding.imgEditPic == view) {
            showImageChooserDialog();
        } /*else if (mBinding.tvUpdate == view) {
            PreferenceUtils.setImage(profilePicFilePath);
            PreferenceUtils.setUserName(mBinding.edName.getText().toString());
            PreferenceUtils.setEmail(mBinding.edEmail.getText().toString());
            updateProfile();
        }*/
    }


    private void showImageChooserDialog() {
        //ImagePickerUtils.add(getSupportFragmentManager(), this);

        BottomSheetDialog dialog = new BottomSheetDialog(getDashboardActivity());
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
        new ImagePicker.Builder(getDashboardActivity())
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
        new ImagePicker.Builder(getDashboardActivity())
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
        GlideUtils.loadImageProfilePic(getContext(), path, mBinding.ivProfile, null, 0);
        PreferenceUtils.setImage(path);
        EventBus.getDefault().post(new UpdateProfileEvent());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                updateProfilePic();
                EventBus.getDefault().post(new UpdateProfileEvent());
            }
        }
    }

    private void setImageFromLocal(String filePath) {
        profilePicFilePath = filePath;
        File f = new File(filePath);
        if (f.exists()) {
            GlideUtils.loadImageProfilePic(getContext(), filePath, mBinding.ivProfile, null, R.drawable.avatar);
        }
    }

    private void gotoCropper(Uri sourceUri) {
        CropImage.activity(sourceUri).setAspectRatio(1, 1)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(getDashboardActivity());
    }

    private void updateProfile() {
        ProfileRequest profileRequest=new ProfileRequest();
        profileRequest.setUserid(PreferenceUtils.getUserId());
        profileRequest.setName(PreferenceUtils.getUserName());
        profileRequest.setAddress(PreferenceUtils.getAddress());
        profileRequest.setCity(PreferenceUtils.getCity());
        profileRequest.setEmail(mBinding.edEmail.getText().toString());
        if (isNetworkConnected()) {
            getPresenter().updateProfile(getDashboardActivity(),profileRequest);
        }
    }



    private void updateProfilePic() {
        try {
            Bitmap bitmap = ((RoundedBitmapDrawable) mBinding.ivProfile.getDrawable()).getBitmap();
            Upload postImage = new Upload(getDashboardActivity(), bitmap);
            postImage.execute();
        } catch (Exception e) {
            LogUtils.LOGE("ProfileUpdate", e.toString());
        }


    }

    @Override
    public void onDestroy() {
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
                getPresenter().updateProfilePic(getDashboardActivity(), profilePicRequest);
            }
        }


    }

    @Override
    public void onDeleteClick(int position) {
        addressList.remove(position);
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddressClick(int position) {
        addressAdapter.notifyDataSetChanged();
    }
}
