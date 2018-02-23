package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentProductDetailBinding;
import com.app.community.network.request.dashboard.MerchantRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.home.MerchantResponseData;
import com.app.community.network.response.dashboard.home.ReviewResponse;
import com.app.community.network.response.dashboard.home.ReviewResponseData;
import com.app.community.network.response.dashboard.home.StoreImages;
import com.app.community.ui.SimpleDividerItemDecoration;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.DashboardInsidePresenter;
import com.app.community.ui.dashboard.home.adapter.ReviewAdapter;
import com.app.community.ui.dashboard.home.adapter.ImageAdapter;
import com.app.community.ui.dialogfragment.OrderFeedbackDialogFragment;
import com.app.community.ui.activity.ZoomAnimationImageActivity;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;
import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;
public class MerchantDetailsFragment extends DashboardFragment implements OrderFeedbackDialogFragment.OrderDialogListener,ImageAdapter.ImageListener {

    private FragmentProductDetailBinding mBinding;
    private ImageAdapter mImageAdapter;
    private ReviewAdapter mReviewAdapter;
    private ArrayList<ReviewResponse> reviewList;

    private MerchantResponse merchantResponse;
    @Inject
    DashboardInsidePresenter presenter;
    private ArrayList<StoreImages> imageList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
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
        imageList=new ArrayList<>();
        setList();
        mImageAdapter = new ImageAdapter(getDashboardActivity(),imageList,this);
        mBinding.photoRecycler.setAdapter(mImageAdapter);
        mReviewAdapter = new ReviewAdapter(getDashboardActivity(), reviewList);
        mBinding.rvReview.setAdapter(mReviewAdapter);
        if (CommonUtils.isNotNull(merchantResponse)) {
            presenter.getMerchantDetails(getDashboardActivity(), new MerchantRequest(Integer.parseInt(merchantResponse.getId())));
            presenter.getMerchantReviews(getDashboardActivity(), new MerchantRequest(Integer.parseInt("8")));
        }

    }

    private void setList() {
        try {
            StoreImages image = new StoreImages();
            image.setPath("https://api.androidhive.info/images/glide/small/deadpool.jpg");
            imageList.add(image);

            StoreImages image1 = new StoreImages();
            image1.setPath("https://api.androidhive.info/images/glide/large/bvs.jpg");
            imageList.add(image1);

            StoreImages image2 = new StoreImages();
            image2.setPath("https://api.androidhive.info/images/glide/large/cacw.jpg");
            imageList.add(image2);

            StoreImages image3 = new StoreImages();
            image3.setPath("https://api.androidhive.info/images/glide/large/bourne.jpg");
            imageList.add(image3);

            StoreImages image4 = new StoreImages();
            image4.setPath("https://api.androidhive.info/images/glide/large/squad.jpg");
            imageList.add(image4);

        } catch (Exception e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvStartShopping) {
            CommonUtils.clicked(mBinding.tvStartShopping);
            Bundle bundle=new Bundle();
            bundle.putString(AppConstants.MERCHANT_ID, merchantResponse.getId());
            bundle.putString(AppConstants.MERCHANT_ADDRESS,merchantResponse.getAddress());
            bundle.putString(AppConstants.MERCHANT_IMAGE,merchantResponse.getImage());
            bundle.putString(AppConstants.MERCHANT_BACKGROUND_COLOR,merchantResponse.getBackground_color());
            getDashboardActivity().addFragmentInContainer(new ProductSubproductFragment(),bundle,true,true, BaseActivity.AnimationType.NONE);
        } else if (view == mBinding.tvShareReview) {
            CommonUtils.clicked(mBinding.tvStartShopping);
            CommonUtils.showOrderDialog(getDashboardActivity(), null, this);
        }
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
                        CommonUtils.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,true);
                        if (CommonUtils.isNotNull(merchantResponse)) {
                            mBinding.setMerchantResponse(merchantResponse);
                            setImage(merchantResponse);
                        }else{
                            CommonUtils.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,false);
                        }
                    }
                }
                else{
                    CommonUtils.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,false);
                }
            }else{
                CommonUtils.setVisibility(mBinding.layoutMain,mBinding.layoutNoData.layoutNoData,false);
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
        if (CommonUtils.isNotNull(imageList)&&CommonUtils.isNotNull(merchantResponse.getStoreimages())) {
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
    public void submit(String submit) {
        getDashboardActivity().showToast("" + submit);
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(GeneralConstant.POSITION,position);
        bundle.putParcelableArrayList(GeneralConstant.IMAGE_LIST,imageList);
        ExplicitIntent.getsInstance().navigateToZoom(getDashboardActivity(), ZoomAnimationImageActivity.class,bundle);
        /*getDashboardActivity().pushFragment(ZOOMIMAGE_FRAGMENT,bundle,android.R.id.content,
                true,true, BaseActivity.AnimationType.ZOOM);*/
    }
}
