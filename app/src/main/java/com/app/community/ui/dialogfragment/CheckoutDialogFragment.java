package com.app.community.ui.dialogfragment;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.app.community.R;
import com.app.community.databinding.DialogfragmentBinding;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

public class CheckoutDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentBinding mBinding;
    private CheckoutDialogListener listener;
    private int parentPosition;
    private int childPosition;


    public interface CheckoutDialogListener {
        void ok(int parentPosition, int childPosition);
        void cancel();
    }

    public void DialogListener(CheckoutDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialogfragment, container, false);
        dialog = getDialog();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);
        initializeData();
        setListener();
        return mBinding.getRoot();
    }
    private void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            parentPosition=bundle.getInt(GeneralConstant.PARENT_POSITION);
            childPosition=bundle.getInt(GeneralConstant.CHILD_POSITION);
            mBinding.tvMessage.setText(bundle.getString(GeneralConstant.MESSAGE));

        }
    }

    public void setListener() {
        mBinding.tvCancel.setOnClickListener(this);
        mBinding.tvOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvOk) {
            dialog.cancel();
            listener.ok(parentPosition,childPosition);
        } else if (view == mBinding.tvCancel) {
            dialog.cancel();
            listener.cancel();
        }
    }
}