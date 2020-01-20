package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/5/2018.
 */

public class AuthorizationInfo {

    @SerializedName("IsAdmin")
    @Expose
    private boolean isAdmin;
    @SerializedName("IsApprover")
    @Expose
    private boolean isApprover;
    @SerializedName("IsInserter")
    @Expose
    private boolean isInserter;
    @SerializedName("IsViewer")
    @Expose
    private boolean isViewer;
    @SerializedName("IsCreditCardUser")
    @Expose
    private boolean isCreditCardUser;
    @SerializedName("NeedFinancialLimitCheck")
    @Expose
    private boolean needFinancialLimitCheck;

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsApprover() {
        return isApprover;
    }

    public void setIsApprover(boolean isApprover) {
        this.isApprover = isApprover;
    }

    public boolean isIsInserter() {
        return isInserter;
    }

    public void setIsInserter(boolean isInserter) {
        this.isInserter = isInserter;
    }

    public boolean isIsViewer() {
        return isViewer;
    }

    public void setIsViewer(boolean isViewer) {
        this.isViewer = isViewer;
    }

    public boolean isIsCreditCardUser() {
        return isCreditCardUser;
    }

    public void setIsCreditCardUser(boolean isCreditCardUser) {
        this.isCreditCardUser = isCreditCardUser;
    }

    public boolean isNeedFinancialLimitCheck() {
        return needFinancialLimitCheck;
    }

    public void setNeedFinancialLimitCheck(boolean needFinancialLimitCheck) {
        this.needFinancialLimitCheck = needFinancialLimitCheck;
    }

}
