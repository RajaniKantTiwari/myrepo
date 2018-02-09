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

    }

    public void initializeData() {
        setList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new EditAddressAdapter(this, editList, this);
        mBinding.rvEditAddress.setLayoutManager(linearLayoutManager);
        mBinding.rvEditAddress.setAdapter(adapter);

    }

    private void setList() {
        EditAddress address = new EditAddress();
        address.setAddress("Ram Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address);
        EditAddress address1 = new EditAddress();
        address1.setAddress("Ramesh Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address1);
        EditAddress address2 = new EditAddress();
        address2.setAddress("Ramksdkfkfs Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address2);
        EditAddress address3 = new EditAddress();
        address3.setAddress("Ram Naegar.ksdk New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
        editList.add(address3);
        EditAddress address4 = new EditAddress();
        address4.setAddress(",nsd,mfn,mnRam Naegar New Delhi PinCode 1333j kjhsfkdjh, bcjgkjfdhkjdckjcbk");
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
        switch (view.getId()) {

        }
    }


    @Override
    public void onItemClick(int position) {
        for (int i = 0; i < editList.size(); i++) {
            if (i == position) {
                EditAddress address = editList.get(position);
                address.setSelected(true);
                editList.set(i, address);
            } else {
                EditAddress address = editList.get(position);
                address.setSelected(false);
                editList.set(i, address);
            }
        }
        adapter.notifyDataSetChanged();

    }
}
