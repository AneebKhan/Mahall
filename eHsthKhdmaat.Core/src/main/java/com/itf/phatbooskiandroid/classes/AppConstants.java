package com.itf.phatbooskiandroid.classes;

import com.itf.phatbooskiandroid.enums.AppStatus;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public class AppConstants {
    private static AppConstants mInstance;

    //use this method when you want the reference
    public static AppConstants getInstance() {
        //initializing your singleton if it is null
        //is a good thing to do in getInstance because
        //now you can see if your singleton is actually being reinitialized.
        //e.g. after the application startup. Makes debugging it a bit easier.
        if(mInstance == null) mInstance = new AppConstants();

        return mInstance;
    }

    //and this one if you want a new instance
    public static AppConstants init() {
        mInstance = new AppConstants();
        return mInstance;
    }

    public AppStatus appstatus = AppStatus.UNDEFINED;

    public int pstatus = 20;
}
