package com.app.community.ui.newspaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.app.community.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajnikant on 24/01/18.
 */

public class SelectedDaysAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflator;
    private final Context context;

    public SelectedDaysAdapter(Context context, List<String> daysList) {
        super(context, 0, daysList);
        this.context=context;
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String days = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = mInflator.inflate(R.layout.spinner_row, parent, false);
        }
        TextView tvDays = convertView.findViewById(R.id.tvDays);
        if(position==getCount()){
           tvDays.setBackgroundResource(0);
        }

        tvDays.setText(days);
        return convertView;
    }
    @Override
    public int getCount() {
        return super.getCount()-1; // you dont display last item. It is used as hint.
    }
}
