package com.app.community.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.app.community.R;
import com.app.community.ui.cart.ProductSubproductFragment;
import com.app.community.ui.dashboard.home.ConfirmOrderFragment;
import com.app.community.ui.dashboard.home.WelcomeHomeFragment;
import com.app.community.ui.dashboard.home.fragment.CategoryFragment;
import com.app.community.ui.dashboard.home.fragment.NewsMainFragment;
import com.app.community.ui.dashboard.home.fragment.SubscribeFragment;
import com.app.community.ui.dashboard.home.fragment.HomeFragment;
import com.app.community.ui.dashboard.home.fragment.LiveOrderFragment;
import com.app.community.ui.dashboard.home.fragment.NewsFragment;
import com.app.community.ui.dashboard.home.fragment.NewsTabFragment;
import com.app.community.ui.dashboard.home.fragment.PastOrderFragment;
import com.app.community.ui.dashboard.home.fragment.MerchantListFragment;
import com.app.community.ui.dashboard.home.fragment.ProductMapFragment;
import com.app.community.ui.dashboard.home.fragment.RecentFragment;
import com.app.community.ui.dashboard.notification.NotificationFragment;
import com.app.community.ui.dashboard.offer.OfferFragment;
import com.app.community.ui.dashboard.user.UserFragment;
import com.app.community.ui.fragment.ZoomAnimationImageActivity;
import com.app.community.ui.newspaper.SubscriptionDetailsFragment;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.LogUtils;
import com.app.community.utils.NetworkUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.community.ui.base.BaseActivity.AnimationType.DEFAULT;
import static com.app.community.ui.base.BaseActivity.AnimationType.FADE;
import static com.app.community.ui.base.BaseActivity.AnimationType.NONE;
import static com.app.community.ui.base.BaseActivity.AnimationType.SLIDE;
import static com.app.community.ui.base.BaseActivity.AnimationType.ZOOM;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.CATEGORY_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.CONFIRM_ORDER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.HOME_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.LIVEORDER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.NEWS_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.NEWS_TAB_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.NOTIFICATION_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.OFFER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PASTORDER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_LIST;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_MAP_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.PRODUCT_SUBPRODUCT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.RECENT_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.SUBSCRIPTION_DETAIL_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.SUBSCRIPTION_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.USER_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.WELCOME_HOME_FRAGMENT;
import static com.app.community.utils.GeneralConstant.FRAGMENTS.ZOOMIMAGE_FRAGMENT;


/**
 * Created by ashok on 01/11/17.
 */
/*
Parent Activity to give functionality to all Activity
*/
public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback, View.OnClickListener {
    //private ActivityComponent mActivityComponent;
    private static String TAG = BaseActivity.class.getSimpleName();
    private Snackbar mSnackbar;
    private boolean mAlive;
    private Dialog mLoadingDialog;

    //to attach View
    public abstract void attachView();


    @Override
    public void onError(String message, int requestCode) {
//        if (message != null) {
//            showSnackBar(message);
//        } else {
//            showSnackBar(getString(R.string.internet_error));
//        }
    }


    public void showSnackBar(String message) {
        mSnackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = mSnackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        mSnackbar.show();
    }

    public void showKeyboard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void hideSoftKeyboard(View view) {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAlive = true;
    }

    @Override
    protected void onStop() {
        if (isNotNull(mSnackbar)) {
            mSnackbar.dismiss();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mAlive = false;
        super.onDestroy();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mLoadingDialog = new Dialog(this, R.style.transDialog);
        mLoadingDialog.setContentView(R.layout.view_progress_dialog);
    }


    /**
     * Shows progress dialog with custom view, title and message.
     */
    @Override
    public void showProgress() {
        try {

            if (mLoadingDialog == null) {
                mLoadingDialog = new Dialog(this, R.style.transDialog);
                mLoadingDialog.setContentView(R.layout.view_progress_dialog);
                mLoadingDialog.findViewById(R.id.progress_bar);
            }
            //progressBar.setIndeterminateTintList(ColorStateList.valueOf(ContextCompat.getColor(this, id)));

            mLoadingDialog.setCancelable(false);
            mLoadingDialog.show();
        } catch (Exception e) {
            if (e.getMessage() != null)
                LogUtils.LOGE(TAG, e.getMessage());
        }
    }

    /**
     * Hides progressbar
     */
    @Override
    public void hideProgress() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing())
                mLoadingDialog.dismiss();
            mLoadingDialog = null;
        } catch (Exception x) {
            if (x.getMessage() != null)
                LogUtils.LOGE(TAG, x.getMessage());
        }
    }


    /**
     * Shows Snackbar from top of the screen.
     *
     * @param msg
     */
    public void showSnakBarFromBottom(String msg) {
        //if (!TextUtils.isEmpty(msg))
        //CommonUtils.showSnakbarFromBottom(this, msg);
    }

    public void showToast(String msg) {
        CommonUtils.showToastLong(this, msg);

    }


    //Check object is not null
    public boolean isNotNull(Object obj) {
        return obj != null;
    }


    //check object is null
    public boolean isNull(Object obj) {
        return obj == null;
    }

    @Override
    public boolean isNetworkConnected() {
        boolean connectionStatus=NetworkUtils.isNetworkConnected(getApplicationContext());
        if(!connectionStatus){
            showToast(getResources().getString(R.string.network_not_connected));
        }
        return connectionStatus;
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (isNotNull(imm)) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void onFragmentAttached() {

    }

    public void onFragmentDetached(String tag) {

    }

    private boolean isAlive() {
        return mAlive;
    }

    private static BaseFragment getFragment(int fragmentId) {
        BaseFragment fragment = null;
        switch (fragmentId) {
            case PRODUCT_MAP_FRAGMENT:
                fragment = new ProductMapFragment();
                break;
            case PRODUCT_LIST:
                fragment = new MerchantListFragment();
                break;
            case RECENT_FRAGMENT:
                fragment = new RecentFragment();
                break;
            case CATEGORY_FRAGMENT:
                fragment = new CategoryFragment();
                break;
            case LIVEORDER_FRAGMENT:
                fragment = new LiveOrderFragment();
                break;
            case PASTORDER_FRAGMENT:
                fragment = new PastOrderFragment();
                break;
            case WELCOME_HOME_FRAGMENT:
                fragment=new WelcomeHomeFragment();
                break;
            case NEWS_TAB_FRAGMENT:
                fragment=new NewsTabFragment();
                break;
            case HOME_FRAGMENT:
                fragment=new HomeFragment();
                break;
            case NEWS_FRAGMENT:
                fragment=new NewsMainFragment();
                break;
            case CONFIRM_ORDER_FRAGMENT:
                fragment=new ConfirmOrderFragment();
                break;
            case PRODUCT_SUBPRODUCT:
                fragment=new ProductSubproductFragment();
                break;
            case SUBSCRIPTION_DETAIL_FRAGMENT:
                fragment=new SubscriptionDetailsFragment();
                break;
            case SUBSCRIPTION_FRAGMENT:
                fragment=new SubscribeFragment();
                break;
            case OFFER_FRAGMENT:
                new OfferFragment();
                break;
            case NOTIFICATION_FRAGMENT:
                new NotificationFragment();
                break;
            case USER_FRAGMENT:
                new UserFragment();
        }
        return fragment;
    }

    public Fragment pushChildFragment(FragmentManager manager, int fragmentId, Bundle args, int containerViewId, boolean shouldAdd, boolean addToBackStack, @AnimationType int animationType) {
        try {
            BaseFragment fragment = getFragment(fragmentId);
            if (fragment == null) return null;
            if (args != null)
                fragment.setArguments(args);

            FragmentTransaction transaction = manager.beginTransaction();
            setAnimation(containerViewId, shouldAdd, addToBackStack, animationType, fragment, transaction);
            return fragment;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }




    public Fragment pushFragment(int fragmentId, Bundle args, int containerViewId, boolean addToBackStack, boolean shouldAdd, @AnimationType int animationType) {
        try {
            BaseFragment fragment = getFragment(fragmentId);
            if (fragment == null) return null;
            if (args != null)
                fragment.setArguments(args);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            setAnimation(containerViewId, shouldAdd, addToBackStack, animationType, fragment, ft);
            return fragment;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void setAnimation(int containerViewId, boolean shouldAdd, boolean addToBackStack, @AnimationType int animationType, BaseFragment fragment, FragmentTransaction transaction) {
        switch (animationType) {
            case DEFAULT:
            case SLIDE:
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case FADE:
                transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                break;
            case ZOOM:
                transaction.setCustomAnimations(R.anim.zoomin, R.anim.fadein);
                break;
            case NONE:
                break;

        }
        if (shouldAdd)
            transaction.add(containerViewId, fragment, fragment.getFragmentName());
        else
            transaction.replace(containerViewId, fragment, fragment.getFragmentName());
        if (addToBackStack)
            transaction.addToBackStack(fragment.getFragmentName());

        transaction.commitAllowingStateLoss();
    }

    public void clearAllBackStack(){
        FragmentManager fm =getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    public void addFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment, fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    @IntDef({SLIDE, FADE, DEFAULT, NONE,ZOOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationType {
        int SLIDE = 0;
        int FADE = 1;
        int DEFAULT = 2;
        int NONE = 3;
        int ZOOM=4;
    }


}