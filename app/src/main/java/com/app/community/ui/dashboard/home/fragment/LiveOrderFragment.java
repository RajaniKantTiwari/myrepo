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
import com.app.community.databinding.FragmentLiveOrderBinding;
import com.app.community.network.request.Feedback;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.chat.ChatActivity;
import com.app.community.ui.dashboard.home.OrderDetailsActivity;
import com.app.community.ui.dashboard.home.adapter.LiveOrderAdapter;
import com.app.community.ui.dialogfragment.ContactDialogFragment;
import com.app.community.ui.dialogfragment.OrderFeedbackDialogFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;

import static com.app.community.utils.GeneralConstant.REQUEST_CALL;

/**
 * Created by rajnikant on 31/12/17.
 */

public class LiveOrderFragment extends BaseFragment implements
        LiveOrderAdapter.OrderListener,
        OrderFeedbackDialogFragment.OrderDialogListener,ContactDialogFragment.ContactDialogListener {
    private FragmentLiveOrderBinding mBinding;
    private Intent callIntent;
    private LiveOrderAdapter mAdapter;
    private ArrayList<Order> recentOrderList;
    private MyOrderActivity mActivity;
    private String rating;
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_live_order, container, false);
        mActivity = (MyOrderActivity) getActivity();
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        recentOrderList = new ArrayList<>();
        mAdapter = new LiveOrderAdapter(getContext(), this, recentOrderList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvOrder.setLayoutManager(layoutManager);
        mBinding.rvOrder.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {
        mActivity.getPresenter().attachView(this);

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == 1) {
                Order order = recentOrderList.get(position);
                order.setRating(rating);
                recentOrderList.set(position,order);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void setLiveOrder(ArrayList<Order> recentOrderList) {
        this.recentOrderList.clear();
        if (CommonUtils.isNotNull(recentOrderList) && recentOrderList.size() > 0) {
            this.recentOrderList.addAll(recentOrderList);
        }

        if (CommonUtils.isNotNull(this.recentOrderList) && this.recentOrderList.size() > 0) {
            mBinding.rvOrder.setVisibility(View.VISIBLE);
            mBinding.layoutNoData.layoutNoData.setVisibility(View.GONE);
        } else {
            mBinding.rvOrder.setVisibility(View.GONE);
            mBinding.layoutNoData.layoutNoData.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void setVisibility(int visibility) {
        mBinding.layoutOrder.setVisibility(visibility);
    }

    @Override
    public void viewDetailsClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(GeneralConstant.ORDER_DETAILS, recentOrderList.get(position));
        ExplicitIntent.getsInstance().navigateTo(getActivity(),OrderDetailsActivity.class,bundle);
    }

    @Override
    public void helpClick(int position) {
        openDialog(position);
    }

    @Override
    public void feedBackClicked(int position) {
        this.position=position;
        Bundle bundle = new Bundle();
        if (CommonUtils.isNotNull(recentOrderList) && recentOrderList.size() > position) {
            Order order = recentOrderList.get(position);
            bundle.putInt(GeneralConstant.ID, order.getId());
            bundle.putString(GeneralConstant.STORE_NAME, order.getStore_name());
        }
        CommonUtils.showOrderDialog(mActivity, bundle, this);
    }

    @Override
    public void submit(int id, float rating, String feedbackStr) {
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setRating(String.valueOf(rating));
        this.rating=String.valueOf(rating);
        feedback.setComments(feedbackStr);
        mActivity.getPresenter().submitFeedBack(mActivity, feedback);
    }
    private void openDialog(int position) {
        Bundle bundle = new Bundle();
        MerchantResponse merchantResponse=new MerchantResponse();
        merchantResponse.setId(String.valueOf(recentOrderList.get(position).getMerchant_id()));
        //merchantResponse.setLogo();
        //merchantResponse.setAddress();
        //merchantResponse.setMobileNumber();
        //merchantResponse.setName();
        bundle.putParcelable(GeneralConstant.PRODUCT_INFO,merchantResponse);
        CommonUtils.showContactDialog(getBaseActivity(), bundle, this);
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

    @Override
    public void messageChat(String name, String mobileNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(GeneralConstant.CHAT_WITH, mobileNumber);
        bundle.putString(GeneralConstant.CHAT_USER_NAME, name);
        ExplicitIntent.getsInstance().navigateTo(mActivity, ChatActivity.class, bundle);
    }
}
