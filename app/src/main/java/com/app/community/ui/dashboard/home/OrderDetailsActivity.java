package com.app.community.ui.dashboard.home;

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
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityOrderDetailsBinding;
import com.app.community.network.request.Feedback;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.request.dashboard.OrderDetailsRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.network.response.dashboard.OrderDetailData;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.chat.ChatActivity;
import com.app.community.ui.dashboard.home.adapter.OrderDetailsAdapter;
import com.app.community.ui.dashboard.home.adapter.OrderListAdapter;
import com.app.community.ui.dialogfragment.ContactDialogFragment;
import com.app.community.ui.dialogfragment.OrderFeedbackDialogFragment;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.REQUEST_CALL;

/**
 * Created by ashok on 13/11/17.
 */

public class OrderDetailsActivity extends CommonActivity
        implements OrderListAdapter.OrderListener,
        OrderFeedbackDialogFragment.OrderDialogListener,ContactDialogFragment.ContactDialogListener {

    private ActivityOrderDetailsBinding mBinding;
    private OrderDetailsAdapter mOrderAdapter;
    private Order order;
    private Intent callIntent;
    @Inject
    CommonPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        initializeData();
        setListener();
    }


    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.order_details));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mBinding.rvCartItem.setLayoutManager(manager);
        mOrderAdapter = new OrderDetailsAdapter(this);
        mBinding.rvCartItem.setAdapter(mOrderAdapter);
        Intent intent = getIntent();
        if (CommonUtils.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtils.isNotNull(bundle)) {
                order = bundle.getParcelable(GeneralConstant.ORDER_DETAILS);
                mBinding.tvOrderDate.setText(order.getInvoiceDate());
                mBinding.tvInvoice.setText(order.getInvoiceNumber());
                mBinding.tvOrderStatus.setText(order.getOrder_status());
                if (CommonUtils.isNotNull(order.getRating()) && (!order.getRating().equalsIgnoreCase("0"))) {
                    mBinding.tvFeedBack.setVisibility(View.GONE);
                    mBinding.ratingBar.setVisibility(View.VISIBLE);
                    mBinding.ratingBar.setRating(CommonUtils.setRating(order.getRating()));
                } else {
                    mBinding.tvFeedBack.setVisibility(View.VISIBLE);
                }
                OrderDetailsRequest request = new OrderDetailsRequest();
                request.setOrderid(order.getInvoiceNumber());
                presenter.orderDetails(this, request);
                MerchantRequest merchantRequest = new MerchantRequest(order.getMerchant_id());
                presenter.getMerchantDetails(this, merchantRequest);
            }
        }

    }

    public void setListener() {
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.tvFeedBack.setOnClickListener(this);
        mBinding.tvContact.setOnClickListener(this);
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response)) {
            if (requestCode == 1) {
                orderDetails(response);
            } else if (requestCode == 2) {
                merchantDetails(response);
            }
        }
    }

    private void merchantDetails(BaseResponse response) {
        MerchantResponseData data = (MerchantResponseData) response;
        ArrayList<MerchantResponse> merchantResponse = data.getInfo();
        if (CommonUtils.isNotNull(merchantResponse) && merchantResponse.size() > 0) {
            MerchantResponse merchant = merchantResponse.get(0);
            GlideUtils.loadImage(this, merchant.getImage(), mBinding.storeImage, null, R.drawable.icon_placeholder);
            mBinding.tvStoreName.setText(merchant.getName());
            mBinding.tvAddress.setText(merchant.getAddress());
        }
    }

    private void orderDetails(BaseResponse response) {
        OrderDetailData orderDetailData = (OrderDetailData) response;
        if (CommonUtils.isNotNull(orderDetailData.getOrderdetails()) && orderDetailData.getOrderdetails().size() > 0) {
            mOrderAdapter.setCartList(orderDetailData.getOrderdetails());

        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        } else if (view == mBinding.tvFeedBack) {
            feedBackClicked();
        }else if(view==mBinding.tvContact){
            contact();
        }
    }

    private void contact() {
        Bundle bundle = new Bundle();
        MerchantResponse merchantResponse=new MerchantResponse();
        merchantResponse.setId(String.valueOf(order.getMerchant_id()));
        //merchantResponse.setLogo();
        //merchantResponse.setAddress();
        //merchantResponse.setMobileNumber();
        //merchantResponse.setName();
        bundle.putParcelable(GeneralConstant.PRODUCT_INFO,merchantResponse);
        CommonUtils.showContactDialog(this, bundle, this);
    }


    private void feedBackClicked() {
        Bundle bundle = new Bundle();
        bundle.putInt(GeneralConstant.ID, order.getId());
        bundle.putString(GeneralConstant.STORE_NAME, order.getStore_name());
        CommonUtils.showOrderDialog(this, bundle, this);
    }

    @Override
    public void submit(int id, float rating, String feedbackStr) {
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setRating(String.valueOf(rating));
        mBinding.ratingBar.setRating(CommonUtils.setRating(String.valueOf(rating)));
        mBinding.tvFeedBack.setVisibility(View.GONE);
        mBinding.ratingBar.setVisibility(View.VISIBLE);
        feedback.setComments(feedbackStr);
        presenter.submitFeedBack(this, feedback);
    }

    @Override
    public void onOrderClick(int position) {

    }

    @Override
    public void contact(String phoneNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
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
                    showToast(getResources().getString(R.string.permition_denied));
                }
            }
        }
    }

    @Override
    public void messageChat(String name, String mobileNumber) {
        Bundle bundle = new Bundle();
        bundle.putString(GeneralConstant.CHAT_WITH, mobileNumber);
        bundle.putString(GeneralConstant.CHAT_USER_NAME, name);
        ExplicitIntent.getsInstance().navigateTo(this, ChatActivity.class, bundle);
    }
}
