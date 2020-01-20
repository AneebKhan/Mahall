package utilities;


import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.itf.phatbooskiandroid.classes.CustomApplication;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */


public class UIControlsUtils {

    public static void showControl(View v)
    {
        v.setVisibility(View.VISIBLE);
    }

    public static void hideControl(View v)
    {
        v.setVisibility(View.GONE);
    }

    public static LayoutInflater getCustomLayoutInflater(Fragment fragment)
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return fragment.getLayoutInflater();
        }
        else
        {
            return LayoutInflater.from(CustomApplication.getContext());
        }
    }

}
