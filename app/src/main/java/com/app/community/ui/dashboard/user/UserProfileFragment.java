package com.app.community.ui.dashboard.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentUserBinding;
import com.app.community.event.UpdateProfileEvent;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.activity.UpdateProfileActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.presenter.CommonPresenter;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.ExplicitIntent;
import com.app.community.utils.GlideUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by ashok on 13/11/17.
 */

public class UserProfileFragment extends DashboardFragment {

    @Inject
    CommonPresenter presenter;
    private FragmentUserBinding mBinding;


    public static Fragment newInstance(int instance){
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_user,container,false);
        GlideUtils.loadImageProfilePic(getContext(), PreferenceUtils.getImage(),mBinding.ivProfile,null,R.drawable.avatar);
        getDashboardActivity().setHeaderTitle(getString(R.string.user));
        CommonUtils.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void setListener() {
     mBinding.ivEdit.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return UserProfileFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onClick(View view) {
     if(view==mBinding.ivEdit){
         ExplicitIntent.getsInstance().navigateTo(getDashboardActivity(), UpdateProfileActivity.class);
     }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);
    }

    @Subscribe
    public void onUpdateProfileEvent(UpdateProfileEvent event) {
        GlideUtils.loadImageProfilePic(getContext(), PreferenceUtils.getImage(),mBinding.ivProfile,null,R.drawable.avatar);
        mBinding.tvName.setText(PreferenceUtils.getUserName());
        mBinding.tvMobile.setText(PreferenceUtils.getUserMono());
        mBinding.tvEmail.setText(PreferenceUtils.getEmail());
        mBinding.tvCardNumber.setText(PreferenceUtils.getCardNumber());
    }
}
