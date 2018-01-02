package com.app.community.ui.dialogfragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.DialogfragmentOrderBinding;
import com.app.community.network.response.dashboard.meeting.ProductResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

public class OrderDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentOrderBinding mBinding;
    private OrderDialogListener listener;
    private String mobileNumber;
    private ProductResponse productInfo;

    public interface OrderDialogListener {
        void contact(String phoneNumber);
        void message(String message);
    }
    public void addListener(OrderDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialogfragment_order, container, false);
        dialog = getDialog();
        CommonUtils.setDialog(dialog);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        CommonUtils.setPadding(dialog,getActivity());
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
         productInfo=bundle.getParcelable(GeneralConstant.PRODUCT_INFO);
         if(CommonUtils.isNotNull(productInfo)){
             mBinding.tvName.setText(productInfo.getName());
             mBinding.ivAddress.setText(productInfo.getAddress());
             mobileNumber=productInfo.getPhoneno();

         }
        }
    }

    public void setListener() {
        mBinding.tvMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvMessage) {
            dialog.cancel();
            if(CommonUtils.isNotNull(listener)){
                listener.message(mobileNumber);
            }
        }
    }
}