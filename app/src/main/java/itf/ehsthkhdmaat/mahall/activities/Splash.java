package itf.ehsthkhdmaat.mahall.activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import com.crashlytics.android.Crashlytics;
import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.activities.CoreActivity;
import com.itf.phatbooskiandroid.classes.CustomApplication;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import directives.ITFButton;
import directives.ITFGifImageView;
import directives.ITFImageView;
import interfaces.ServiceCallListener;

import io.fabric.sdk.android.Fabric;
import itf.ehsthkhdmaat.mahall.BuildConfig;
import itf.ehsthkhdmaat.mahall.R;
import keys.ConfigurationKeys;
import models.ConfigurationParameter.Response.Response;
import keys.GetTransactionKeys;
import managers.ConfigurationManager;
import managers.TransactionManager;
import utilities.GPSTracker;
import utilities.PopUpDialogClass;

import static keys.DelegateKeys.BTN_OK;
import static keys.DelegateKeys.DELEGATE_RESPONSE_FAILURE;
import static keys.GetTransactionKeys.TOP_RATED_VENUES;


/**
 * Created by aneebahmedkhan on 12/11/2018.
 * ITF
 */

public class Splash extends CoreActivity implements ServiceCallListener, PopUpDialogClass.interfaceButtonOptions {

    //region /* region====================================== Interface ========================================= */
    //endregion /* region====================================== Interface ====================================== */

    //region /* ================================== Constant Variable =========================================== */
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @BindView(R.id.gifImageView1)
    ITFGifImageView gifImageView;

    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    //endregion /* =================================== Class Variable =========================================== */

    //region /* ================================ Getter - Setter Method ======================================== */
    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        Bundle params = new Bundle();
        params.putString("LoginTime", "time_stamp");

        gifImageView.setGifImageResource(R.mipmap.splash_gif);

        gifImageView.setScaleX(1.2f);
        gifImageView.setScaleY(1.2f);

        GPSTracker.enableMyLocation(CustomApplication.getContext());

        //navigateActivityWithDelay();

        fetchConfigurationParameters(1);
    }
    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private void navigateActivityWithDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToIntent(DashboardActivity.class, true);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    @Override
    public <T> void onFailure(T errordetail) {
        if (errordetail instanceof Throwable &&
                ((Throwable) errordetail).getMessage().equals(getString(R.string.network_connectivity_issue))) {
            PopUpDialogClass popUpDialog = new PopUpDialogClass(this, getString(com.ift.ehsthkhdmaatcore.R.string.no_internet_error), BTN_OK, DELEGATE_RESPONSE_FAILURE);
            popUpDialog.setOptionButtonsListener(this);
            popUpDialog.show();
        }


    }

    @Override
    public <T> void onResponse(T response) {

        if (response instanceof ArrayList) {

            if (((ArrayList) response).size() > 0 && ((ArrayList) response).get(0) instanceof Response) {
                ArrayList<Response> arrayList = (ArrayList<Response>) response;

                ConfigurationManager.getInstance().setConfigurationData(arrayList);

                if (Boolean.parseBoolean(ConfigurationManager.getInstance().getValueFromConfigList(ConfigurationKeys.KEY_ANDROID_FORCE_UPDATE))) {
                    showAppUpdateDialog(Boolean.parseBoolean(ConfigurationManager.getInstance().getValueFromConfigList(ConfigurationKeys.KEY_ANDROID_FORCE_UPDATE)));
                    return;
                }
                if (isVersionLatest(BuildConfig.VERSION_NAME, ConfigurationManager.getInstance().getValueFromConfigList(ConfigurationKeys.KEY_ANDROID_VERSION))) {
                    navigateActivityWithDelay();
                } else {
                    showAppUpdateDialog(Boolean.parseBoolean(ConfigurationManager.getInstance().getValueFromConfigList(ConfigurationKeys.KEY_ANDROID_FORCE_UPDATE)));
                }
            }
        }

    }

    @Override
    public void buttonFirst(String delegate) {
        finish();
    }

    @Override
    public void buttonSecond(String delegate) {

    }

    private void fetchConfigurationParameters(int channelId) {

        Type responseType = new TypeToken<ArrayList<Response>>() {
        }.getType();
        List<Response> responses = new ArrayList<Response>();
        Class c1 = responses.getClass();
        TransactionManager<Response, ArrayList<Response>> trManager = TransactionManager.getInstance();
        trManager.getTransactionData(this, GetTransactionKeys.GET_CONFIGURATION_PARAMETERS + "/" + channelId, responseType);
    }

    public void showAppUpdateDialog(boolean isForceUpdate) {


        Dialog reviewDialog = new Dialog(Splash.this);
        reviewDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        reviewDialog.setCancelable(false);
        reviewDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        reviewDialog.setContentView(R.layout.dialog_check_updates);

        ITFButton btnUpdate = (ITFButton) reviewDialog.findViewById(R.id.btnUpdate);
        ITFButton btnCancel = (ITFButton) reviewDialog.findViewById(R.id.btnCancel);

        if (isForceUpdate) {
            btnCancel.setVisibility(View.GONE);
        } else {
            btnCancel.setVisibility(View.VISIBLE);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                reviewDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateActivityWithDelay();
                reviewDialog.dismiss();
            }
        });


        reviewDialog.show();
    }

    private boolean isVersionLatest(String appVersion, String serverVersion) {

        int appVersionInt = Integer.parseInt(appVersion.replace(".", ""));
        int serverVersionInt = Integer.parseInt(serverVersion.replace(".", ""));


        if (serverVersionInt > appVersionInt) {
            return false;
        }

        return true;

    }

    //endregion /* =================================== User Define Methods ===================================== */

}
