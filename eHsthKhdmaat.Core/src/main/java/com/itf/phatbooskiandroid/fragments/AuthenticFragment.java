package com.itf.phatbooskiandroid.fragments;

import android.os.Bundle;

import managers.ApplicationStateManager;

public abstract class AuthenticFragment extends CoreFragment {
//region /* ====================================== Interface =============================================== */
    //endregion /* ====================================== Interface ============================================ */

    //region /* ================================== Constant Variable =========================================== */
    //endregion  /* ================================== Constant Variable ======================================= */

    //region /* =================================== Class Variable ============================================= */
    //endregion /* =================================== Class Variable ========================================== */

    //region /* =================================== Constructors ============================================ */
    //endregion /* =================================== Constructors ============================================ */

    //region /* ================================ Getter - Setter Method ======================================== */
    //endregion /* ================================ Getter - Setter Method ===================================== */

    //region /* ================================== Life Cycle Method =========================================== */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!ApplicationStateManager.getInstance().getIsAuthenticated())
        {
            authenticateUserBeforeNavigate(this.getClass().getSimpleName(), getArguments());
        }
    }
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
