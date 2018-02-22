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
import com.app.community.mapview.InfoWindow;
import com.app.community.mapview.InfoWindowManager;
import com.app.community.mapview.ShowWindowFragment;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.event.MerchantEvent;
import com.app.community.ui.location.GPSTracker;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.PreferenceUtils;
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


public class ProductMapFragment extends DashboardFragment implements OnMapReadyCallback, ShowWindowFragment.MarkerInfoListener
        , GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private FragmentLocationMapBinding mBinder;
    private SupportMapFragment mapFragment;
    private InfoWindowManager infoWindowManager;
    private ArrayList<InfoWindow> infoWindowList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_location_map, container, false);
        CommonUtils.register(this);
        if (CommonUtils.checkService(getBaseActivity())) {
            initMap();
        }
        infoWindowManager = new InfoWindowManager(getDashboardActivity().getSupportFragmentManager());
        infoWindowManager.onParentViewCreated(mBinder.mapViewContainer, savedInstanceState);
        return mBinder.getRoot();
    }

    private void initMap() {
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
                            //moveCamera(new LatLng(location.getLatitude(), location.getLongitude()));
                            moveCamera(new LatLng(PreferenceUtils.getLatitude(), PreferenceUtils.getLongitude()));
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {
       /* mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });*/
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
       /* if (CommonUtils.isNotNull(meetingEventList)) {
            for (MerchantResponse response : meetingEventList) {*/
        addMarker(meetingEventList);
           /* }
        }*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (CommonUtils.isNull(googleMap))
            return;
        infoWindowManager.onMapReady(googleMap);
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //Set Custom InfoWindow Adapter
       /* MarkerInfoWindowAdapter adapter = new MarkerInfoWindowAdapter(getBaseActivity(), mMarkersHashMap);
        mMap.setInfoWindowAdapter(adapter);*/
        checkPermition();
    }

    private void checkPermition() {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

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

    private void addMarker(ArrayList<MerchantResponse> responseList) {
        if (ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (CommonUtils.isNotNull(mMap) && CommonUtils.isNotNull(responseList)) {
            infoWindowList = new ArrayList<>();
            for (int i = 0; i < responseList.size(); i++) {
                MerchantResponse response = responseList.get(i);
                LatLng latLng = new LatLng(Double.parseDouble(response.getLatitude()), Double.parseDouble(response.getLongitude()));
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng).snippet(String.valueOf(i))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pushpin)));
                showWindow(marker, response, i);
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        InfoWindow infoWindow = null;
        switch (marker.getSnippet()) {
            case "0":
                infoWindow = infoWindowList.get(0);
                break;
            case "1":
                infoWindow = infoWindowList.get(1);
                break;
            case "2":
                infoWindow = infoWindowList.get(2);
                break;
            case "3":
                infoWindow = infoWindowList.get(3);
                break;
            case "4":
                infoWindow = infoWindowList.get(4);
                break;
            case "5":
                infoWindow = infoWindowList.get(5);
                break;
            case "6":
                infoWindow = infoWindowList.get(6);
                break;
            case "7":
                infoWindow = infoWindowList.get(7);
                break;
            case "8":
                infoWindow = infoWindowList.get(8);
                break;
            case "9":
                infoWindow = infoWindowList.get(9);
                break;
            case "10":
                infoWindow = infoWindowList.get(10);
                break;
            case "11":
                infoWindow = infoWindowList.get(11);
                break;
            case "12":
                infoWindow = infoWindowList.get(12);
                break;
            case "13":
                infoWindow = infoWindowList.get(13);
                break;
            case "14":
                infoWindow = infoWindowList.get(14);
                break;
            case "15":
                infoWindow = infoWindowList.get(15);
                break;
            case "16":
                infoWindow = infoWindowList.get(16);
                break;
            case "17":
                infoWindow = infoWindowList.get(17);
                break;
            case "18":
                infoWindow = infoWindowList.get(18);
                break;
            case "19":
                infoWindow = infoWindowList.get(19);
                break;
            case "20":
                infoWindow = infoWindowList.get(20);
                break;
            case "21":
                infoWindow = infoWindowList.get(21);
                break;
            case "22":
                infoWindow = infoWindowList.get(22);
                break;
            case "23":
                infoWindow = infoWindowList.get(23);
                break;
            case "24":
                infoWindow = infoWindowList.get(24);
                break;
            case "25":
                infoWindow = infoWindowList.get(25);
                break;
            case "26":
                infoWindow = infoWindowList.get(26);
                break;
            case "27":
                infoWindow = infoWindowList.get(27);
                break;
            case "28":
                infoWindow = infoWindowList.get(28);
                break;
            case "29":
                infoWindow = infoWindowList.get(29);
                break;
            case "30":
                infoWindow = infoWindowList.get(30);
                break;
            case "31":
                infoWindow = infoWindowList.get(31);
                break;
            case "32":
                infoWindow = infoWindowList.get(32);
                break;
            case "33":
                infoWindow = infoWindowList.get(33);
                break;
            case "34":
                infoWindow = infoWindowList.get(34);
                break;
            case "35":
                infoWindow = infoWindowList.get(35);
                break;
            case "36":
                infoWindow = infoWindowList.get(36);
                break;
            case "37":
                infoWindow = infoWindowList.get(37);
                break;
            case "38":
                infoWindow = infoWindowList.get(38);
                break;
            case "39":
                infoWindow = infoWindowList.get(39);
                break;
            case "40":
                infoWindow = infoWindowList.get(40);
                break;
            case "41":
                infoWindow = infoWindowList.get(41);
                break;
            case "42":
                infoWindow = infoWindowList.get(42);
                break;
            case "43":
                infoWindow = infoWindowList.get(43);
                break;
            case "44":
                infoWindow = infoWindowList.get(44);
                break;
            case "45":
                infoWindow = infoWindowList.get(45);
                break;
            case "46":
                infoWindow = infoWindowList.get(46);
                break;
            case "47":
                infoWindow = infoWindowList.get(47);
                break;
            case "48":
                infoWindow = infoWindowList.get(48);
                break;
            case "49":
                infoWindow = infoWindowList.get(49);
                break;
            case "50":
                infoWindow = infoWindowList.get(50);
                break;
            case "51":
                infoWindow = infoWindowList.get(51);
                break;
            case "52":
                infoWindow = infoWindowList.get(52);
                break;
            case "53":
                infoWindow = infoWindowList.get(53);
                break;
            case "54":
                infoWindow = infoWindowList.get(54);
                break;
            case "55":
                infoWindow = infoWindowList.get(55);
                break;
            case "56":
                infoWindow = infoWindowList.get(56);
                break;
            case "57":
                infoWindow = infoWindowList.get(57);
                break;
            case "58":
                infoWindow = infoWindowList.get(58);
                break;
            case "59":
                infoWindow = infoWindowList.get(59);
                break;
            case "60":
                infoWindow = infoWindowList.get(60);
                break;
            case "61":
                infoWindow = infoWindowList.get(61);
                break;
            case "62":
                infoWindow = infoWindowList.get(62);
                break;
            case "63":
                infoWindow = infoWindowList.get(63);
                break;
            case "64":
                infoWindow = infoWindowList.get(64);
                break;
            case "65":
                infoWindow = infoWindowList.get(65);
                break;
            case "66":
                infoWindow = infoWindowList.get(66);
                break;
            case "67":
                infoWindow = infoWindowList.get(67);
                break;
            case "68":
                infoWindow = infoWindowList.get(68);
                break;
            case "69":
                infoWindow = infoWindowList.get(69);
                break;
            case "70":
                infoWindow = infoWindowList.get(70);
                break;
            case "71":
                infoWindow = infoWindowList.get(71);
                break;
            case "72":
                infoWindow = infoWindowList.get(72);
                break;
            case "73":
                infoWindow = infoWindowList.get(73);
                break;
            case "74":
                infoWindow = infoWindowList.get(74);
                break;
            case "75":
                infoWindow = infoWindowList.get(75);
                break;
            case "76":
                infoWindow = infoWindowList.get(76);
                break;
            case "77":
                infoWindow = infoWindowList.get(77);
                break;
            case "78":
                infoWindow = infoWindowList.get(78);
                break;
            case "79":
                infoWindow = infoWindowList.get(79);
                break;
            case "80":
                infoWindow = infoWindowList.get(80);
                break;
            case "81":
                infoWindow = infoWindowList.get(81);
                break;
            case "82":
                infoWindow = infoWindowList.get(82);
                break;
            case "83":
                infoWindow = infoWindowList.get(83);
                break;
            case "84":
                infoWindow = infoWindowList.get(84);
                break;
            case "85":
                infoWindow = infoWindowList.get(85);
                break;
            case "86":
                infoWindow = infoWindowList.get(86);
                break;
            case "87":
                infoWindow = infoWindowList.get(87);
                break;
            case "88":
                infoWindow = infoWindowList.get(88);
                break;
            case "89":
                infoWindow = infoWindowList.get(89);
                break;
            case "90":
                infoWindow = infoWindowList.get(90);
                break;
            case "91":
                infoWindow = infoWindowList.get(91);
                break;
            case "92":
                infoWindow = infoWindowList.get(92);
                break;
            case "93":
                infoWindow = infoWindowList.get(93);
                break;
            case "94":
                infoWindow = infoWindowList.get(94);
                break;
            case "95":
                infoWindow = infoWindowList.get(95);
                break;
            case "96":
                infoWindow = infoWindowList.get(96);
                break;
            case "97":
                infoWindow = infoWindowList.get(97);
                break;
            case "98":
                infoWindow = infoWindowList.get(98);
                break;
            case "99":
                infoWindow = infoWindowList.get(99);
                break;
            case "100":
                infoWindow = infoWindowList.get(100);
                break;

        }

        if (infoWindow != null) {
            infoWindowManager.toggle(infoWindow, true);
        }

        return true;
    }

    private void showWindow(Marker marker, MerchantResponse response, int positio) {
        ShowWindowFragment fragment = new ShowWindowFragment(this);
        Bundle bundle = new Bundle();
        bundle.putParcelable(GeneralConstant.MERCHANT_RESPONSE, response);
        fragment.setArguments(bundle);
        final int offsetX = (int) getResources().getDimension(R.dimen.marker_offset_x);
        final int offsetY = (int) getResources().getDimension(R.dimen.marker_offset_y);

        final InfoWindow.MarkerSpecification markerSpec =
                new InfoWindow.MarkerSpecification(offsetX, offsetY);
        infoWindowList.add(new InfoWindow(marker, markerSpec, fragment));
    }


    /*private void showMarker(Marker marker, MerchantResponse response) {
        try {
            marker.showInfoWindow();
            Geocoder geocoder = new Geocoder(getBaseActivity(), Locale.getDefault());
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(response.getLatitude()), Double.parseDouble(response.getLongitude()), 1);
            String address = addresses.get(0).getAddressLine(0);
            if (CommonUtils.isNotNull(address)) {
                if (address.length() > GeneralConstant.MAX_LENGTH) {
                    marker.setTitle(address.substring(0, GeneralConstant.MAX_LENGTH));
                } else {
                    marker.setTitle(address);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

   /* private void moveCamera(LatLng latLng) {
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
    }*/


    private void moveCamera(LatLng latLng) {
        if (CommonUtils.isNotNull(mMap)) {
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
        if (CommonUtils.isNotNull(mapFragment)) {
            mapFragment.onLowMemory();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        infoWindowManager.onDestroy();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (CommonUtils.isNotNull(mapFragment)) {
            mapFragment.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CommonUtils.isNotNull(mapFragment)) {
            mapFragment.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (CommonUtils.isNotNull(mapFragment)) {
            mapFragment.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (CommonUtils.isNotNull(mapFragment)) {
            mapFragment.onStop();
        }
    }

    @Override
    public void imageClick(String mobileNumber) {

    }
}
