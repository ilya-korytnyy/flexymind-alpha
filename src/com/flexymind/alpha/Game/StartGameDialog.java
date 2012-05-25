package com.flexymind.alpha.Game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import com.flexymind.alpha.GameScreen;

public class StartGameDialog extends GameScreen {
    private static Dialog dialog;
    private static final int ID = 1;

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == ID) {
            AlertDialog.Builder startDialog = new AlertDialog.Builder(getApplicationContext());
            startDialog.setTitle("Let's game!" );
            startDialog.setPositiveButton("OK", null);
            dialog = startDialog.create();

            dialog.setOnShowListener( new OnShowListener() {

                @Override
                public void onShow(DialogInterface dialogInterface) {

                }
            });

            dialog.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    dialog.dismiss();
                }
            });

            dialog.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    dialog.dismiss();
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);


    }

    public void showStartDialog() {
        showDialog(ID);
    }
}
