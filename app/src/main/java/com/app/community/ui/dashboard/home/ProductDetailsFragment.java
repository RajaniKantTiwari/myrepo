package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentProductDetailBinding;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.meeting.ProductResponse;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.community.ui.dashboard.home.adapter.StoreAdapter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.GeneralConstant;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class ProductDetailsFragment extends DashboardFragment {

    private FragmentProductDetailBinding mBinding;
    private StoreAdapter mPhotoAdapter;
    private ReviewAdapter mReviewAdapter;
    private ProductResponse productResponse;
    @Inject
    DashboardInsidePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail,container,false);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager photoManager=new LinearLayoutManager(getDashboardActivity());
        photoManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.photoRecycler.setLayoutManager(photoManager);
        LinearLayoutManager reviewManager=new LinearLayoutManager(getDashboardActivity());
        mBinding.rvReview.setLayoutManager(reviewManager);
        mBinding.rvReview.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return ProductDetailsFragment.class.getSimpleName();
    }

    public void initializeData() {
        Bundle bundle=getArguments();
        if(CommonUtils.isNotNull(bundle)){
            productResponse=bundle.getParcelable(GeneralConstant.RESPONSE);
        }
        mPhotoAdapter =new StoreAdapter(getDashboardActivity());
        mBinding.photoRecycler.setAdapter(mPhotoAdapter);
        mReviewAdapter=new ReviewAdapter(getDashboardActivity());
        mBinding.rvReview.setAdapter(mReviewAdapter);
        if(CommonUtils.isNotNull(productResponse)){
            presenter.getMerchantDetails(getDashboardActivity(),new MerchantRequest(productResponse.getId()));
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public static Fragment newInstance(int instance, ProductResponse productResponse) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        args.putParcelable(GeneralConstant.RESPONSE,productResponse);
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
