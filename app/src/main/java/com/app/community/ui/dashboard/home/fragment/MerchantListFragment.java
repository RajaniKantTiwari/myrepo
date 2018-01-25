package com.app.community.ui.dashboard.home.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentLocationListBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.ProductDetailsFragment;
import com.app.community.ui.dashboard.home.adapter.MerchantAdapter;
import com.app.community.ui.dashboard.home.event.ProductEvent;
import com.app.community.ui.dialogfragment.ContactDialogFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static com.app.community.utils.GeneralConstant.REQUEST_CALL;

/**
 * Created by Amul on 27/12/17.
 */

public class MerchantListFragment extends DashboardFragment implements ContactDialogFragment.ContactDialogListener {
    private FragmentLocationListBinding mBinding;
    private MerchantAdapter mAdapter;
    private ArrayList<MerchantResponse> productList;
    private Intent callIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CommonUtils.register(this);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_location_list, container, false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        mAdapter = new MerchantAdapter(getBaseActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
        mBinding.rvProduct.setLayoutManager(layoutManager);
        mBinding.rvProduct.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClick(new MerchantAdapter.MerchantClickListener() {
            @Override
            public void onItemClick(int adapterPosition) {
                if (CommonUtils.isNotNull(productList) && productList.size() > adapterPosition) {
                    //mFragmentNavigation.pushFragment(ProductSubproductFragment.newInstance(mInt+1,productList.get(adapterPosition)));
                    mFragmentNavigation.pushFragment(ProductDetailsFragment.newInstance(mInt+1,productList.get(adapterPosition)));
                    //ExplicitIntent.getsInstance().navigateTo(getBaseActivity(), ProductDetailsFragment.class);
                }
            }

            @Override
            public void onContactClick(int adapterPosition) {
                openDialog(adapterPosition);
            }

            @Override
            public void onViewClick(int adapterPosition) {
                mFragmentNavigation.pushFragment(ProductDetailsFragment.newInstance(mInt+1, productList.get(adapterPosition)));
            }
        });
    }

    private void openDialog(int adapterPosition) {
        if (CommonUtils.isNotNull(productList) && productList.size() > adapterPosition) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(GeneralConstant.PRODUCT_INFO,productList.get(adapterPosition));
            CommonUtils.showContactDialog(getBaseActivity(), bundle, this);
        }
    }


    @Override
    public String getFragmentName() {
        return MerchantListFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(ProductEvent event) {
        if (event.getListMap() == GeneralConstant.LIST_PRODUCT) {
            mBinding.layoutList.setVisibility(View.VISIBLE);
            productList = event.getProductList();
            mAdapter.setLocationList(event.getProductList());
        } else if (event.getListMap() == GeneralConstant.MAP_PRODUCT) {
            mBinding.layoutList.setVisibility(View.GONE);

        }
    }

    @Override
    public void contact(String phoneNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getBaseActivity(),new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }


    @Override
    public void message(String message) {
            try {
                Uri uri = Uri.parse("smsto:"+message);
                // No permisison needed
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
                // Set the message to be sent
                //smsIntent.putExtra("sms_body", "SMS application launched from stackandroid.com example");
                startActivity(smsIntent);
            } catch (Exception e) {
                getBaseActivity().showToast(getResources().getString(R.string.message_failed));
                e.printStackTrace();
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);
                } else {
                    getBaseActivity().showToast(getResources().getString(R.string.permition_denied));
                }
            }
        }
    }
}
