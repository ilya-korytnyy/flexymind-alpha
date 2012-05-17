package com.flexymind.alpha.startbutton;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.flexymind.alpha.R;

public class StartDialog extends Dialog {

    private View btn;

    public StartDialog(Context context) {
        super(context);
        //setContentView(R.layout.startlayout);
        Window w = this.getWindow();

        w.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        setContentView(R.layout.startlayout);

    }





}
