package com.app.community.ui.dashboard.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentWelcomehomeBinding;
import com.app.community.databinding.LayoutImpPlaceBinding;
import com.app.community.databinding.LayoutLastOrderBinding;
import com.app.community.databinding.LayoutLatestNewsBinding;
import com.app.community.databinding.LayoutNewsBinding;
import com.app.community.databinding.LayoutOfferBinding;
import com.app.community.databinding.LayoutWelcomeSearchBinding;
import com.app.community.event.RightDrawerEvent;
import com.app.community.network.request.Feedback;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.Order;
import com.app.community.network.response.dashboard.home.Banner;
import com.app.community.network.response.dashboard.home.Emergency;
import com.app.community.network.response.dashboard.home.LastOrder;
import com.app.community.network.response.dashboard.home.News;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.network.response.dashboard.home.WelcomeHomeData;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.EmergencyAdapter;
import com.app.community.ui.dashboard.home.adapter.LatestNewsAdapter;
import com.app.community.ui.dashboard.home.adapter.NewsAdapter;
import com.app.community.ui.dashboard.home.adapter.OffersAdapter;
import com.app.community.ui.dashboard.home.event.UpdateAddress;
import com.app.community.ui.dashboard.home.fragment.MyOrderActivity;
import com.app.community.ui.dashboard.home.fragment.NewsMainFragment;
import com.app.community.ui.dashboard.offer.OfferDetailsActivity;
import com.app.community.ui.dialogfragment.EmergencyDialogFragment;
import com.app.community.ui.dialogfragment.OrderFeedbackDialogFragment;
import com.app.community.utils.AddWelcomeChildView;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;
import static com.app.community.utils.GeneralConstant.REQUEST_CALL;


/**
 * Created by atul on 22/09/17.
 * To inject activity reference.
 */

public class WelcomeHomeFragment extends DashboardFragment implements NewsAdapter.NewsListener,
        LatestNewsAdapter.LatestNewsListener, EmergencyAdapter.EmergencyListener,
        EmergencyDialogFragment.EmergencyDialogListener,
        OrderFeedbackDialogFragment.OrderDialogListener,
        OffersAdapter.OffersListener {

    private FragmentWelcomehomeBinding mBinding;
    private EmergencyAdapter mEmergencyAdapter;
    private LatestNewsAdapter mLatestNewsAdapter;
    private LayoutWelcomeSearchBinding mWelcomeBinding;
    private LayoutNewsBinding mNewsViewBinding;
    private LayoutOfferBinding mOfferBinding;
    private LayoutLastOrderBinding mLastOrderBinding;
    private LayoutImpPlaceBinding mEmergencyPlaceBinding;
    private LayoutLatestNewsBinding mLatestBinding;
    private NewsAdapter mNewsAdapter;
    private OffersAdapter mOfferAdapter;
    private Intent callIntent;
    private ArrayList<Emergency> emergencyList;
    private ArrayList<News> newsList;
    private ArrayList<Offer> offersList;
    private LinearLayoutManager emergencyPlaceManager;
    private ArrayList<LastOrder> lastOrdersList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcomehome, container, false);
        CommonUtils.register(this);
        getDashboardActivity().setHeaderTitle(getString(R.string.home));
        mWelcomeBinding = AddWelcomeChildView.addWelcomeSearchView(inflater, mBinding);
        mEmergencyPlaceBinding = AddWelcomeChildView.addImportantPlace(inflater, mBinding);
        mLastOrderBinding = AddWelcomeChildView.addLastOrderView(inflater, mBinding);
        mNewsViewBinding = AddWelcomeChildView.addNewsView(inflater, mBinding);
        mOfferBinding = AddWelcomeChildView.addOfferView(inflater, mBinding);
        mLatestBinding = AddWelcomeChildView.addLatestNewsView(inflater, mBinding);
        initializeView();
        return mBinding.getRoot();
    }

    private void initializeView() {
        emergencyList = new ArrayList<>();
        newsList = new ArrayList<>();
        offersList = new ArrayList<>();
        emergencyPlaceManager = new LinearLayoutManager(getContext());
        emergencyPlaceManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mEmergencyPlaceBinding.rvImportantPlace.setLayoutManager(emergencyPlaceManager);
        mEmergencyAdapter = new EmergencyAdapter(getBaseActivity(), emergencyList, this);
        mEmergencyPlaceBinding.rvImportantPlace.setAdapter(mEmergencyAdapter);
        LinearLayoutManager latestNewsManager = new LinearLayoutManager(getBaseActivity());
        mLatestBinding.rvLatestNews.setLayoutManager(latestNewsManager);
        mLatestNewsAdapter = new LatestNewsAdapter(getBaseActivity(), this);
        mLatestBinding.rvLatestNews.setAdapter(mLatestNewsAdapter);
        LinearLayoutManager newsManager = new LinearLayoutManager(getBaseActivity());
        mNewsViewBinding.rvNews.setLayoutManager(newsManager);
        mNewsViewBinding.rvNews.setNestedScrollingEnabled(false);
        mNewsAdapter = new NewsAdapter(getBaseActivity(), newsList, this);
        mNewsViewBinding.rvNews.setAdapter(mNewsAdapter);

        LinearLayoutManager offerManager = new LinearLayoutManager(getBaseActivity());
        mOfferBinding.rvOffer.setLayoutManager(offerManager);
        mOfferBinding.rvOffer.setNestedScrollingEnabled(false);
        mOfferAdapter = new OffersAdapter(getBaseActivity(), offersList, this);
        mOfferBinding.rvOffer.setAdapter(mOfferAdapter);

    }


    @Override
    public void attachView() {
        if (CommonUtils.isNotNull(getActivityComponent())) {
            getPresenter().attachView(this);
        }
    }

    @Override
    public void setListener() {
        mWelcomeBinding.tvSearch.setOnClickListener(this);
        mLastOrderBinding.layoutLastOrder.setOnClickListener(this);
        mLastOrderBinding.rating.setOnClickListener(this);
        mEmergencyPlaceBinding.ivLeft.setOnClickListener(this);
        mEmergencyPlaceBinding.ivRight.setOnClickListener(this);

        mEmergencyPlaceBinding.rvImportantPlace.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dx > 0) {
                    System.out.println("Scrolled Right");
                    checkIfItsLastItem();
                    checkIfItsFirstItem();
                } else if (dx < 0) {
                    checkIfItsFirstItem();
                    checkIfItsLastItem();
                    System.out.println("Scrolled Left");
                } else {
                    System.out.println("No Horizontal Scrolled");
                }
            }
        });
    }

    /**
     * is used to check list has scroll to first position or not
     */
    private void checkIfItsFirstItem() {
        int firstVisibleItemPosition = emergencyPlaceManager.findFirstCompletelyVisibleItemPosition();
        if (firstVisibleItemPosition > 0) {
            mEmergencyPlaceBinding.ivLeft.setVisibility(View.VISIBLE);
        } else {
            mEmergencyPlaceBinding.ivLeft.setVisibility(View.GONE);
        }
    }

    /**
     * is used to check list has scroll to last position or not
     */
    private void checkIfItsLastItem() {
        int visibleItemCount = emergencyPlaceManager.getChildCount();
        int totalItemCount = emergencyPlaceManager.getItemCount();
        int pastVisibleItems = emergencyPlaceManager.findFirstVisibleItemPosition();
        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
            mEmergencyPlaceBinding.ivRight.setVisibility(View.GONE);
        } else if (CommonUtils.isNotNull(emergencyList) && emergencyList.size() > 5) {
            mEmergencyPlaceBinding.ivRight.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public String getFragmentName() {
        return WelcomeHomeFragment.class.getSimpleName();
    }

    @Override
    public void initializeData() {
        getPresenter().getWelcomeHomePage(getDashboardActivity());
        EventBus.getDefault().post(new RightDrawerEvent());
    }

    @Override
    public void onClick(View view) {
        if (view == mLastOrderBinding.layoutLastOrder) {
            Bundle bundle=new Bundle();
            if(CommonUtils.isNotNull(lastOrdersList)&&lastOrdersList.size()>0){
                bundle.putInt(GeneralConstant.ID,lastOrdersList.get(0).getId());
                bundle.putString(GeneralConstant.STORE_NAME,lastOrdersList.get(0).getProductname());
            }
            CommonUtils.showOrderDialog(getDashboardActivity(), bundle, this);
            //mFragmentNavigation.pushFragment(ConfirmOrderFragment.newInstance(mInt+1));
        } else if (view == mWelcomeBinding.tvSearch) {
            ExplicitIntent.getsInstance().navigateTo(getActivity(), SearchActivity.class);
        } else if (view == mLastOrderBinding.rating) {
            ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), MyOrderActivity.class);
            //mFragmentNavigation.pushFragment(MyOrderActivity.newInstance(mInt + 1));
        } else if (view == mEmergencyPlaceBinding.ivLeft) {
            int firstVisibleItemPosition = emergencyPlaceManager.findFirstVisibleItemPosition();
            if(firstVisibleItemPosition>3){
                mEmergencyPlaceBinding.rvImportantPlace.smoothScrollToPosition(firstVisibleItemPosition-3);
            }
            else{
                mEmergencyPlaceBinding.rvImportantPlace.smoothScrollToPosition(0);
            }
        } else if (view == mEmergencyPlaceBinding.ivRight) {
            int lastVisibleItemPosition = emergencyPlaceManager.findLastVisibleItemPosition();
            if(lastVisibleItemPosition+3<emergencyList.size()){
                mEmergencyPlaceBinding.rvImportantPlace.smoothScrollToPosition(lastVisibleItemPosition+3);
            }
            else{
                mEmergencyPlaceBinding.rvImportantPlace.smoothScrollToPosition(emergencyList.size());
            }
        }
    }


    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (CommonUtils.isNotNull(response) && response instanceof WelcomeHomeData) {
            CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, true);
            WelcomeHomeData welcomeHomeData = (WelcomeHomeData) response;
            setResponseData(welcomeHomeData);
        } else if(requestCode==GeneralConstant.FEEDBACK){
            if(CommonUtils.isNotNull(response)){
                getDashboardActivity().showToast(response.getMsg());
            }
        } else {
            CommonUtils.setVisibility(mBinding.layoutMain, mBinding.layoutNoData.layoutNoData, false);
        }
    }

    private void setResponseData(WelcomeHomeData welcomeHomeData) {
        ArrayList<Banner> bannerList = welcomeHomeData.getBanner();
        ArrayList<News> newsList = welcomeHomeData.getNews();
        ArrayList<Offer> offersList = welcomeHomeData.getOffer();
        ArrayList<Emergency> emergencyList = welcomeHomeData.getEmergency();
        lastOrdersList = welcomeHomeData.getOrderreview();
        setBanner(bannerList);
        setNews(newsList);
        setOffer(offersList);
        setEmergency(emergencyList);
        setLastOrder(lastOrdersList);
    }

    private void setLastOrder(ArrayList<LastOrder> lastOrdersList) {
        if (CommonUtils.isNotNull(lastOrdersList) && lastOrdersList.size() > 0) {
            mLastOrderBinding.getRoot().setVisibility(View.VISIBLE);
            LastOrder order = lastOrdersList.get(0);
            if (CommonUtils.isNotNull(order)) {
                mLastOrderBinding.tvProductName.setText(order.getProductname());
                mLastOrderBinding.tvDetails.setText(order.getMeasure());
                GlideUtils.loadImage(getDashboardActivity(), order.getIcon(), mLastOrderBinding.ivLastOrder, null, R.drawable.stroke_grey);
                if (CommonUtils.isNotNull(order.getRating())) {
                    mLastOrderBinding.rating.setRating(Float.parseFloat(CommonUtils.oneDecimalPlaceString(order.getRating())));
                }
            }
        } else {
            mLastOrderBinding.getRoot().setVisibility(View.GONE);
        }
    }

    private void setEmergency(ArrayList<Emergency> emergencyList) {
        if (CommonUtils.isNotNull(emergencyList) && emergencyList.size() > 0) {
            mEmergencyPlaceBinding.getRoot().setVisibility(View.VISIBLE);
            this.emergencyList.clear();
            this.emergencyList.addAll(emergencyList);
            if (CommonUtils.isNotNull(emergencyList) && emergencyList.size() > 5) {
                mEmergencyPlaceBinding.ivRight.setVisibility(View.VISIBLE);
            }
            mEmergencyAdapter.notifyDataSetChanged();
        } else {
            mEmergencyPlaceBinding.getRoot().setVisibility(View.GONE);
        }
    }

    private void setOffer(ArrayList<Offer> offersList) {
        if (CommonUtils.isNotNull(offersList) && offersList.size() > 0) {
            mOfferBinding.getRoot().setVisibility(View.VISIBLE);
            this.offersList.clear();
            this.offersList.addAll(offersList);
            CommonUtils.setRecyclerViewHeight(mOfferBinding.rvOffer, offersList, GeneralConstant.OFFER_HEIGHT);
            mOfferAdapter.notifyDataSetChanged();
        } else {
            mOfferBinding.getRoot().setVisibility(View.GONE);
        }
    }

    private void setNews(ArrayList<News> newsList) {
        if (CommonUtils.isNotNull(newsList) && newsList.size() > 0) {
            mNewsViewBinding.getRoot().setVisibility(View.VISIBLE);
            this.newsList.clear();
            this.newsList.addAll(newsList);
            CommonUtils.setRecyclerViewHeight(mNewsViewBinding.rvNews, newsList, GeneralConstant.NEWS_HEIGHT);
            mNewsAdapter.notifyDataSetChanged();
        } else {
            mNewsViewBinding.getRoot().setVisibility(View.GONE);
        }
    }

    private void setBanner(ArrayList<Banner> bannerList) {
        if (CommonUtils.isNotNull(bannerList) && bannerList.size() > 0) {
            mWelcomeBinding.getRoot().setVisibility(View.VISIBLE);
            Banner banner = bannerList.get(0);
            if (CommonUtils.isNotNull(banner)) {
                mWelcomeBinding.setBanner(banner);
                GlideUtils.loadImage(getDashboardActivity(), banner.getBannerimage(), mWelcomeBinding.ivBanner, null, R.drawable.background_placeholder);
            }
        } else {
            mWelcomeBinding.getRoot().setVisibility(View.GONE);
        }
    }


    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        WelcomeHomeFragment fragment = new WelcomeHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void newsItemClick(int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(GeneralConstant.NEWSLIST, newsList);
                bundle.putInt(GeneralConstant.POSITION, position);
                getDashboardActivity().unselectAllTab();
                getDashboardActivity().addFragmentInContainer(new NewsMainFragment(), bundle, true, true, NONE);
            }
        }, GeneralConstant.DELAYTIME);

    }

    @Override
    public void onItemClick(int adapterPosition) {
        Bundle bundle = new Bundle();
        getDashboardActivity().addFragmentInContainer(new NewsMainFragment(), bundle, true, false, NONE);
    }

    @Override
    public void emergencyClicked(int position) {
        if (CommonUtils.isNotNull(emergencyList) && emergencyList.size() > position) {
            Emergency emergency = emergencyList.get(position);
            Bundle bundle = new Bundle();
            bundle.putParcelable(GeneralConstant.EMERGENCY, emergency);
            CommonUtils.showContactImpDialog(getBaseActivity(), bundle, this);
        }
    }

    @Override
    public void contact(String phoneNumber) {
        callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            return;
        } else
            startActivity(callIntent);
    }


    @Override
    public void view(String message) {
        getDashboardActivity().addFragmentInContainer(new MerchantDetailsFragment(), null, true, true, NONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            getBaseActivity().showToast(getResources().getString(R.string.permition_denied));
        }
    }

    @Override
    public void submit(int id, float rating, String feedbackStr) {
        Feedback feedback=new Feedback();
        feedback.setId(id);
        feedback.setRating(String.valueOf(rating));
        feedback.setComments(feedbackStr);
        getPresenter().submitFeedBack(getDashboardActivity(),feedback);
    }

    @Override
    public void offersItemClick(int position) {
        if (CommonUtils.isNotNull(offersList) && offersList.size() > position) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.OFFER, offersList.get(position));
            ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), OfferDetailsActivity.class, bundle);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);
    }

    @Subscribe
    public void onAddressEvent(UpdateAddress event) {
        getPresenter().getWelcomeHomePage(getDashboardActivity());
    }

}
