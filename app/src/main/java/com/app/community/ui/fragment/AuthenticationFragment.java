package com.app.community.ui.fragment;

import android.content.Context;

import com.app.community.injector.component.CommonComponent;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.BaseFragment;


/**
 * Created by arvind on 06/11/17.
 */

public abstract class AuthenticationFragment extends BaseFragment {
    private CommonActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CommonActivity) {
            CommonActivity activity = (CommonActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public CommonComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

}
