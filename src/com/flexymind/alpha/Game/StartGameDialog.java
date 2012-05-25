package com.flexymind.alpha.Game;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.flexymind.alpha.R;
import com.flexymind.alpha.customviews.NoteBoard;

public class StartGameDialog extends Dialog {
    private NoteBoard noteBoard;

    public StartGameDialog(Context context, NoteBoard noteBoard) {
        super(context);
        this.noteBoard = noteBoard;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgamedialog);
        setTitle("Let's game!");

        Button OKButton = (Button) findViewById(R.id.okbutton);
        OKButton.setOnClickListener(OKListener);

        setCanceledOnTouchOutside(true);
        setOnCancelListener(cancelListener);
    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    private OnCancelListener cancelListener = new OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            StartGameDialog.this.cancel();
        }
    };

    private View.OnClickListener OKListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            noteBoard.showEverythingNeeded();
            StartGameDialog.this.cancel();
        }
    };



}

/*
            AlertDialog.Builder startDialog = new AlertDialog.Builder(getApplicationContext());

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
    */