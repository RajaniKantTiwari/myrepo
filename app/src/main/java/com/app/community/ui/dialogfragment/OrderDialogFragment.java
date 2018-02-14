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
import com.app.community.databinding.DialogfragmentOrderBinding;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;
import com.app.community.widget.RatingBar;

public class OrderDialogFragment extends DialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private DialogfragmentOrderBinding mBinding;
    private OrderDialogListener listener;
    private MerchantResponse productInfo;
    private RatingBar ratingBar;

    public interface OrderDialogListener {
        void submit(String submit);
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
         }
        }

       /* ratingBar = new RatingBar(getContext());
        ratingBar.setFillDrawableRes(R.drawable.empty);
        ratingBar.setEmptyDrawableRes(R.drawable.fill);
        ratingBar.setSpace(0);
        ratingBar.setTouchRating(true);
        ratingBar.setClickRating(true);
        ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onChange(RatingBar view, int preCount, int curCount) {
                Log.i("GetRating", String.format("previous count:%d, current count:%d", preCount, curCount));
            }
        });*/
    }

    public void setListener() {
        mBinding.tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvSubmit) {
            dialog.cancel();
            if(CommonUtils.isNotNull(listener)){
                listener.submit(mBinding.edFeedBack.getText().toString());
                Log.e("GetRating",""+ratingBar.getCount());

            }
        }
    }
}