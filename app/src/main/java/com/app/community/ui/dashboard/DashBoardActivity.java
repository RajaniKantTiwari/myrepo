package com.app.community.ui.dashboard;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.app.community.CommonApplication;
import com.app.community.R;
import com.app.community.databinding.ActivityDashboardBinding;
import com.app.community.event.ProductDetailsEvent;
import com.app.community.event.UpdateCartEvent;
import com.app.community.event.UpdateProfileEvent;
import com.app.community.injector.component.DaggerDashboardComponent;
import com.app.community.injector.component.DashboardComponent;
import com.app.community.injector.module.DashboardModule;
import com.app.community.network.DeviceToken;
import com.app.community.network.request.DeviceTokenRequest;
import com.app.community.network.request.cart.Cart;
import com.app.community.network.request.cart.CartListRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.home.MerchantResponse;
import com.app.community.network.response.dashboard.rightdrawer.Merchant;
import com.app.community.network.response.dashboard.rightdrawer.ProductSubCategory;
import com.app.community.network.response.dashboard.rightdrawer.ProductTypeData;
import com.app.community.ui.WelcomeScreenActivity;
import com.app.community.ui.activity.HelpsAndSupportActivity;
import com.app.community.ui.activity.UpdateProfileActivity;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.expandrecycleview.draweradapter.DrawerAdapterRight;
import com.app.community.ui.dashboard.home.SearchActivity;
import com.app.community.ui.dashboard.home.WelcomeHomeFragment;
import com.app.community.ui.dashboard.home.adapter.DrawerAdapterLeft;
import com.app.community.ui.dashboard.home.event.NewsEvent;
import com.app.community.ui.dashboard.home.event.SearchProductEvent;
import com.app.community.ui.dashboard.home.event.UpdateAddress;
import com.app.community.ui.dashboard.home.fragment.CheckoutFragment;
import com.app.community.ui.dashboard.home.fragment.MerchantFragment;
import com.app.community.ui.dashboard.home.fragment.MyOrderActivity;
import com.app.community.ui.dashboard.home.fragment.NewsMainFragment;
import com.app.community.ui.dashboard.notification.NotificationFragment;
import com.app.community.ui.dashboard.offer.OfferFragment;
import com.app.community.ui.dashboard.user.UserProfileFragment;
import com.app.community.ui.dialogfragment.CheckoutDialogFragment;
import com.app.community.utils.AppConstants;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.DashBoardHelper;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GeneralConstant;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.LogUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.OFFER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.USER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.WELCOME_HOME_FRAGMENT;

public class DashBoardActivity extends BaseActivity implements DrawerAdapterLeft.DrawerLeftListener,
        DrawerAdapterRight.ProductSubHolderListener, CheckoutDialogFragment.CheckoutDialogListener {
    private static String TAG = DashBoardActivity.class.getSimpleName();
    //Better convention to properly name the indices what they are in your app

    private ActivityDashboardBinding mBinding;
    public DashboardComponent mDashboardComponent;
    @Inject
    public DashboardPresenter mPresenter;
    //Sliding drawer
    private ActionBarDrawerToggle mDrawerToggleRight;

    private DrawerAdapterRight mDrawerAdapterRight;
    ArrayList<ProductSubCategory> responseList;
    private ActionBarDrawerToggle mDrawerToggleLeft;
    private DrawerAdapterLeft mDrawerAdapterLeft;

    @Override
    public void onLeftDrawerItemClicked(int position) {
        closeDrawerLeft();
        switch (position) {
            case AppConstants.HOME:
                changeIcon(WELCOME_HOME_FRAGMENT);
                openFragment(new WelcomeHomeFragment(), null, false, false, NONE);
                break;
            case AppConstants.MYORDER:
                ExplicitIntent.getsInstance().navigateTo(this, MyOrderActivity.class);
                break;
            case AppConstants.MYADDRESS:
                Bundle bundle = new Bundle();
                bundle.putBoolean(GeneralConstant.IS_FROM_HOME, true);
                ExplicitIntent.getsInstance().navigateTo(this, WelcomeScreenActivity.class, bundle);
                break;
            case AppConstants.MYACCOUNT:
                changeIcon(USER_FRAGMENT);
                openFragment(new UserProfileFragment(), null, false, false, NONE);
                break;
            case AppConstants.NOTIFICATION:
                changeIcon(NOTIFICATION_FRAGMENT);
                openFragment(new NotificationFragment(), null, false, false, NONE);
                break;
            case AppConstants.ABOUTUS:
                //onTabSelected(USER_FRAGMENT);
                break;
            case AppConstants.HELPSUPPORT:
                ExplicitIntent.getsInstance().navigateTo(this, HelpsAndSupportActivity.class);
                break;

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        CommonUtils.register(this);
        hideSoftKeyboard(mBinding.getRoot());
        responseList = new ArrayList<>();
        initDashboardComponent();
        attachView();
        setupDrawerToggleRight();
        setupDrawerToggleLeft();
        initializeData();
        setListener();
        onUpdateCartEvent(new UpdateCartEvent());
    }

    private void initDashboardComponent() {
        mDashboardComponent = DaggerDashboardComponent.builder().
                dashboardModule(new DashboardModule(this)).
                applicationComponent(((CommonApplication) getApplication()).
                        getApplicationComponent()).build();

    }

    public DashboardComponent getActivityComponent() {
        return mDashboardComponent;
    }

    public void onTabSelected(int position) {
        changeIcon(position);
        switch (position) {
            case WELCOME_HOME_FRAGMENT:
                openFragment(new WelcomeHomeFragment(), null, false, false, NONE);
                break;
            case OFFER_FRAGMENT:
                openFragment(new OfferFragment(), null, false, false, NONE);
                break;
            case NOTIFICATION_FRAGMENT:
                openFragment(new NotificationFragment(), null, false, false, NONE);
                break;
            case USER_FRAGMENT:
                openFragment(new UserProfileFragment(), null, false, false, NONE);
                break;
        }
    }

    private void openFragment(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        pushFragment(fragment, bundle, R.id.container, addToBackStack, shouldAdd, animationType);
        clearAllBackStack();
    }

    public void addFragmentInContainer(Fragment fragment, Bundle bundle, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        pushFragment(fragment, bundle, R.id.container, addToBackStack, shouldAdd, animationType);
    }

    private void changeIcon(int position) {
        for (int i = 0; i < AppConstants.NO_OF_TAB; i++) {
            switch (i) {
                case 0:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar1.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar1.setImageResource(R.drawable.ic_home);
                    } else {
                        mBinding.bottomLayout.viewBar1.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar1.setImageResource(R.drawable.ic_home_light);
                    }

                    break;
                case 1:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar2.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar2.setImageResource(R.drawable.ic_gift);
                    } else {
                        mBinding.bottomLayout.viewBar2.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar2.setImageResource(R.drawable.ic_gift_light);
                    }
                    break;
                case 2:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar3.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar3.setImageResource(R.drawable.ic_notification);
                    } else {
                        mBinding.bottomLayout.viewBar3.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar3.setImageResource(R.drawable.ic_notification_light);
                    }
                    break;
                case 3:
                    if (i == position) {
                        mBinding.bottomLayout.viewBar4.setVisibility(View.VISIBLE);
                        mBinding.bottomLayout.imageViewBar4.setImageResource(R.drawable.ic_user);
                    } else {
                        mBinding.bottomLayout.viewBar4.setVisibility(View.INVISIBLE);
                        mBinding.bottomLayout.imageViewBar4.setImageResource(R.drawable.ic_user_light);
                    }
                    break;
            }
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {
        if (requestCode == AppConstants.RIGHT_DRAWER_RESPONSE) {
            if (CommonUtils.isNotNull(response) && response instanceof ProductTypeData) {
                this.responseList.clear();
                this.responseList.addAll(DashBoardHelper.setRightDrawerData((ProductTypeData) response));
                if (responseList.size() > 0) {
                    CommonUtils.setVisibility(mBinding.layoutDrawerRight.layoutMain,
                            mBinding.layoutDrawerRight.layoutNoData.layoutNoData, true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                    mBinding.layoutDrawerRight.rvDrawer.setLayoutManager(layoutManager);
                    mDrawerAdapterRight = new DrawerAdapterRight(this, responseList, this);
                    mBinding.layoutDrawerRight.rvDrawer.setAdapter(mDrawerAdapterRight);
                } else {
                    CommonUtils.setVisibility(mBinding.layoutDrawerRight.layoutMain,
                            mBinding.layoutDrawerRight.layoutNoData.layoutNoData, false);
                    mBinding.layoutDrawerRight.layoutNoData.layoutNoData.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black_color));
                    mBinding.layoutDrawerRight.layoutNoData.tvSubProductName.setText(getResources().getString(R.string.service_is_anavailable_for_this_area));
                }

            } else {
                CommonUtils.setVisibility(mBinding.layoutDrawerRight.layoutMain,
                        mBinding.layoutDrawerRight.layoutNoData.layoutNoData, false);
                mBinding.layoutDrawerRight.layoutNoData.layoutNoData.setBackgroundColor(CommonUtils.getColor(this, R.color.dark_black_color));
                mBinding.layoutDrawerRight.layoutNoData.tvSubProductName.setText(getResources().getString(R.string.service_is_anavailable_for_this_area));
            }
        } else if (requestCode == AppConstants.DEVICE_TOKEN_RESPONSE) {
            LogUtils.LOGE(TAG, response.getMsg());
        } /*else if (requestCode == AppConstants.LOGOUT||requestCode==AppConstants.CARTADDED) {*/ else if (requestCode == AppConstants.LOGOUT) {
            if (CommonUtils.isNotNull(response)) {
                showToast(response.getMsg());
                CommonUtils.logout(this);
            }
        }
    }


    @Override
    public void attachView() {
        getActivityComponent().inject(this);
    }

    public void initializeData() {
        mBinding.layoutDrawerLeft.tvName.setText(PreferenceUtils.getUserName());
        mBinding.layoutDrawerLeft.tvMobile.setText(PreferenceUtils.getUserMono());
        mPresenter.getCategorySubCategoryRightDrawer(this);
        DeviceTokenRequest request = new DeviceTokenRequest();
        request.setUserid(PreferenceUtils.getUserId());
        DeviceToken token = new DeviceToken();
        token.setDeveiceUniqId(CommonUtils.getDeviceUniqueId(this));
        token.setDeviceTokenId(PreferenceUtils.getDeviceToken());
        token.setDeviceType(GeneralConstant.DEVICETYPE);
        request.setInfo(token);
        mPresenter.setDeviceToken(this, request);
        pushFragment(new WelcomeHomeFragment(), null, R.id.container, true, false, NONE);
    }

    public void setListener() {
        mBinding.toolBar.ivSearch.setOnClickListener(this);
        mBinding.toolBar.ivDrawer.setOnClickListener(this);
        mBinding.toolBar.ivRightDrawer.setOnClickListener(this);
        mBinding.layoutDrawerLeft.layoutLogout.setOnClickListener(this);
        mBinding.layoutDrawerLeft.ivProfile.setOnClickListener(this);
        mBinding.layoutDrawerLeft.ivUpdate.setOnClickListener(this);

        mBinding.bottomLayout.linearLayoutBar1.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar2.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar3.setOnClickListener(this);
        mBinding.bottomLayout.linearLayoutBar4.setOnClickListener(this);
        mBinding.toolBar.layoutCart.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (mBinding.toolBar.ivDrawer == view) {
            openDrawerLeft();
        } else if (view == mBinding.toolBar.ivRightDrawer) {
            openDrawerRight();
        } else if (view == mBinding.toolBar.ivSearch) {
            ExplicitIntent.getsInstance().navigateTo(this, SearchActivity.class);
        } else if (mBinding.layoutDrawerLeft.layoutLogout == view) {
            mPresenter.logout(this);

        } else if (mBinding.layoutDrawerLeft.ivProfile == view) {
            closeDrawerLeft();
            changeIcon(USER_FRAGMENT);
            onTabSelected(USER_FRAGMENT);
        } else if (mBinding.layoutDrawerLeft.ivUpdate == view) {
            closeDrawerLeft();
            ExplicitIntent.getsInstance().navigateTo(this, UpdateProfileActivity.class);
        } else if (view == mBinding.bottomLayout.linearLayoutBar1) {
            changeIcon(WELCOME_HOME_FRAGMENT);
            clearAllBackStack();
            pushFragment(new WelcomeHomeFragment(), null, R.id.container, true, false, NONE);
        } else if (view == mBinding.bottomLayout.linearLayoutBar2) {
            changeIcon(OFFER_FRAGMENT);
            clearAllBackStack();
            pushFragment(new OfferFragment(), null, R.id.container, true, false, NONE);
        } else if (view == mBinding.bottomLayout.linearLayoutBar3) {
            changeIcon(NOTIFICATION_FRAGMENT);
            clearAllBackStack();
            pushFragment(new NotificationFragment(), null, R.id.container, true, false, NONE);
        } else if (view == mBinding.bottomLayout.linearLayoutBar4) {
            changeIcon(USER_FRAGMENT);
            clearAllBackStack();
            pushFragment(new UserProfileFragment(), null, R.id.container, true, false, NONE);
        }else if(view==mBinding.toolBar.layoutCart){
            addFragmentInContainer(new CheckoutFragment(), null, true, true, NONE);
        }
    }


    void setupDrawerToggleRight() {

        mDrawerToggleRight = new ActionBarDrawerToggle(this, mBinding.drawer, null, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggleRight.syncState();
        mBinding.drawer.addDrawerListener(mDrawerToggleRight);
        RecyclerView.ItemAnimator animator = mBinding.layoutDrawerRight.rvDrawer.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
    }


    void setupDrawerToggleLeft() {

        mDrawerToggleLeft = new ActionBarDrawerToggle(this, mBinding.drawer, null, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggleLeft.syncState();
        mBinding.drawer.addDrawerListener(mDrawerToggleLeft);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator animator = mBinding.layoutDrawerLeft.rvDrawer.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }
        mDrawerAdapterLeft = new DrawerAdapterLeft(this, this);
        mBinding.layoutDrawerLeft.rvDrawer.setLayoutManager(layoutManager);
        mBinding.layoutDrawerLeft.rvDrawer.setAdapter(mDrawerAdapterLeft);
    }

    private void openDrawerLeft() {
        if (!mBinding.drawer.isDrawerOpen(Gravity.LEFT)) {
            mBinding.drawer.openDrawer(Gravity.LEFT);
        }
    }

    private void openDrawerRight() {
        if (!mBinding.drawer.isDrawerOpen(Gravity.RIGHT)) {
            mBinding.drawer.openDrawer(Gravity.RIGHT);
        }
    }

    private void closeDrawerLeft() {
        if (mBinding.drawer.isDrawerOpen(Gravity.LEFT)) {
            mBinding.drawer.closeDrawers();
        }
    }

    private void closeDrawerRight() {
        if (mBinding.drawer.isDrawerOpen(Gravity.RIGHT)) {
            mBinding.drawer.closeDrawers();
        }
    }


    public void setHeaderTitle(String title) {
        mBinding.toolBar.layoutTab.setVisibility(View.VISIBLE);
        mBinding.toolBar.layoutProduct.setVisibility(View.GONE);
        mBinding.toolBar.tvHeading.setText(title);
        mBinding.toolBar.toolbar.setBackgroundColor(CommonUtils.getColor(this, R.color.darker_blackish));
    }

    public void setHeader(String address, String imageUrl, String bgColor) {
        mBinding.toolBar.layoutTab.setVisibility(View.GONE);
        mBinding.toolBar.layoutProduct.setVisibility(View.VISIBLE);
        mBinding.toolBar.tvAddress.setText(address);
        GlideUtils.loadImage(this, imageUrl, mBinding.toolBar.ivProductImage, null, R.drawable.icon_placeholder);
        if (CommonUtils.isNotNull(bgColor)) {
            mBinding.toolBar.toolbar.setBackgroundColor(Color.parseColor(bgColor));
        }
    }

    @Override
    public void onSubItemClicked(int parentPosition, int childPosition) {
        if (CommonUtils.isNotNull(PreferenceUtils.getCartData()) && PreferenceUtils.getCartData().size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putString(GeneralConstant.MESSAGE, getResources().getString(R.string.if_you_change_merchant_all_previous));
            bundle.putInt(GeneralConstant.PARENT_POSITION, parentPosition);
            bundle.putInt(GeneralConstant.CHILD_POSITION, childPosition);
            CommonUtils.showCheckoutDialog(this, bundle, this);
        } else {
            openProductSubProduct(parentPosition, childPosition);
        }

    }

    private void openProductSubProduct(int parentPosition, int childPosition) {
        Bundle bundle = new Bundle();
        if (CommonUtils.isNotNull(responseList) && responseList.size() > parentPosition) {
            ProductSubCategory subCategory = responseList.get(parentPosition);
            if (CommonUtils.isNotNull(subCategory)) {
                ArrayList<Merchant> merchantList = subCategory.getMerchantname();
                if (CommonUtils.isNotNull(merchantList) && merchantList.size() > childPosition) {
                    Merchant merchantData = merchantList.get(childPosition);
                    if (CommonUtils.isNotNull(merchantData)) {
                        bundle.putString(AppConstants.MERCHANT_ID, merchantData.getId());
                        bundle.putString(AppConstants.MERCHANT_ADDRESS, merchantData.getAddress());
                        bundle.putString(AppConstants.MERCHANT_IMAGE, merchantData.getImage());
                        bundle.putString(AppConstants.MERCHANT_BACKGROUND_COLOR, merchantData.getBackground_color());

                    }
                }
            }
        }
        closeDrawerRight();
        pushFragment(new ProductSubproductFragment(), bundle, R.id.container, true, true, NONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);
    }

    @Subscribe
    public void onSearchProduct(ProductDetailsEvent event) {
        Bundle bundle = new Bundle();
        MerchantResponse merchantResponse = event.getMerchant();
        bundle.putString(AppConstants.MERCHANT_ID, merchantResponse.getId());
        bundle.putString(AppConstants.MERCHANT_ADDRESS, merchantResponse.getAddress());
        bundle.putString(AppConstants.MERCHANT_IMAGE, merchantResponse.getImage());
        bundle.putString(AppConstants.MERCHANT_BACKGROUND_COLOR, merchantResponse.getBackground_color());
        pushFragment(new ProductSubproductFragment(), bundle, R.id.container, true, true, NONE);


    }

    @Subscribe
    public void onSearchProduct(SearchProductEvent event) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                bundle.putString(GeneralConstant.SEARCH_STRING, event.getSearchString());
                addFragmentInContainer(new MerchantFragment(), bundle, false, false, NONE);
            }
        }, GeneralConstant.DELAYTIME);

    }

    @Subscribe
    public void onNewsEvent(NewsEvent event) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pushFragment(new NewsMainFragment(), null, R.id.container, true, true, NONE);
            }
        }, GeneralConstant.DELAYTIME);

    }

    @Subscribe
    public void onAddressEvent(UpdateAddress event) {
        mPresenter.getCategorySubCategoryRightDrawer(this);
        mDrawerAdapterLeft.notifyDataSetChanged();
    }

    @Subscribe
    public void onUpdateProfileEvent(UpdateProfileEvent event) {
        GlideUtils.loadImageProfilePic(this, PreferenceUtils.getImage(), mBinding.layoutDrawerLeft.ivProfile, null, R.drawable.avatar);
        mBinding.layoutDrawerLeft.tvName.setText(PreferenceUtils.getUserName());
        mBinding.layoutDrawerLeft.tvMobile.setText(PreferenceUtils.getUserMono());
    }

    @Subscribe
    public void onUpdateCartEvent(UpdateCartEvent event) {
        int cartItem = 0;
        if (CommonUtils.isNotNull(PreferenceUtils.getCartData()) && PreferenceUtils.getCartData().size() > 0) {
            ArrayList<ProductData> productList = PreferenceUtils.getCartData();
            for (ProductData product : productList) {
                if (CommonUtils.isNotNull(product)) {
                    cartItem = cartItem + product.getQty();
                }
            }
        }
        if (cartItem > 0) {
            mBinding.toolBar.tvCart.setText(String.valueOf(cartItem));
            mBinding.toolBar.tvCart.setVisibility(View.VISIBLE);
        } else {
            mBinding.toolBar.tvCart.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (CommonUtils.isNotNull(PreferenceUtils.getCartData()) &&
                CommonUtils.isNotNull(PreferenceUtils.getCartData().size())
                && PreferenceUtils.getCartData().size() > 0) {
            //request for cart
            CartListRequest request = new CartListRequest();
            //list of product added in cart
            ArrayList<Cart> cartList = new ArrayList<>();
            ArrayList<ProductData> productList = PreferenceUtils.getCartData();
            request.setMerchant_id(productList.get(0).getMerchantId());
            //id of merchant
            for (ProductData product : productList) {
                if (CommonUtils.isNotNull(product)) {
                    Cart cart = new Cart();
                    cart.setMerchantlist_id(product.getMerchantlistid());
                    cart.setMasterproductid(product.getMasterproductid());
                    cart.setQty(product.getQty());
                    cartList.add(cart);
                }
            }
            request.setCart(cartList);
            mPresenter.addForCartList(this, request, this);

        }
    }

    @Override
    public void ok(int parentPosition, int childPosition) {
        PreferenceUtils.setCartData(null);
        onUpdateCartEvent(new UpdateCartEvent());
        openProductSubProduct(parentPosition, childPosition);
    }

    @Override
    public void cancel() {

    }
}
