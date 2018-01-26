package com.app.community.ui.dashboard.home.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentLocationMapBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.MarkerInfoWindowAdapter;
import com.app.community.ui.dashboard.home.event.MerchantEvent;
import com.app.community.ui.location.GPSTracker;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static com.app.community.utils.GeneralConstant.PERMISSIONS_REQUEST_LOCATION;


public class ProductMapFragment extends DashboardFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FragmentLocationMapBinding mBinder;
    private GPSTracker gpsTracker;
    private SupportMapFragment mapFragment;
    private HashMap<Marker, MerchantResponse> mMarkersHashMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_location_map, container, false);
        CommonUtils.register(this);
        if (CommonUtils.checkService(getBaseActivity())) {
            initMap();
        }
        return mBinder.getRoot();
    }

    private void initMap() {
        mMarkersHashMap = new HashMap();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getCurrentLocation();

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        } else {
            SmartLocation.with(getBaseActivity()).location()
                    .start(new OnLocationUpdatedListener() {
                        @Override
                        public void onLocationUpdated(Location location) {
                            moveCamera(new LatLng(location.getLatitude(), location.getLongitude()));
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });
    }

    @Subscribe
    public void onEvent(MerchantEvent event) {
        if (event.getListMap() == GeneralConstant.LIST_PRODUCT) {
            mBinder.layoutMap.setVisibility(View.GONE);
        } else if (event.getListMap() == GeneralConstant.MAP_PRODUCT) {
            mBinder.layoutMap.setVisibility(View.VISIBLE);
            showMarkerList(event.getProductList());
        }
    }

    private void showMarkerList(ArrayList<MerchantResponse> meetingEventList) {
        if (CommonUtils.isNotNull(meetingEventList)) {
            for (MerchantResponse response : meetingEventList) {
                addMarker(response);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (CommonUtils.isNull(googleMap))
            return;
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //Set Custom InfoWindow Adapter
        MarkerInfoWindowAdapter adapter = new MarkerInfoWindowAdapter(getBaseActivity(), mMarkersHashMap);
        mMap.setInfoWindowAdapter(adapter);
        checkPermition();
    }

    private void checkPermition() {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

        } else {
            mMap.setMyLocationEnabled(true);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermition();
                    getCurrentLocation();
                }
                break;
        }
    }

    private void addMarker(MerchantResponse response) {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (CommonUtils.isNotNull(mMap)&&CommonUtils.isNotNull(response)) {
            LatLng latLng = new LatLng(Double.parseDouble(response.getLatitude()), Double.parseDouble(response.getLongitude()));
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
            mMarkersHashMap.put(marker, response);
            if(CommonUtils.isNotNull(response)&&response.getId().equalsIgnoreCase("0")){
                ShowMarker(marker,response);
                marker.showInfoWindow();
            }
        }
    }

    private void ShowMarker(Marker marker, MerchantResponse response) {
        try {
            marker.showInfoWindow();
            Geocoder geocoder = new Geocoder(getBaseActivity(), Locale.getDefault());
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(response.getLatitude()), Double.parseDouble(response.getLongitude()), 1);
            String address = addresses.get(0).getAddressLine(0);
            if(CommonUtils.isNotNull(address)){
                if(address.length()>GeneralConstant.MAX_LENGTH) {
                    marker.setTitle(address.substring(0, GeneralConstant.MAX_LENGTH));
                }else {
                    marker.setTitle(address);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moveCamera(LatLng latLng) {
        if(CommonUtils.isNotNull(mMap)){
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng)
                    .bearing(GeneralConstant.BEARING)
                    .tilt(GeneralConstant.TILT)
                    .zoom(GeneralConstant.MAX_ZOOM)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return ProductMapFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(CommonUtils.isNotNull(mapFragment)){
            mapFragment.onLowMemory();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStart() {
        super.onStart();
        if(CommonUtils.isNotNull(mapFragment)){
            mapFragment.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(CommonUtils.isNotNull(mapFragment)){
            mapFragment.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(CommonUtils.isNotNull(mapFragment)){
            mapFragment.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(CommonUtils.isNotNull(mapFragment)){
            mapFragment.onStop();
        }
    }

}
