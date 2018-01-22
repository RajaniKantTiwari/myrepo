package com.app.community.ui.dialogfragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.DialogfragmentContactBinding;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

public class ContactDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentContactBinding mBinding;
    private ContactDialogListener listener;
    private String mobileNumber;
    private MerchantResponse productInfo;

    public interface ContactDialogListener {
        void contact(String phoneNumber);
        void message(String message);
    }
    public void addListener(ContactDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialogfragment_contact, container, false);
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
             GlideUtils.loadImage(getActivity(),productInfo.getLogo(),mBinding.ivImage,null,0);
             mBinding.ivName.setText(productInfo.getName());
             mBinding.ivAddress.setText(productInfo.getAddress());
             //mobileNumber=productInfo.getPhoneno();

         }
        }
    }

    public void setListener() {
        mBinding.tvCall.setOnClickListener(this);
        mBinding.tvMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvCall) {
            dialog.cancel();
            listener.contact(mobileNumber);
        } else if (view == mBinding.tvMessage) {
            dialog.cancel();
            listener.message(mobileNumber);
        }
    }
}