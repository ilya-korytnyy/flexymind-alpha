package com.flexymind.alpha.game;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.flexymind.alpha.R;

public class StartGameDialog extends Dialog {

    public StartGameDialog(Context context) {
        super(context);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgamedialog);
        setTitle("Let's play!");

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
            StartGameDialog.this.cancel();
        }
    };



}
