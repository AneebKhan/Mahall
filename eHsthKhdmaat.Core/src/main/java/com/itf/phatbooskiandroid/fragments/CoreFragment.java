package com.itf.phatbooskiandroid.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ift.ehsthkhdmaatcore.R;

import com.itf.phatbooskiandroid.activities.MasterActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.itf.phatbooskiandroid.classes.CustomApplication;
import com.itf.phatbooskiandroid.classes.ScreenNames;
import com.itf.phatbooskiandroid.enums.TransactionStates;

import interfaces.IndicatorPresenter;
import interfaces.OnDisplaySnackBarClickedListener;
import interfaces.ServiceCallListener;
import models.Bundles.SnackBarBundle;
import utilities.PopUpDialogClass;

import static keys.DelegateKeys.BTN_OK;
import static keys.DelegateKeys.DELEGATE_RESPONSE_FAILURE;

public abstract class CoreFragment extends Fragment implements ServiceCallListener, IndicatorPresenter {
    //region /* ====================================== Interface =============================================== */
    public interface OnFragmentInteractionListener {
        void onNavigateNextFragment(@NonNull final String fragmentName,
                                    final boolean goNext, final boolean goBack, Bundle params, final boolean addToBackStack);

        void onAuthenticateUserBeforeNavigate(@NonNull final String nextScreen, Bundle nextScreenBundle);

        void setPageTitle(String title);

        void setSearchIcon(boolean state);


    }

    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    public final String disable_feature_demo = CustomApplication.getContext().getString(R.string.disable_feature_demo);
    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    private IndicatorPresenter indicatorPresenter;
    // Variable for Unbinder
    private Unbinder unbinder;
    private OnFragmentInteractionListener mListener;
    private OnDisplaySnackBarClickedListener snackBarListener;

    //endregion /* =================================== Class Variable ========================================== */

    //region  /* =================================== Constructors ============================================== */
    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    protected abstract int getLayoutResourceId();

    protected abstract void setPageMenuData();

    protected  abstract String getPageTitle();



    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResourceId(), container, false);

        unbinder = ButterKnife.bind(this, rootView);

        //setPageMenuData();

        //setSearchIconImage(false);

        //mListener.setPageTitle(getPageTitle());



        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        indicatorPresenter = (MasterActivity) context;

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }

        if (context instanceof OnDisplaySnackBarClickedListener) {
            snackBarListener = (OnDisplaySnackBarClickedListener) context;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Unbind the views
        unbinder.unbind();
    }

    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    @Override
    public void showIndicator() {
        indicatorPresenter.showIndicator();
    }

    @Override
    public void hideIndicator() {
        indicatorPresenter.hideIndicator();
    }

    @Override
    public <T> void onFailure(T errordetail) {
        hideIndicator();
        if (errordetail instanceof Throwable && ((Throwable) errordetail).getMessage().equals(getString(com.ift.ehsthkhdmaatcore.R.string.network_connectivity_issue))) {
            PopUpDialogClass popUpDialog= new PopUpDialogClass(getActivity(), getString(R.string.no_internet_error), BTN_OK, DELEGATE_RESPONSE_FAILURE);
            popUpDialog.show();
        } else if (errordetail instanceof Throwable && ((Throwable) errordetail).getMessage().equals(getString(com.ift.ehsthkhdmaatcore.R.string.error_transaction_not_authentic))) {
            showSnackBarMessage();
        }
    }
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    protected void replaceChildFragmentWithDelay(@NonNull final String fragmentName,
                                                 final boolean goNext, final boolean goBack, Bundle params, final boolean addToBackStack) {
        if (mListener != null) {
            mListener.onNavigateNextFragment(fragmentName, goNext, goBack, params, addToBackStack);


        }
    }

    protected void authenticateUserBeforeNavigate(@NonNull final String nextScreen, Bundle nextScreenBundle) {
        if (mListener != null) {
            mListener.onAuthenticateUserBeforeNavigate(nextScreen, nextScreenBundle);
        }
    }

    protected void setSearchIconImage(boolean state){

        if (mListener !=null){

            mListener.setSearchIcon(state);
        }

    }

    public void navigateBackFragment() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {

            fm.popBackStack();
        }
    }

    protected <T> void showSnackBar(T obj) {
        if (snackBarListener != null) {
            snackBarListener.onSnackBarClicked(obj);


        }
    }


    private void showSnackBarMessage() {
        SnackBarBundle snackBarBundle = new SnackBarBundle();
        snackBarBundle.setMessage(getString(R.string.error_authentication));
        snackBarBundle.setSuccess(true);
        snackBarBundle.setAction(TransactionStates.WARNING);
        snackBarBundle.setNavigateTo(ScreenNames.LOGIN_SCREEN);
        snackBarBundle.setNavigationText(getString(R.string.btn_login));
        showSnackBar(snackBarBundle);
    }




    //endregion /* =================================== User Define Methods ===================================== */


}
