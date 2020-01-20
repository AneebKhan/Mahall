package com.itf.phatbooskiandroid.classes;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import service.remote.sharedclass.TLSSocketFactory;
import utilities.LogUtils;


/**
 * Created by zahmed on 2/26/2018.
 */

public class CustomApplication extends Application {

    private static Context mContext;
    public static TLSSocketFactory sTLSSocketFactory;
    public static String Current_Culture = "en-US";
    public static FirebaseAnalytics mFirebaseAnalytics;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // init TLS for webCalls
        try {

            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle params = new Bundle();
            params.putString("LoginTime","time_stamp");
            mFirebaseAnalytics.logEvent("Logged_in",params);


            sTLSSocketFactory = new TLSSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            LogUtils.LOGStackTrace(e);
        }
    }

    public static Context getContext(){
        return mContext;
    }
}