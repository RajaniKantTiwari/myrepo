package com.app.community.ui.dialogfragment;

import android.app.ActionBar;
import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.app.community.R;
import com.app.community.databinding.DialogfragmentContactBinding;
import com.app.community.network.response.dashboard.meeting.ProductResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

public class ContactDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentContactBinding mBinding;
    private ContactDialogListener listener;
    private String mobileNumber;
    private ProductResponse productInfo;

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
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
         productInfo=bundle.getParcelable(GeneralConstant.PRODUCT_INFO);
         if(CommonUtils.isNotNull(productInfo)){
             GlideUtils.loadImage(getActivity(),productInfo.getLogo(),mBinding.ivImage,null,0);
             mBinding.ivName.setText(productInfo.getName());
             mBinding.ivAddress.setText(productInfo.getAddress());
             mobileNumber=productInfo.getPhoneno();

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