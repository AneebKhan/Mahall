package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itf.phatbooskiandroid.adapters.ITFRecyclerViewAdapter;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import directives.ITFEditText;
import itf.ehsthkhdmaat.mahall.R;
import itf.ehsthkhdmaat.mahall.adapters.FavouritesAdapter;
import itf.ehsthkhdmaat.mahall.classes.keys.BundleKeys;
import itf.ehsthkhdmaat.mahall.enums.TransactionStates;
import itf.ehsthkhdmaat.mahall.interfaces.onFavouriteClickedListener;
import itf.ehsthkhdmaat.mahall.models.bundle.SnackBarBundle;
import itf.ehsthkhdmaat.mahall.models.bundle.SubCategoryDetailsBundle;
import itf.ehsthkhdmaat.mahall.models.service_model.favourites.Response;
import managers.ApplicationStateManager;
import managers.PreferenceManager;
import managers.TransactionManager;
import utilities.LogUtils;

import static com.itf.phatbooskiandroid.classes.ScreenNames.DEFAULT_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.FORGET_PASSWORD_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.SIGN_UP_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_DETAILS_FRAGMENT;
import static keys.GetTransactionKeys.GET_FAVOURITES;
import static keys.GetTransactionKeys.REMOVE_FAVOURITE;
import static keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static keys.PreferenceKeys.KEY_PASSWORD;
import static keys.PreferenceKeys.KEY_REMEMBER_ME;
import static keys.PreferenceKeys.KEY_USER;
import static keys.PreferenceKeys.KEY_USERNAME;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class LoginFragment extends UnAuthenticFragment {

    @BindView(R.id.edtEmail)
    ITFEditText edtEmail;

    @BindView(R.id.edtPassword)
    ITFEditText edtPassword;

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.features_screen)
    String dashboard;

    /**
     * Duration of wait
     **/
    private final int SIMULATE_VERIFY_LOGIN_LENGTH = 3000;

    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */


    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public LoginFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setPageMenuData() {

    }

    @Override
    protected String getPageTitle() {
        return dashboard;

    }


    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this BooskiMartShopListFragment
        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */


    @Override
    public <T> void onFailure(T errordetail) {
        super.onFailure(errordetail);
        hideIndicator();

    }

    @Override
    public <T> void onResponse(T response) {

        hideIndicator();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    @OnClick(R.id.tvForgetPassword)
    public void onForgotClicked(View view) {
        replaceChildFragmentWithDelay(FORGET_PASSWORD_SCREEN, true, false, null, true);
    }

    @OnClick(R.id.btnLogin)
    public void onLoginClicked() {
        attemptLogin();
    }

    @OnClick(R.id.txtSignUp)
    public void onSignUpClicked() {
        replaceChildFragmentWithDelay(SIGN_UP_SCREEN, true, false, null, true);
    }


    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private void attemptLogin() {
        // Reset errors.
        edtEmail.setError(null);
        edtPassword.setError(null);

        // Store values at the time of the login attempt.
        String userid = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid username
        if (TextUtils.isEmpty(userid)) {
            edtEmail.setError(getString(R.string.login_error_field_required));
            focusView = edtEmail;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            edtPassword.setError(getString(R.string.login_error_invalid_password));
            focusView = edtPassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            showIndicator();
            try {
                /*Request request = new Request(userid, password);
                loginRequest(request);*/

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ApplicationStateManager.getInstance().setIsAuthenticated(true);

                        PreferenceManager.getInstance().put(KEY_USERNAME, edtEmail.getText().toString());
                        PreferenceManager.getInstance().put(KEY_PASSWORD, edtPassword.getText().toString());
                        PreferenceManager.getInstance().put(KEY_CUSTOMER_ID, "1");

                        /*Gson gson = new Gson();
                        String userJson = gson.toJson(srvListResponse.getResponseObject());

                        PreferenceManager.getInstance().put(KEY_USER, userJson);*/

                        //save username and password in preferences
                        PreferenceManager.getInstance().put(KEY_REMEMBER_ME, true);

                        hideIndicator();

                        if (getNextScreen() != null)
                            replaceChildFragmentWithDelay(getNextScreen(), false, true, getNextScreenBundle(), true);
                        else
                            replaceChildFragmentWithDelay(DEFAULT_SCREEN, false, true, null, true);
                    }
                }, SIMULATE_VERIFY_LOGIN_LENGTH);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }
    //endregion /* =================================== User Define Methods ===================================== */


}
