package com.app.community.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;

import com.app.community.R;


/**
 * Created by arvind on 06/11/17.
 */

public class Alert {
    public static AlertDialog.Builder createYesNoAlert(Context context,
                                                       String title, String message, final OnAlertClickListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(
                new ContextThemeWrapper(context, R.style.CustomAlertDialogTheme));
        if (title != null)
            dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
            if (listener != null)
                listener.onPositive(dialogInterface);
        });
        dialog.setNegativeButton(android.R.string.no, (dialogInterface, i) -> {
            if (listener != null)
                listener.onNegative(dialogInterface);
        });
        return dialog;
    }
    public interface OnAlertClickListener {

        void onPositive(DialogInterface dialog);

        void onNegative(DialogInterface dialog);
    }
}
