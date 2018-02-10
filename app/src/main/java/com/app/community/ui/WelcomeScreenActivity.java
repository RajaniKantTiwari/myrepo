package com.app.community.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityWelcomeScreenBinding;
import com.app.community.network.request.UpdateLocation;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.authentication.LoginActivity;
import com.app.community.ui.dashboard.home.event.UpdateAddress;
import com.app.community.ui.dialogfragment.CustomDialogFragment;
import com.app.community.ui.location.GPSTracker;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.PERMISSIONS_REQUEST_LOCATION;


/**
 * Created by arvind on 21/12/17.
 */

public class WelcomeScreenActivity extends CommonActivity implements CustomDialogFragment.CustomDialogListener {
    private ActivityWelcomeScreenBinding mBinding;

    private GPSTracker gpsTracker;
    private boolean isClickedOnAddress;
    private boolean isFromHome;

    @Inject
    CommonPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome_screen);
        initializeData();
        setListener();
    }

    private void initializeData() {
        Intent intent = getIntent();
        if (CommonUtils.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtils.isNotNull(bundle)) {
                isFromHome = bundle.getBoolean(GeneralConstant.IS_FROM_HOME);
            }
        }
        if (isFromHome) {
            mBinding.ivBack.setVisibility(View.VISIBLE);
        } else {
            mBinding.ivBack.setVisibility(View.GONE);
        }
    }

    public void setListener() {
        mBinding.layoutLocation.setOnClickListener(this);
        mBinding.tvManually.setOnClickListener(this);
        mBinding.ivBack.setOnClickListener(this);
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if(CommonUtils.isNotNull(response)){
            showToast(response.getMsg());
            EventBus.getDefault().post(new UpdateAddress());
        }
        finish();
    }

    @Override
    public void onError(String message, int requestCode) {
        super.onError(message, requestCode);
        finish();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutLocation) {
            CommonUtils.clicked(mBinding.layoutLocation);
            if (isNetworkConnected()) {
                getCurrentLocation();
            }
        } else if (view == mBinding.tvManually) {
            CommonUtils.clicked(mBinding.tvManually);
            if (isNetworkConnected()) {
                if (!isClickedOnAddress) {
                    isClickedOnAddress = true;
                    address();
                }
            }
        } else if (view == mBinding.ivBack) {
            finish();
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
        PreferenceUtils.setLatitude(gpsTracker.getLatitude());
        PreferenceUtils.setLongitude(gpsTracker.getLongitude());
        if (!isFromHome) {
            ExplicitIntent.getsInstance().navigateTo(this, LoginActivity.class);
            finish();
        } else {
            EventBus.getDefault().post(new UpdateAddress());
            presenter.updateLocation(this, new UpdateLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude()));
        }
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
            isClickedOnAddress = false;
            if (resultCode == RESULT_OK) {
                try {
                    if (CommonUtils.isNotNull(data)) {
                        // retrive the data by using getPlace() method.
                        Place place = PlaceAutocomplete.getPlace(this, data);
                        if (CommonUtils.isNotNull(place)) {
                            LatLng latLng = place.getLatLng();
                            PreferenceUtils.setAddress(place.getAddress().toString());
                            PreferenceUtils.setLatitude(latLng.latitude);
                            PreferenceUtils.setLongitude(latLng.longitude);
                        }

                    }
                    EventBus.getDefault().post(new UpdateAddress());
                    if (!isFromHome) {
                        ExplicitIntent.getsInstance().navigateTo(this, LoginActivity.class);
                        finish();
                    } else {
                        presenter.updateLocation(this, new UpdateLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude()));
                    }
                } catch (Exception ex) {
                    showToast(getResources().getString(R.string.something_went_wrong));
                    finish();
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
        EventBus.getDefault().post(new UpdateAddress());
        finish();
    }

    @Override
    public void cancel() {

    }
}
