package com.edge.checkmyattendance.Dialogs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Edeilson Silva on 24/04/2017.
 */

public class DialogsUtil{

    static ProgressDialog dialog;

    public DialogsUtil(){};

    public void progressDialogs(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Please wait");
        dialog.show();
    }
}
