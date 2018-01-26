package com.app.community.ui.newspaper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.FragmentSubscriptionDetailsBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.newspaper.adapter.DaysAdapter;
import com.app.community.ui.newspaper.adapter.SelectedDaysAdapter;
import com.app.community.ui.newspaper.event.SubscriptionEvent;
import com.app.community.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;
import static com.app.community.utils.GeneralConstant.SUBSCRIPTION;
import static com.app.community.utils.GeneralConstant.SUBSCRIPTION_DETAIL;

/**
 * Created by rajnikant on 31/12/17.
 */

public class SubscriptionDetailsFragment extends DashboardFragment {
    private FragmentSubscriptionDetailsBinding mBinding;
    private LinearLayoutManager mDaysLayoutManager;
    private DaysAdapter mDaysAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_subscription_details, container, false);
        CommonUtils.register(this);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        setViews();
        List<String> daysArrayList = new ArrayList<>();
        daysArrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.selected_type)));
        SelectedDaysAdapter adapter = new SelectedDaysAdapter(getDashboardActivity(), daysArrayList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.selectedSpiner.setAdapter(adapter);
        mBinding.selectedSpiner.setSelection(adapter.getCount());
        mBinding.selectedSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != daysArrayList.size() - 1) {
                    if (CommonUtils.isNotNull(view)) {
                        view.setBackgroundResource(0);
                        Toast.makeText(getDashboardActivity(), "" + position, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setViews() {
        mDaysLayoutManager = new LinearLayoutManager(getDashboardActivity());
        mBinding.rvDays.setLayoutManager(mDaysLayoutManager);
        ArrayList<Days> daysArrayList = new ArrayList<>();
        CommonUtils.setDays(daysArrayList);
        mDaysAdapter = new DaysAdapter(getDashboardActivity(), daysArrayList);
        mBinding.rvDays.setAdapter(mDaysAdapter);
    }

    @Override
    public void setListener() {
        mBinding.tvDone.setOnClickListener(this);
    }

    @Override
    public String getFragmentName() {
        return SubscriptionDetailsFragment.class.getSimpleName();
    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.tvDone) {
            EventBus.getDefault().post(new SubscriptionEvent(SUBSCRIPTION));
        }
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CommonUtils.unregister(this);
    }

    @Subscribe
    public void onEvent(SubscriptionEvent event) {
        if (event.getShow() == SUBSCRIPTION) {
            mBinding.layoutSubscription.setVisibility(View.GONE);
        } else if (event.getShow() == SUBSCRIPTION_DETAIL) {
            mBinding.layoutSubscription.setVisibility(View.VISIBLE);
        }
    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        SubscriptionDetailsFragment fragment = new SubscriptionDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
