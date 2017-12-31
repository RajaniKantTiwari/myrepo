/*
 * Copyright © 2017 Hauler & DSE. All rights reserved.
 * Developed by Appster.
 *
 */
package com.app.community.utils;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.app.community.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 *
 */

public class ImagePickerUtils extends Fragment implements Alert.OnAlertClickListener {

    public interface OnImagePickerListener {

        void success(String name, String path, int type);

        void fail(String message);
    }

    private static final String TAG = ImagePickerUtils.class.getSimpleName();
    private static final int CAMERA_PIC_REQUEST = 2000;
    private static final int IMAGE_PICKER_REQUEST = CAMERA_PIC_REQUEST + 1;
    private static final int MEMORY_PERMISSION_REQUEST = IMAGE_PICKER_REQUEST + 1;
    private OnImagePickerListener listener;
    private String mediaPath;
    private boolean isFirstTime=true;
    private int mType;

    public static void add(@NonNull FragmentManager manager, @NonNull OnImagePickerListener listener) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(ImagePickerUtils.newInstance(listener), TAG);
        transaction.commit();
    }

    public static void add(@NonNull FragmentManager manager, @NonNull OnImagePickerListener listener, int type) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(ImagePickerUtils.newInstance(listener, type), TAG);
        transaction.commit();
    }

    private static ImagePickerUtils newInstance(@NonNull OnImagePickerListener listener) {
        ImagePickerUtils fragment = new ImagePickerUtils();
        fragment.setOnImagePickerListener(listener);
        return fragment;
    }

    private static ImagePickerUtils newInstance(@NonNull OnImagePickerListener listener, int type) {
        ImagePickerUtils fragment = new ImagePickerUtils();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        fragment.setOnImagePickerListener(listener);
        return fragment;
    }

    private void setOnImagePickerListener(OnImagePickerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mType = bundle.getInt("type");
        }

        if (isStoragePermissionGranted(true) && isCameraPermitionGranted(true)) {
            showGalleryDialog();
        }
    }

    private boolean isCameraPermitionGranted(boolean isFirstTime) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                LogUtils.LOGD(TAG, "Permission is granted");
                return true;
            } else if(isFirstTime){
                LogUtils.LOGD(TAG, "Permission is revoked");
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PIC_REQUEST);
                return false;
            }
            return false;
        } else { //permission is automatically granted on sdk<23 upon installation
            LogUtils.LOGD(TAG, "Permission is granted");
            return true;
        }

    }

    private boolean isStoragePermissionGranted(boolean isFirstTime) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                LogUtils.LOGD(TAG, "Permission is granted");
                return true;
            } else if(isFirstTime){
                LogUtils.LOGD(TAG, "Permission is revoked");
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_PERMISSION_REQUEST);
                return false;
            }
            return false;
        } else { //permission is automatically granted on sdk<23 upon installation
            LogUtils.LOGD(TAG, "Permission is granted");
            return true;
        }
    }


    private void showGalleryDialog() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo");
        builder.setItems(items, (dialog, which) -> {
            if (items[which].equals("Take Photo")&&isCameraPermitionGranted(true)) {
                dialog.dismiss();
                mediaPath = BitmapUtils.scaledImagePath();
                File file = new File(mediaPath);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri photoURI = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            } else if (items[which].equals("Choose from Library")&&isStoragePermissionGranted(true)) {
                dialog.dismiss();
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, IMAGE_PICKER_REQUEST);
            } else if (items[which].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private String handleCameraResult() {
        //Scale down the image to reduce size.
        mediaPath = BitmapUtils.scaleImage(getContext(), mediaPath, BitmapUtils.DEFAULT_PHOTO_WIDH,
                BitmapUtils.DEFAULT_PHOTO_HEIGHT);
        return mediaPath != null ? "file:///" + mediaPath : null;
    }

    private String handleGalleryResult(Intent intent) {
        String path = BitmapUtils.getImagePath(getContext(), intent);
        if (TextUtils.isEmpty(path)) {
            listener.fail("Please select proper image.");
            return null;
        } else {
            mediaPath = BitmapUtils.scaleImage(getContext(), path, BitmapUtils.DEFAULT_PHOTO_WIDH,
                    BitmapUtils.DEFAULT_PHOTO_HEIGHT);
            return "file:///" + mediaPath;
        }
    }

    //Runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (MEMORY_PERMISSION_REQUEST == requestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            LogUtils.LOGD(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            //showGalleryDialog();
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[0])) {
                LogUtils.LOGD("Permission", "Storage permission pending");
            } else {
                Alert.createYesNoAlert(getContext(), null, getString(R.string.storage_permission), this).show();
            }
        }
        if (CAMERA_PIC_REQUEST == requestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LogUtils.LOGD(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
            } else {
                LogUtils.LOGD("Permission", "Storage permission pending");
            }
        }
        if(isFirstTime){
            isFirstTime=false;
            isCameraPermitionGranted(true);
        }
        if ((isStoragePermissionGranted(false) && isCameraPermitionGranted(false))) {
            showGalleryDialog();
        }
        /*if ((!isFirstTime)&&(isStoragePermissionGranted(false) || isCameraPermitionGranted(false))) {
            showGalleryDialog();
        }*/
    }

    private void cropImage(Uri sourceUri, Uri destinationUri) {

        UCrop.Options options = new UCrop.Options();
        options.setHideBottomControls(true);
        options.setFreeStyleCropEnabled(true);
        options.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        options.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        options.setActiveWidgetColor(ContextCompat.getColor(getActivity(), R.color.progressColor));
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        uCrop.withOptions(options);
        uCrop.start(getActivity(), ImagePickerUtils.this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String filePath;
            switch (requestCode) {
                case CAMERA_PIC_REQUEST:
                    filePath = handleCameraResult();
                    cropImage(Uri.parse(filePath), Uri.parse(filePath));
                    break;
                case IMAGE_PICKER_REQUEST:
                    filePath = handleGalleryResult(data);
                    LogUtils.LOGD(TAG, filePath);
                    cropImage(Uri.parse(filePath), Uri.parse(filePath));

                    break;
                case UCrop.REQUEST_CROP:
                    LogUtils.LOGD(TAG, "CROP");
                    handleCropResult(data);
                    break;

            }

        }
    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        LogUtils.LOGD(TAG, "CROP " + resultUri);

        if (resultUri != null) {
            String filePath = resultUri.toString();
            LogUtils.LOGD(TAG, "CROP " + filePath);

            if (filePath != null) {
                listener.success(resultUri.toString(), resultUri.toString(), mType);
            } else {
                listener.fail("Unable to get path");
            }
        }
    }

    @Override
    public void onPositive(DialogInterface dialog) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void onNegative(DialogInterface dialog) {
        dialog.cancel();
    }

}