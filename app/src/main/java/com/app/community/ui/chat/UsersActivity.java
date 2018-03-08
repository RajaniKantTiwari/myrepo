package com.app.community.ui.chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.community.R;
import com.app.community.databinding.ActivityUsersBinding;
import com.app.community.utils.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UsersActivity extends AppCompatActivity implements UserAdapter.UsersListener {

    private ActivityUsersBinding mBinding;
    ArrayList<String> userList = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog progressDialog;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        initializeUserView();
        callApi();
    }

    private void initializeUserView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rvUsersList.setLayoutManager(layoutManager);
        mAdapter = new UserAdapter(this, this);

    }

    private void callApi() {
        progressDialog = new ProgressDialog(UsersActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String url = AppConstants.FIREBASE_BASE_URL + AppConstants.FIREBASE_USER;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String userJson) {
                doOnSuccess(userJson);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(UsersActivity.this);
        rQueue.add(request);

       /* usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = userList.get(position);
                startActivity(new Intent(UsersActivity.this, ChatActivity.class));
            }
        });*/
    }

    public void doOnSuccess(String userJson) {
        try {
            userList.clear();
            JSONObject obj = new JSONObject(userJson);
            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();

                if (!key.equals(UserDetails.username)) {
                    userList.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (totalUsers <= 1) {
            mBinding.layoutNoData.layoutNoData.setVisibility(View.VISIBLE);
            mBinding.layoutNoData.tvNoData.setText(getResources().getString(R.string.no_user_found));
            mBinding.rvUsersList.setVisibility(View.GONE);
        } else {
            mBinding.layoutNoData.layoutNoData.setVisibility(View.GONE);
            mBinding.rvUsersList.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }

        progressDialog.dismiss();
    }

    @Override
    public void onUsersClick(int position) {
        UserDetails.chatWith = userList.get(position);
        startActivity(new Intent(UsersActivity.this, ChatActivity.class));
    }
}