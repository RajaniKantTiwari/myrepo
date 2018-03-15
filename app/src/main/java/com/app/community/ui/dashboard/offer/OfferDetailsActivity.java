package com.app.community.ui.dashboard.offer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.databinding.ActivityOfferDetailsBinding;
import com.app.community.databinding.ItemTabViewBinding;
import com.app.community.event.StartShoppingEvent;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.home.Offer;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.DashboardInsideActivity;
import com.app.community.ui.dashboard.offer.adapter.OfferDetailsAdapter;
import com.app.community.ui.dashboard.offer.adapter.OfferPagerAdapter;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;

/**
 * Created by ashok on 13/11/17.
 */

public class OfferDetailsActivity extends DashboardInsideActivity implements ViewPager.OnPageChangeListener {

    @Inject
    CommonPresenter presenter;
    private ActivityOfferDetailsBinding mBinding;
    private Offer offer;
    private OfferPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_offer_details);
        setUpPagerView();
        setUpTabView();
        initializeData();
        setListener();
    }

    private void setUpPagerView() {
        CommonUtils.changeWidthOfTab(mBinding.tabLayout, this);
        mPagerAdapter = new OfferPagerAdapter(getSupportFragmentManager());
        mBinding.viewPager.setAdapter(mPagerAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
        mBinding.viewPager.setCurrentItem(AppConstants.ITEM_START_INDEX);
        mBinding.viewPager.setOffscreenPageLimit(2);
        mBinding.viewPager.addOnPageChangeListener(this);
    }

    private void setUpTabView() {
        Integer[] tabTitle = {R.string.offer_details, R.string.term_conditions};
        for (int index = 0; index < 2; index++) {
            ItemTabViewBinding viewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_tab_view, null, false);
            TabLayout.Tab tab = mBinding.tabLayout.getTabAt(index);
            if (tab != null) {
                tab.setCustomView(viewBinding.getRoot());
                viewBinding.setTitle(getString(tabTitle[index]));
                if (index != 0)
                    continue;
                View customView = tab.getCustomView();
                if (customView != null)
                    ((TextView) customView.findViewById(R.id.tvAdsText)).setTextColor(ContextCompat.getColor(this, R.color.tab_sel_txt_clr));
            }
        }
    }

    private void setListener() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.offer));
        mBinding.layoutHeader.headerLayout.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black));
        mBinding.layoutHeader.ivBack.setImageResource(R.drawable.ic_back_white);
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
        mBinding.tvStartShopping.setOnClickListener(this);
    }


    public void initializeData() {
        Intent intent = getIntent();
        if (CommonUtils.isNotNull(intent)) {
            Bundle bundle = intent.getExtras();
            if (CommonUtils.isNotNull(bundle)) {
                mBinding.noDataFound.layoutNoData.setVisibility(View.GONE);
                offer = bundle.getParcelable(AppConstants.OFFER);
                try {
                    mBinding.layoutOffer.setOffer(offer);
                    mBinding.layoutOffer.cvLayout.setCardBackgroundColor(CommonUtils.getColor(offer.getOffer_bg_color()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                mBinding.noDataFound.layoutNoData.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
        if (mBinding.layoutHeader.ivBack == view) {
            finish();
        } else if (view == mBinding.tvStartShopping) {
            CommonUtils.clicked(mBinding.tvStartShopping);
            EventBus.getDefault().post(new StartShoppingEvent(offer.getMerchant_id()));
            finish();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int selColor = ContextCompat.getColor(this, R.color.tab_sel_txt_clr);
        int unSelColor = ContextCompat.getColor(this, R.color.tab_usl_clr);
        //Utils.changeWidthOfTab(mBinding.tabLayout,mActivity);
        for (int index = 0; index < mBinding.tabLayout.getTabCount(); index++) {
            TabLayout.Tab tab = mBinding.tabLayout.getTabAt(index);
            if (tab != null) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    if (index != position) {
                        ((TextView) customView.findViewById(R.id.tvAdsText)).setTextColor(unSelColor);
                        continue;
                    }
                    ((TextView) customView.findViewById(R.id.tvAdsText)).setTextColor(selColor);
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
