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
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentMerchantDetailBinding;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ReviewResponse;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.StoreImages;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.activity.ZoomAnimationImageActivity;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.ImageAdapter;
import com.app.community.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.community.ui.dialogfragment.ContactDialogFragment;
import com.app.community.ui.dialogfragment.OrderFeedbackDialogFragment;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;
import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;
import static com.app.community.utils.GeneralConstant.REQUEST_CALL;

public class MerchantDetailsFragment extends DashboardFragment implements
        OrderFeedbackDialogFragment.OrderDialogListener,
        ImageAdapter.ImageListener,ContactDialogFragment.ContactDialogListener {

    private FragmentMerchantDetailBinding mBinding;
    private ImageAdapter mImageAdapter;
    private ReviewAdapter mReviewAdapter;
    private ArrayList<ReviewResponse> reviewList;
    private MerchantResponse merchantResponse;
    @Inject
    DashboardInsidePresenter presenter;
    private ArrayList<StoreImages> imageList;
    private Intent callIntent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_merchant_detail, container, false);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        LinearLayoutManager photoManager = new LinearLayoutManager(getDashboardActivity());
        photoManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.photoRecycler.setLayoutManager(photoManager);
        LinearLayoutManager reviewManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvReview.setLayoutManager(reviewManager);
        mBinding.rvReview.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
    }

    @Override
    public void attachView() {
        getActivityComponent().inject(this);
        presenter.attachView(this);
    }

    public void setListener() {
        mBinding.tvStartShopping.setOnClickListener(this);
        mBinding.tvShareReview.setOnClickListener(this);
        mBinding.tvContact.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return MerchantDetailsFragment.class.getSimpleName();
    }

    public void initializeData() {
        Bundle bundle = getArguments();
        if (CommonUtils.isNotNull(bundle)) {
            merchantResponse = bundle.getParcelable(GeneralConstant.RESPONSE);
        }
        reviewList = new ArrayList<>();
        imageList = new ArrayList<>();
        mImageAdapter = new ImageAdapter(getDashboardActivity(), imageList, this);
        mBinding.photoRecycler.setAdapter(mImageAdapter);
        mReviewAdapter = new ReviewAdapter(getDashboardActivity(), reviewList);
        mBinding.rvReview.setAdapter(mReviewAdapter);
        if (CommonUtils.isNotNull(merchantResponse)) {
            presenter.getMerchantDetails(getDashboardActivity(), new MerchantRequest(Integer.parseInt(merchantResponse.getId())));
            presenter.getMerchantReviews(getDashboardActivity(), new MerchantRequest(Integer.parseInt(merchantResponse.getId())));
        }

    }



    @Override
    public void onClick(View view) {
        if (view == mBinding.tvStartShopping) {
            CommonUtils.clicked(mBinding.tvStartShopping);
            Bundle bundle = new Bundle();
            bundle.putString(AppConstants.MERCHANT_ID, merchantResponse.getId());
            bundle.putString(AppConstants.MERCHANT_ADDRESS, merchantResponse.getAddress());
            bundle.putString(AppConstants.MERCHANT_IMAGE, merchantResponse.getImage());
            bundle.putString(AppConstants.MERCHANT_BACKGROUND_COLOR, merchantResponse.getBackground_color());
            getDashboardActivity().addFragmentInContainer(new ProductSubproductFragment(), bundle, true, true, BaseActivity.AnimationType.NONE);
        } else if (view == mBinding.tvShareReview) {
            CommonUtils.clicked(mBinding.tvStartShopping);
            CommonUtils.showOrderDialog(getDashboardActivity(), null, this);
        } else if (view == mBinding.tvContact) {
            openDialog();
        }
    }
    private void openDialog() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(GeneralConstant.PRODUCT_INFO,merchantResponse);
            CommonUtils.showContactDialog(getBaseActivity(), bundle, this);
    }
    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == 1) {
            if (CommonUtils.isNotNull(response) && response instanceof MerchantResponseData) {
                MerchantResponseData data = (MerchantResponseData) response;
                if (CommonUtils.isNotNull(data)) {
                    ArrayList<MerchantResponse> infoList = data.getInfo();
                    if (CommonUtils.isNotNull(infoList) && infoList.size() > 0) {
                        com.app.community.network.response.dashboard.home.MerchantResponse merchantResponse = infoList.get(0);
                        CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
                        if (CommonUtils.isNotNull(merchantResponse)) {
                            mBinding.setMerchantResponse(merchantResponse);
                            if (CommonUtils.twoDecimalPlaceString(merchantResponse.getDistance()) != null) {
                                mBinding.tvDistance.setText(CommonUtils.twoDecimalPlaceString(merchantResponse.getDistance())
                                        + " " + getResources().getString(R.string.km));
                            }
                            setImage(merchantResponse);
                        } else {
                            CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
                        }
                    }
                } else {
                    CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
                }
            } else {
                CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
            }
        } else if (requestCode == 2) {
            reviewList.clear();
            if (CommonUtils.isNotNull(response) && response instanceof ReviewResponseData) {
                ReviewResponseData data = (ReviewResponseData) response;
                ArrayList<ReviewResponse> responseArrayList = data.getInfo();
                if (CommonUtils.isNotNull(responseArrayList)) {
                    reviewList.addAll(responseArrayList);
                    mReviewAdapter.notifyDataSetChanged();
                }
            }
        }


    }

    private void setImage(MerchantResponse merchantResponse) {
        if (CommonUtils.isNotNull(merchantResponse.getRating())) {
            mBinding.ratingBar.setRating(CommonUtils.setRating(merchantResponse.getRating()));
        }
        GlideUtils.loadImage(getDashboardActivity(), merchantResponse.getImage(), mBinding.imageLogo, null, 0);
        GlideUtils.loadImage(getDashboardActivity(), merchantResponse.getBanner_image(), mBinding.storeImage, null, 0);
        if (CommonUtils.isNotNull(imageList) && CommonUtils.isNotNull(merchantResponse.getStoreimages())) {
            imageList.addAll(merchantResponse.getStoreimages());
            mImageAdapter.notifyDataSetChanged();
        }
    }

    public static Fragment newInstance(int instance, MerchantResponse productResponse) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        args.putParcelable(GeneralConstant.RESPONSE, productResponse);
        MerchantDetailsFragment fragment = new MerchantDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void submit(int id, float rating, String submit) {
        getDashboardActivity().showToast("" + submit);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(GeneralConstant.POSITION, position);
        bundle.putParcelableArrayList(GeneralConstant.IMAGE_LIST, imageList);
        ExplicitIntent.getsInstance().navigateToZoom(getDashboardActivity(), ZoomAnimationImageActivity.class, bundle);
        /*getDashboardActivity().pushFragment(ZOOMIMAGE_FRAGMENT,bundle,android.R.id.content,
                true,true, BaseActivity.AnimationType.ZOOM);*/
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
    public void message(String message) {
        try {
            Uri uri = Uri.parse("smsto:"+message);
            // No permisison needed
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
            // Set the message to be sent
            startActivity(smsIntent);
        } catch (Exception e) {
            getBaseActivity().showToast(getResources().getString(R.string.message_failed));
            e.printStackTrace();
        }
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
}
