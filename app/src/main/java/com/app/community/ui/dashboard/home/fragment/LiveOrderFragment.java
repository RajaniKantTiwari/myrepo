package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentOrderBinding;
import com.app.community.network.request.Feedback;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.home.OrderDetailsFragment;
import com.app.community.ui.dashboard.home.adapter.LiveOrderAdapter;
import com.app.community.ui.dialogfragment.OrderFeedbackDialogFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;

import static com.app.community.utils.GeneralConstant.FRAGMENTS.ORDER_DETAILS_FRAGMENT;

/**
 * Created by rajnikant on 31/12/17.
 */

public class LiveOrderFragment extends BaseFragment implements
        LiveOrderAdapter.OrderListener, OrderFeedbackDialogFragment.OrderDialogListener {
    private FragmentOrderBinding mBinding;
    private LiveOrderAdapter mAdapter;
    private ArrayList<Order> recentOrderList;
    private MyOrderActivity mActivity;
    private String rating;
    private int position;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        mActivity = (MyOrderActivity) getActivity();
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        recentOrderList = new ArrayList<>();
        mAdapter = new LiveOrderAdapter(getBaseActivity(), this, recentOrderList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseActivity());
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
        if (CommonUtils.isNotNull(recentOrderList) && recentOrderList.size() > 0) {
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
        bundle.putString(GeneralConstant.ORDER_ID, String.valueOf(recentOrderList.get(position).getId()));
        ((MyOrderActivity) getBaseActivity()).addFragmentInContainer(new OrderDetailsFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void helpClick(int position) {

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
}
