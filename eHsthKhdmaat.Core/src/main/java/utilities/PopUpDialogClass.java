package utilities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import com.ift.ehsthkhdmaatcore.R;

import directives.ITFButton;
import directives.ITFTextView;

public class PopUpDialogClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public ITFButton btnOptionFirst, btnOptionSecond;
    public ITFTextView tvHeading;

    String dialogHeading;
    String btnStringFirst;
    String btnStringSecond;
    String delegate;

    public PopUpDialogClass(Activity a) {
        super(a);
        this.c = a;
    }

    public PopUpDialogClass(Activity a, String heading, String firstButtonName, String _delegate) {
        super(a);
        this.c = a;
        this.dialogHeading = heading;
        this.btnStringFirst = firstButtonName;
        this.delegate = _delegate;
    }

    public PopUpDialogClass(Activity a, String heading, String firstButtonName, String secondButtonName, String _delegate) {
        super(a);
        this.c = a;
        this.dialogHeading = heading;
        this.btnStringFirst = firstButtonName;
        this.btnStringSecond = secondButtonName;
        this.delegate = _delegate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_dialog);
        btnOptionFirst = (ITFButton) findViewById(R.id.btnOptionYes);
        btnOptionSecond = (ITFButton) findViewById(R.id.btnOptionNo);
        tvHeading = (ITFTextView) findViewById(R.id.tvHeading);

        btnOptionFirst.setText(btnStringFirst);
        btnOptionSecond.setText(btnStringSecond);

        if (btnStringSecond == null)
            btnOptionSecond.setVisibility(View.GONE);

        btnOptionFirst.setOnClickListener(this);
        btnOptionSecond.setOnClickListener(this);

        tvHeading.setText(dialogHeading);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnOptionYes) {
            if (interfaceButtonOptionsListener != null) {
                interfaceButtonOptionsListener.buttonFirst(delegate);
            }


        } else if (i == R.id.btnOptionNo) {
            if (interfaceButtonOptionsListener != null) {
                interfaceButtonOptionsListener.buttonSecond(delegate);
            }


        } else {
        }
        dismiss();
    }

    interfaceButtonOptions interfaceButtonOptionsListener;

    public void setOptionButtonsListener(interfaceButtonOptions interfaceBottomSheet){
        this.interfaceButtonOptionsListener = interfaceBottomSheet;
    }


    public interface interfaceButtonOptions {
        void buttonFirst(String delegate);
        void buttonSecond(String delegate);
    }

}
