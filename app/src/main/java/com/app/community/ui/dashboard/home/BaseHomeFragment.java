package com.app.community.ui.dashboard.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentHomeBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.base.BaseFragment;
import com.app.community.ui.dashboard.home.adapter.CartAdapter;
import com.app.community.ui.dashboard.home.event.FragmentEvent;
import com.app.community.utils.GeneralConstant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by rajnikant on 31/12/17.
 */

public class BaseHomeFragment extends BaseFragment {
    private FragmentHomeBinding mBinding;
    private CartAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        EventBus.getDefault().register(this);
        getBaseActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.WELCOME_HOME_FRAGMENT,
                null, R.id.container, true, true, BaseActivity.AnimationType.NONE);
        return mBinding.getRoot();
    }
    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public static Fragment newInstance() {
        return new BaseHomeFragment();
    }
    @Subscribe
    public void onFragmentAddReplace(FragmentEvent event){
        getBaseActivity().pushChildFragment(getChildFragmentManager(), event.getFragmentId(), null, R.id.container, event.isShouldAdd(), event.isAddToBackStack(), BaseActivity.AnimationType.NONE);
    }
}
