package com.itf.phatbooskiandroid.activities;

import android.app.DownloadManager;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ift.ehsthkhdmaatcore.R;

import directives.ITFDrawerLayout;
import com.itf.phatbooskiandroid.fragments.AuthenticFragment;
import com.itf.phatbooskiandroid.fragments.CoreFragment;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;
import interfaces.IndicatorPresenter;
import managers.ApplicationStateManager;

import static com.itf.phatbooskiandroid.classes.CustomApplication.mFirebaseAnalytics;
import static com.itf.phatbooskiandroid.classes.ScreenNames.LOGIN_SCREEN;


/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public abstract class MasterActivity extends CoreActivity implements IndicatorPresenter, CoreFragment.OnFragmentInteractionListener {

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    private static final int sDELAY_MILLIS = 150;
    private final String TRANSACTION_FRAGMENT_TAG = "transactionFragment";
    protected final static String sKEY_ACTIONBAR_TITLE = "actionBarTitle";
    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    protected abstract @IdRes
    int getContainerId();

    private AppCompatDialog indicatorDialog;

    //endregion /* =================================== Class Variable =========================================== */

    //region /* ================================ Getter - Setter Method ======================================== */
    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDialog();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public void onBackPressed() {

            FragmentManager childFragmentManager = getFragmentManager();
            if (childFragmentManager.getBackStackEntryCount() > 1) {
                childFragmentManager.popBackStack();
            } else {
                //TODO: call dashboard activity or popup to close app
                super.onBackPressed();
            }
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    @Override
    public void showIndicator() {
        if (isFinishing()) {
            return;
        }

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (!indicatorDialog.isShowing() && !this.isFinishing()) {
            indicatorDialog.show();
        }
    }

    @Override
    public void hideIndicator() {
        if (this.isFinishing()) {
            return;
        }
        indicatorDialog.dismiss();
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */

    private void initDialog() {
        if (isFinishing()) {
            return;
        }
        View indicatorLayout = getLayoutInflater().inflate(R.layout.indicator_dialog, null, false);
        AppCompatDialog dialog = new AppCompatDialog(this, R.style.IndicatorDialogTheme);
        dialog.setCancelable(false);
        dialog.setContentView(indicatorLayout);
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        indicatorDialog = dialog;
    }

    protected void authenticateUserBeforeNavigate(@IdRes final int containerId, @NonNull final String nextScreen, Bundle nextScreenBundle) {
        UnAuthenticFragment fragment = null;
        try {
            fragment = (UnAuthenticFragment) (Class.forName("itf.ehsthkhdmaat.mahall.fragments." + LOGIN_SCREEN).newInstance());
            fragment.setNextScreen(nextScreen);

            if (nextScreenBundle != null)
                fragment.setNextScreenBundle(nextScreenBundle);

        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        final Fragment finalFragment = fragment;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_left, R.anim.slide_in_left);
                fragmentTransaction.replace(containerId, finalFragment, TRANSACTION_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }, sDELAY_MILLIS);
    }

    protected void replaceChildFragmentWithDelay(@IdRes final int containerId, @NonNull final String fragmentName,
                                                 final boolean goNext, final boolean goBack, Bundle params, final boolean addToBackStack) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) (Class.forName("itf.ehsthkhdmaat.mahall.fragments." + fragmentName).newInstance());

            if (!ApplicationStateManager.getInstance().getIsAuthenticated()) {
                if (fragment instanceof AuthenticFragment) {
                    authenticateUserBeforeNavigate(containerId, fragmentName, params);
                    return;
                }
            }

            if (params != null)
                fragment.setArguments(params);

        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        //Log page info into google analytics
        Bundle bundle = new Bundle();
//        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, fragment);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, fragmentName);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        final Fragment finalFragment = fragment;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (goNext) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (goBack) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
                } else if (!goNext && !goBack) {
                    fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                }

                fragmentTransaction.replace(containerId, finalFragment, TRANSACTION_FRAGMENT_TAG);
                if (addToBackStack)
                    fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        }, sDELAY_MILLIS);
    }


    public void downloadFile(String url, String extension, String downloadigntitle, String title, Context con) {
        Uri download_Uri = Uri.parse(url);
        DownloadManager downloadManager = (DownloadManager) getSystemService(con.DOWNLOAD_SERVICE);

        DownloadManager.Request request = new DownloadManager.Request(download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle(downloadigntitle);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/" + title + "." + extension);
        downloadManager.enqueue(request);
    }


    //endregion /* =================================== User Define Methods ===================================== */

}
