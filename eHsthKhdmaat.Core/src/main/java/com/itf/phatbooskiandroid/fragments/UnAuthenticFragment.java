package com.itf.phatbooskiandroid.fragments;

import android.os.Bundle;

public abstract class UnAuthenticFragment extends CoreFragment {

    //region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    private String mNextScreen;
    private Bundle mNextScreenBundle;
    //endregion /* =================================== Class Variable ========================================== */

    //region /* =================================== Constructors ============================================ */
    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    protected String getNextScreen()
    {
        return mNextScreen;
    }

    public void setNextScreen(String mNextScreen)
    {
        this.mNextScreen = mNextScreen;
    }

    protected Bundle getNextScreenBundle() {
        return mNextScreenBundle;
    }

    public void setNextScreenBundle(Bundle mNextScreenBundle) {
        this.mNextScreenBundle = mNextScreenBundle;
    }
    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    //endregion /* ================================== Life Cycle Method ======================================== */

    //region /* ============================= Implemented Interface Method ===================================== */
    //endregion /* ============================= Implemented Interface Method ================================== */

    //region /* ============================= Implemented Abstract Method ====================================== */
    //endregion /* ============================= Implemented Abstract Method =================================== */

    //region /* ==================================== OnClick Methods =========================================== */
    //endregion /* ==================================== OnClick Methods ======================================== */

    //region /* =================================== User Define Methods ======================================== */
    //endregion /* =================================== User Define Methods ===================================== */




}
