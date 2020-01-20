package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import directives.ITFEditText;
import itf.ehsthkhdmaat.mahall.R;
import managers.ApplicationStateManager;
import managers.PreferenceManager;

import static com.itf.phatbooskiandroid.classes.ScreenNames.DEFAULT_SCREEN;
import static com.itf.phatbooskiandroid.classes.ScreenNames.LOGIN_SCREEN;
import static keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static keys.PreferenceKeys.KEY_PASSWORD;
import static keys.PreferenceKeys.KEY_REMEMBER_ME;
import static keys.PreferenceKeys.KEY_USERNAME;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class SignUpFragment extends UnAuthenticFragment {

    @BindView(R.id.edtEmail)
    ITFEditText edtEmail;

    @BindView(R.id.edtUsername)
    ITFEditText edtUsername;

    @BindView(R.id.edtPassword)
    ITFEditText edtPassword;

    @BindView(R.id.edtPhone)
    ITFEditText edtPhone;

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.features_screen)
    String dashboard;

    private final int SIMULATE_VERIFY_LOGIN_LENGTH = 3000;

    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */


    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public SignUpFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_sign_up;
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
    @OnClick(R.id.btnSignUp)
    public void onSignUpClicked() {

        if(!errorInFields()) {

            showIndicator();
            try {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ApplicationStateManager.getInstance().setIsAuthenticated(true);

                        PreferenceManager.getInstance().put(KEY_USERNAME, edtEmail.getText().toString());
                        PreferenceManager.getInstance().put(KEY_PASSWORD, edtPassword.getText().toString());
                        PreferenceManager.getInstance().put(KEY_CUSTOMER_ID, "1");

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

    @OnClick(R.id.txtLogin)
    public void onLoginClicked() {
        replaceChildFragmentWithDelay(LOGIN_SCREEN, true, false, null, true);
    }
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    private boolean errorInFields() {

        if(edtUsername.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Please enter valid username.", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(edtEmail.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Please enter valid Email Address", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(!(edtEmail.getText().toString().matches(getResources().getString(R.string.email_pattern_validation)))) {
            Toast.makeText(getContext(), "Please enter valid Email Address", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(edtPassword.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Please enter valid password.", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(edtPhone.getText().toString().matches("")) {
            Toast.makeText(getContext(), "Please enter valid phone number.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;

    }
    //endregion /* =================================== User Define Methods ===================================== */


}
