package com.app.community.ui.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.app.community.R;

public class AlertDFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				// Set Dialog Icon
				.setIcon(R.drawable.app_icon)
				// Set Dialog Title
				.setTitle("Alert CustomDialogFragment")
				// Set Dialog Message
				.setMessage("Alert CustomDialogFragment Tutorial")
				
				// Positive button
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Do something else
					}
				})
				
				// Negative Button
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,	int which) {
						// Do something else
					}
				}).create();
	}
}