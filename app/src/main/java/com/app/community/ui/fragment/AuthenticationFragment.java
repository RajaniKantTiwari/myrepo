package com.app.community.ui.fragment;

import android.content.Context;

import com.app.community.injector.component.AuthenticationComponent;
import com.app.community.ui.authentication.AuthenticationActivity;
import com.app.community.ui.base.BaseFragment;


/**
 * Created by arvind on 06/11/17.
 */

public abstract class AuthenticationFragment extends BaseFragment {
    private AuthenticationActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AuthenticationActivity) {
            AuthenticationActivity activity = (AuthenticationActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public AuthenticationComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

}
