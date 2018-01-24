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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.FragmentNewsPaperBinding;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.CategoryData;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.network.response.dashboard.cart.SubCategory;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.WelcomeHomeFragment;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class NewsPaperFragment extends DashboardFragment implements NewsCategoryAdapter.NewsOnCatItemClick, NewsSubCatAdapter.OnNewsSubCatItemClick{
    private FragmentNewsPaperBinding mBinding;
    private NewsCategoryAdapter mNewsCategoryAdapter;
    private NewsSubCatAdapter mNewsSubCategoryAdapter;
    private int oldCatPos, oldSubCatPos;
    private ArrayList<CategoryData> mCatList = new ArrayList<>();
    private ArrayList<SubCategory> mSubCatList = new ArrayList<>();
    private ArrayList<ProductData> mCartList = new ArrayList<>();
    private LinearLayoutManager mLayoutManagerNewsCat;
    private LinearLayoutManager mLayoutMangerNewsSubcat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_news_paper,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void initializeData() {
        setViews();
        List<String> daysArrayList=new ArrayList<>();
        daysArrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.selected_type)));
        SelectedDaysAdapter adapter=new SelectedDaysAdapter(getDashboardActivity(),daysArrayList);
        adapter.setDropDownViewResource(R.layout.spinner_row);
        mBinding.selectedSpiner.setAdapter(adapter);
        mBinding.selectedSpiner.setSelection(adapter.getCount());
        mBinding.selectedSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != daysArrayList.size()-1){
                    if(CommonUtils.isNotNull(view)){
                        view.setBackgroundResource(0);
                        Toast.makeText(getDashboardActivity(),""+position,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
//        ItemCartBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();

        switch (itemView.getId()) {
            case R.id.tvName:
                mCatList.get(oldCatPos).setSelected(false);
                mCatList.get(pos).setSelected(true);
                mSubCatList.clear();
                mSubCatList.addAll(mCatList.get(pos).getSubproduct());
                mSubCatList.get(0).setSelected(true);
                mCartList.clear();
                mCartList.addAll(mSubCatList.get(0).getSubproduct());
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
                mCartList.clear();
                mCartList.addAll(mSubCatList.get(pos).getSubproduct());
                mNewsSubCategoryAdapter.notifyDataSetChanged();
                oldSubCatPos = pos;
                break;
        }


    }
    private void setViews() {
        mLayoutManagerNewsCat = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutMangerNewsSubcat = new LinearLayoutManager(getDashboardActivity(), LinearLayoutManager.HORIZONTAL, false);
        mBinding.rvCat.setLayoutManager(mLayoutManagerNewsCat);
        mBinding.rvSubCat.setLayoutManager(mLayoutMangerNewsSubcat);
        mBinding.tvCheckout.setOnClickListener(this);
        mNewsCategoryAdapter = new NewsCategoryAdapter(mCatList, this);
        mNewsSubCategoryAdapter = new NewsSubCatAdapter(mSubCatList, this);
        mBinding.rvCat.setAdapter(mNewsCategoryAdapter);
        mBinding.rvSubCat.setAdapter(mNewsSubCategoryAdapter);
    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        NewsPaperFragment fragment = new NewsPaperFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
