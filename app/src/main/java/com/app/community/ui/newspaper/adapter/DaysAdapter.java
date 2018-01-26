package com.app.community.ui.newspaper.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.app.community.R;
import com.app.community.databinding.DaysRowItemBinding;
import com.app.community.ui.newspaper.Days;
import com.app.community.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by ashok on 25/12/17.
 */

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysHolder> {
    private final LayoutInflater mInflater;
    private final AppCompatActivity activity;
    private DaysRowItemBinding mBinding;
    private ArrayList<Days> daysList;

    public DaysAdapter(AppCompatActivity activity, ArrayList<Days> daysList) {
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.daysList = daysList;
    }

    @Override
    public DaysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.days_row_item, parent, false);
        return new DaysHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(DaysHolder holder, int position) {
        setStatus(holder);
        if (CommonUtils.isNotNull(daysList) && daysList.size() > position) {
            holder.setDays(daysList.get(position));
        }
        holder.layoutDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChecked(holder);
            }
        });
    }

    private void setStatus(DaysHolder holder) {
        if (holder.radioBtn.isChecked()) {
            holder.layoutDays.setBackground(activity.getResources().getDrawable(R.drawable.grey_bg));

        } else {
            holder.layoutDays.setBackground(activity.getResources().getDrawable(R.drawable.grey_corner_bg));

        }
    }

    private void setChecked(DaysHolder holder) {
        if (holder.radioBtn.isChecked()) {
            holder.radioBtn.setChecked(false);
            holder.layoutDays.setBackground(activity.getResources().getDrawable(R.drawable.grey_corner_bg));
        } else {
            holder.radioBtn.setChecked(true);
            holder.layoutDays.setBackground(activity.getResources().getDrawable(R.drawable.grey_bg));
        }
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isNotNull(daysList) ? daysList.size() : 0;
    }

    class DaysHolder extends RecyclerView.ViewHolder {
        private final RadioButton radioBtn;
        private final RelativeLayout layoutDays;
        private DaysRowItemBinding itemView;

        public DaysHolder(DaysRowItemBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
            radioBtn = itemView.radio;
            layoutDays = itemView.layoutDays;

        }

        public void setDays(Days days) {
            itemView.setDaysName(days);
        }
    }
}
