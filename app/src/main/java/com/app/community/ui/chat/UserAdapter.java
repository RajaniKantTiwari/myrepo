package com.app.community.ui.chat;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.community.R;
import com.app.community.databinding.UserItemRowBinding;


/**
 * Created by ashok on 25/12/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private UsersListener listener;
    public interface UsersListener {
        void onUsersClick(int position);
    }
    public UserAdapter(AppCompatActivity activity, UsersListener listener) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.listener=listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemRowBinding mBinding = DataBindingUtil.inflate(mInflater, R.layout.user_item_row, parent, false);
        return new UserViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final UserItemRowBinding mBinding;
        private ImageView productImage;

        public UserViewHolder(UserItemRowBinding itemView) {
            super(itemView.getRoot());
            mBinding = itemView;
            mBinding.getRoot().setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            listener.onUsersClick(getAdapterPosition());
        }
    }
}
