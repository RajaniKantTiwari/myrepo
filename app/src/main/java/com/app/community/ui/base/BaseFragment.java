package com.app.community.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;


/**
 * Created by ashok on 06/11/17.
 */

public abstract class BaseFragment extends Fragment implements MvpView,View.OnClickListener {
    private BaseActivity mActivity;
    private FragmentNavigation mFragmentNavigation;
    public int mInt;

    public abstract void initializeData();

    public abstract void setListener();

    public abstract String getFragmentName();

    public abstract void attachView();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachView();
        initializeData();
        setListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
        if (context instanceof FragmentNavigation) {
            mFragmentNavigation = (FragmentNavigation) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
    }

    @Override
    public void onError(String message,int requestCode) {
        if (mActivity != null) {
            mActivity.onError(message,requestCode);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void showProgress() {
        if (mActivity != null) {
          mActivity.showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (mActivity != null) {
            mActivity.hideProgress();
        }
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    /*public ActivityComponent getBaseActivityComponent() {
        if (mActivity != null) {
            return mActivity.getBaseActivityComponent();
        }
        return null;
    }*/
    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);
    }
}
