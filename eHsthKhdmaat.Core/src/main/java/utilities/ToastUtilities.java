package utilities;

import android.widget.Toast;

import com.itf.phatbooskiandroid.classes.CustomApplication;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */


public class ToastUtilities {
    public static void toastMessage(String message)
    {
        Toast.makeText(CustomApplication.getContext(),message,Toast.LENGTH_LONG).show();
    }


}
