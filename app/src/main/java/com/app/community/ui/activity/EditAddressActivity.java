package com.app.community.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.app.community.R;
import com.app.community.databinding.ActivityEditAddressBinding;
import com.app.community.network.request.EditAddress;
import com.app.community.network.response.BaseResponse;
import com.app.community.ui.activity.adapter.EditAddressAdapter;
import com.app.community.ui.authentication.CommonActivity;
import com.app.community.ui.base.MvpView;
import com.app.community.ui.dashboard.home.event.UpdateAddress;
import com.app.community.utils.CommonUtils;
import com.app.community.utils.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by arvind on 06/11/17.
 */

public class EditAddressActivity extends CommonActivity implements MvpView, View.OnClickListener, EditAddressAdapter.EditListener {
    private ActivityEditAddressBinding mBinding;
    private ArrayList<EditAddress> editList = new ArrayList<>();
    private EditAddressAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_address);
        initializeData();
        setListener();
    }

    public void setListener() {
        mBinding.layoutHeader.textView.setOnClickListener(this);
        mBinding.layoutHeader.ivBack.setOnClickListener(this);
    }

    public void initializeData() {
        mBinding.layoutHeader.tvHeader.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.tvHeader.setText(getResources().getString(R.string.edit_address));
        mBinding.layoutHeader.textView.setVisibility(View.VISIBLE);
        mBinding.layoutHeader.textView.setText(getResources().getString(R.string.update));
        setList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new EditAddressAdapter(this, editList, this);
        mBinding.rvEditAddress.setLayoutManager(linearLayoutManager);
        mBinding.rvEditAddress.setAdapter(adapter);

    }

    private void setList() {
        EditAddress address = new EditAddress();
        address.setAddress("1");
        editList.add(address);
        EditAddress address1 = new EditAddress();
        address1.setAddress("2");
        editList.add(address1);
        EditAddress address2 = new EditAddress();
        address2.setAddress("3");
        editList.add(address2);
        EditAddress address3 = new EditAddress();
        address3.setAddress("4");
        editList.add(address3);
        EditAddress address4 = new EditAddress();
        address4.setAddress("5");
        editList.add(address4);
        EditAddress address5 = new EditAddress();
        address5.setAddress("uigdsyfkbRam Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address5);
        EditAddress address6 = new EditAddress();
        address6.setAddress("Ram Naegar New jkhsdjkhfds Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address6);
        EditAddress address7 = new EditAddress();
        address7.setAddress("Ram klndsfksdlkkndfsfn Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address7);
        EditAddress address8 = new EditAddress();
        address8.setAddress("Ram Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address8);
    }

    @Override
    public void onSuccess(BaseResponse response, int requestCode) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onClick(View view) {
        if (view == mBinding.layoutHeader.ivBack) {
            finish();
        } else if (view == mBinding.layoutHeader.textView) {
            if (CommonUtils.isNotNull(mBinding.edNewAddress.getText().toString()) && mBinding.edNewAddress.getText().toString().length() > 0) {
                PreferenceUtils.setAddress(mBinding.edNewAddress.getText().toString());
            } else {
                for (int i = 0; i < editList.size(); i++) {
                    if (CommonUtils.isNotNull(editList.get(i)) && editList.get(i).isSelected()) {
                        PreferenceUtils.setAddress(editList.get(i).getAddress());
                    }
                }
            }
            EventBus.getDefault().post(new UpdateAddress());
            finish();
        }
    }


    @Override
    public void onItemClick(int position) {
        for (int i = 0; i < editList.size(); i++) {
            if (i == position) {
                EditAddress address = editList.get(i);
                address.setSelected(true);
                editList.set(i, address);
            } else {
                EditAddress address = editList.get(i);
                address.setSelected(false);
                editList.set(i, address);
            }
        }
        adapter.notifyDataSetChanged();

    }
}
