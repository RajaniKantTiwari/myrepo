package com.app.community.ui.dashboard.home.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.R;
import com.app.community.databinding.CartRowItemBinding;
import com.app.community.databinding.FragmentCartBinding;
import com.app.community.network.request.cart.CartRequest;
import com.app.community.network.response.BaseResponse;
import com.app.community.network.response.dashboard.cart.ProductData;
import com.app.community.ui.dashboard.DashboardFragment;
import com.app.community.ui.dashboard.home.adapter.CartAdapter;

import java.util.ArrayList;

import static com.app.community.utils.GeneralConstant.ARGS_INSTANCE;

/**
 * Created by rajnikant on 31/12/17.
 */

public class CartFragment extends DashboardFragment implements CartAdapter.OnAddToCart {
    private FragmentCartBinding mBinding;
    private CartAdapter mAdapter;
    private int MAX_LIMIT = 10, MIN_LIMIT = 0;
    private ArrayList<ProductData> mCartList = new ArrayList<>();
    private CartRequest cartRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_cart,container,false);
        initializeAdapter();
        return mBinding.getRoot();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseActivity());
        mAdapter=new CartAdapter(getBaseActivity(),this);
        mBinding.rvCartList.setLayoutManager(layoutManager);
        mBinding.rvCartList.setAdapter(mAdapter);
    }

    @Override
    public void initializeData() {
        cartRequest=new CartRequest();
        getPresenter().addToCart(getDashboardActivity(),cartRequest);

    }

    @Override
    public void setListener() {

    }

    @Override
    public String getFragmentName() {
        return null;
    }

    @Override
    public void attachView() {
        getPresenter().attachView(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    public static Fragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(ARGS_INSTANCE, instance);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void addToCartClick(int pos, View view) {
        CartRowItemBinding viewBinding = DataBindingUtil.bind(view);
        View itemView = (View) view.getTag();
        switch (itemView.getId()) {
            case R.id.ivAdd:
                addToCart(viewBinding.tvQuantity, pos);
                break;
            case R.id.ivSub:
                removeFromCart(viewBinding.tvQuantity, pos);
                break;
        }
    }
    private void addToCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());

        if (count < MAX_LIMIT) {
            count++;
            textView.setText(String.valueOf(count));

        } else {
            Toast.makeText(getDashboardActivity(), "You can not add more than 10 item in cart", Toast.LENGTH_SHORT).show();
        }
        //mCartList.get(pos).setQty(count);
        //setTotalAmount();
    }
    private void removeFromCart(TextView textView, int pos) {
        int count = Integer.parseInt(textView.getText().toString());
        if (count > MIN_LIMIT) {
            count--;
            textView.setText(String.valueOf(count));

        } else {
            count = 0;
            Toast.makeText(getDashboardActivity(), "Your cart is already empty.", Toast.LENGTH_SHORT).show();
        }
        //mCartList.get(pos).setQty(count);

        //setTotalAmount();

    }
}
