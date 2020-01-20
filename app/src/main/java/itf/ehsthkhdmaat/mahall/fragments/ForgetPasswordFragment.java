package itf.ehsthkhdmaat.mahall.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itf.phatbooskiandroid.fragments.UnAuthenticFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import directives.ITFEditText;
import itf.ehsthkhdmaat.mahall.R;

import static com.itf.phatbooskiandroid.classes.ScreenNames.VENUE_DETAILS_FRAGMENT;

/**
 * Created by aneebahmed on 12/16/2018.
 * ITF
 */

public class ForgetPasswordFragment extends UnAuthenticFragment {

    @BindView(R.id.edtEmail)
    ITFEditText edtEmail;

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    @BindString(R.string.features_screen)
    String dashboard;

    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */


    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    public ForgetPasswordFragment() {
        // Required empty public constructor
    }

    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_forget_password;
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
    @OnClick(R.id.btnSend)
    public void onSendClicked() {

    }
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    //endregion /* =================================== User Define Methods ===================================== */


}
