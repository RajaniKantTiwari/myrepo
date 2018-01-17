package com.app.community.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.ActivityWelcomeScreenBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.LoginActivity;
import com.app.community.ui.authentication.VerifyAccountActivity;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dialogfragment.CustomDialogFragment;
import com.app.community.ui.location.GPSTracker;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtil;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import static com.app.community.utils.GeneralConstant.PERMISSIONS_REQUEST_LOCATION;


/**
 * Created by arvind on 21/12/17.
 */

public class WelcomeScreenActivity extends BaseActivity implements CustomDialogFragment.CustomDialogListener {
    private ActivityWelcomeScreenBinding mBinding;

    private GPSTracker gpsTracker;
    private boolean isClickedOnAddress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome_screen);
        setListener();
    }

    public void setListener() {
        mBinding.layoutLocation.setOnClickListener(this);
        mBinding.tvManually.setOnClickListener(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutLocation) {
            CommonUtils.clicked(mBinding.layoutLocation);
            getCurrentLocation();
        }else if(view==mBinding.tvManually){
            CommonUtils.clicked(mBinding.tvManually);
            if(!isClickedOnAddress){
                isClickedOnAddress=true;
                address();
            }
        }
    }
    private void address() {
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException e) {
        } catch (GooglePlayServicesNotAvailableException e) {
        }
    }
    private void getCurrentLocation() {
        // create class object
        gpsTracker = new GPSTracker(WelcomeScreenActivity.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            gpsTracker.requestPermission(this);
        } else if (gpsTracker.canGetLocation()) {
            getLatLong();
        }
    }

    private void getLatLong() {
        PreferenceUtil.setLatitude(gpsTracker.getLatitude());
        PreferenceUtil.setLongitude(gpsTracker.getLongitude());
        /*Bundle bundle=new Bundle();
        bundle.putString(GeneralConstant.TITLE,getResources().getString(R.string.dialog_title));
        CommonUtils.showDialog(this,bundle,this);*/
        ExplicitIntent.getsInstance().navigateTo(this, LoginActivity.class);
        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gpsTracker.getLocation();
                    getLatLong();
                } else {
                    showToast(getResources().getString(R.string.gps_permittion_denied));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            isClickedOnAddress=false;
            if (resultCode == RESULT_OK) {
                try {
                    // retrive the data by using getPlace() method.
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    LatLng latLng = place.getLatLng();
                    PreferenceUtil.setLatitude(latLng.latitude);
                    PreferenceUtil.setLongitude(latLng.longitude);
                    /*Bundle bundle=new Bundle();
                    bundle.putString(GeneralConstant.TITLE,getResources().getString(R.string.dialog_title));
                    CommonUtils.showDialog(this,bundle,this);*/
                    ExplicitIntent.getsInstance().navigateTo(this, LoginActivity.class);
                    finish();
                }catch (Exception ex){

                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void ok(String string) {
        ExplicitIntent.getsInstance().navigateTo(this, LoginActivity.class);
        finish();
    }

    @Override
    public void cancel() {

    }
}
