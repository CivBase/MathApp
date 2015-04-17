package com.toomanycooksapp.mathapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class Popup {
    public static void create(
            Context context, String title, String message, String buttonText,
            DialogInterface.OnClickListener onClickListener) {

        Popup.create(context, title, message, buttonText, onClickListener, null);
    }

    public static void create(
            Context context, String title, String message, String buttonText,
            DialogInterface.OnClickListener onClickListener, Integer iconID) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(buttonText, onClickListener);
        if (iconID != null) {
            alert.setIcon(iconID);
        }

        AlertDialog popup = alert.create();
        popup.show();
    }
}
