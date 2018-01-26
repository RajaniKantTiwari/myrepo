package com.app.community.ui.newspaper;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsPaperBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.CategoryData;
import com.app.community.network.response.dashboard.cart.SubCategory;
import com.app.community.ui.base.BaseActivity;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.newspaper.adapter.NewsCategoryAdapter;
import com.app.community.ui.newspaper.adapter.NewsSubCatAdapter;
import com.app.community.ui.newspaper.adapter.SelectedDaysAdapter;
import com.app.community.ui.newspaper.event.SubscriptionEvent;
import com.app.community.utils.GeneralConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsPaperFragment extends DashboardFragment implements NewsCategoryAdapter.NewsOnCatItemClick, NewsSubCatAdapter.OnNewsSubCatItemClick {
    private FragmentNewsPaperBinding mBinding;
    private NewsCategoryAdapter mNewsCategoryAdapter;
    private NewsSubCatAdapter mNewsSubCategoryAdapter;
    private int oldCatPos, oldSubCatPos;
    private ArrayList<CategoryData> mCatList = new ArrayList<>();
    private ArrayList<SubCategory> mSubCatList = new ArrayList<>();
    private LinearLayoutManager mLayoutManagerNewsCat;
    private LinearLayoutManager mLayoutMangerNewsSubcat;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_paper, container, false);
        addFragment();
        return mBinding.getRoot();
    }

    private void addFragment() {
        getDashboardActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.SUBSCRIPTION_DETAIL_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
        getDashboardActivity().pushChildFragment(getChildFragmentManager(), GeneralConstant.FRAGMENTS.SUBSCRIPTION_FRAGMENT,
                null, R.id.container, true, false, BaseActivity.AnimationType.NONE);
    }

    @Override
    public void initializeData() {
        setViews();
        List<String> daysArrayList = new ArrayList<>();
        daysArrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.selected_type)));
        SelectedDaysAdapter adapter = new SelectedDaysAdapter(getDashboardActivity(), daysArrayList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
    }

    private void setViews() {
        mLayoutManagerNewsCat = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutMangerNewsSubcat = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mBinding.rvCat.setLayoutManager(mLayoutManagerNewsCat);
        mBinding.rvSubCat.setLayoutManager(mLayoutMangerNewsSubcat);
        mNewsCategoryAdapter = new NewsCategoryAdapter(getDashboardActivity(),mCatList, this);
        mNewsSubCategoryAdapter = new NewsSubCatAdapter(getDashboardActivity(),mSubCatList, this);
        mBinding.rvCat.setAdapter(mNewsCategoryAdapter);
        mBinding.rvSubCat.setAdapter(mNewsSubCategoryAdapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return NewsPaperFragment.class.getSimpleName();
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

    @Override
    public void onCatClick(int pos, View view) {
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.tvName:
                mCatList.get(oldCatPos).setSelected(false);
                mCatList.get(pos).setSelected(true);
                mSubCatList.clear();
                mSubCatList.addAll(mCatList.get(pos).getSubproduct());
                mSubCatList.get(0).setSelected(true);
                mNewsSubCategoryAdapter.notifyDataSetChanged();
                mNewsCategoryAdapter.notifyDataSetChanged();
                oldCatPos = pos;
                oldSubCatPos = 0;
                break;
        }
    }

    @Override
    public void onSubCatClick(int pos, View view) {
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.tvName:
                mSubCatList.get(pos).setSelected(true);
                mSubCatList.get(oldSubCatPos).setSelected(false);
                mNewsSubCategoryAdapter.notifyDataSetChanged();
                oldSubCatPos = pos;
                break;
        }
    }


    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        NewsPaperFragment fragment = new NewsPaperFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
