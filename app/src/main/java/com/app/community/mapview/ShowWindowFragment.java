package com.app.community.mapview;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.ProductDialogBinding;
import com.app.community.network.response.dashboard.dashboardinside.ProductResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;


@SuppressLint("ValidFragment")
public class ShowWindowFragment extends Fragment implements View.OnClickListener {

    private MarkerInfoListener listener;
    private ProductDialogBinding mBinding;
    private FragmentActivity mActivity;
    private MerchantResponse response;
    @SuppressLint("ValidFragment")
    public ShowWindowFragment(MarkerInfoListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.product_dialog, container, false);
        mActivity=getActivity();
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeData();
        setListener();
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if(CommonUtils.isNotNull(bundle)){

            response=bundle.getParcelable(GeneralConstant.MERCHANT_RESPONSE);
            if (CommonUtils.isNotNull(response)) {
                GlideUtils.loadImage(mActivity, response.getImage(), mBinding.ivProduct, null, R.drawable.icon_placeholder);
                mBinding.setMerchantResponse(response);
                mBinding.tvTitle.setText(response.getName());
                mBinding.tvAddress.setText(response.getAddress());
                mBinding.tvType.setText(response.getCategory());
                mBinding.tvDistance.setText(CommonUtils.twoDecimalPlaceString(response.getDistance())+mActivity.getResources().getString(R.string.km));
            }
        }
    }

    private void setListener() {
       mBinding.ivProduct.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (mBinding.ivProduct == view) {
            //call();
        }
    }


    public interface MarkerInfoListener {
        void imageClick(String mobileNumber);
    }
}
