package com.app.community.ui.dialogfragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.DialogfragmentContactImpBinding;
import com.app.community.network.response.dashboard.home.Emergency;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

public class EmergencyDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentContactImpBinding mBinding;
    private EmergencyDialogListener listener;
    private String mobileNumber;
    private Emergency emergency;

    public interface EmergencyDialogListener {
        void contact(String phoneNumber);
        void view(String message);
    }
    public void addListener(EmergencyDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialogfragment_contact_imp, container, false);
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
            emergency=bundle.getParcelable(GeneralConstant.EMERGENCY);
         if(CommonUtils.isNotNull(emergency)){
             mobileNumber=emergency.getPhone_no();
          mBinding.setEmergency(emergency);
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
            listener.view(mobileNumber);
        }
    }
}