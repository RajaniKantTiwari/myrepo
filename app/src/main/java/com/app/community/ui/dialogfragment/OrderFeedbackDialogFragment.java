package com.app.community.ui.dialogfragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.DialogFeedbackBinding;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

public class OrderFeedbackDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogFeedbackBinding mBinding;
    private OrderDialogListener listener;
    private int id;
    private String storeName;

    public interface OrderDialogListener {
        void submit(int id, float rating, String feedback);
    }
    public void addListener(OrderDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_feedback, container, false);
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
            id=bundle.getInt(GeneralConstant.ID);
            storeName=bundle.getString(GeneralConstant.STORE_NAME);
            mBinding.tvName.setText(storeName);
        }
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            dialog.cancel();
            if(CommonUtils.isNotNull(listener)){
                listener.submit(id,mBinding.ratingBar.getRating(),mBinding.edFeedBack.getText().toString());
            }
        }
    }
}