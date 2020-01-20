package com.itf.phatbooskiandroid.models.uimodels.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.itf.phatbooskiandroid.models.uimodels.common.Amount;
import com.itf.phatbooskiandroid.models.uimodels.common.TwoFactorAuthentication;


/**
 * Created by zahmed on 3/22/2018.
 */

public class RequestTransactionData {
    @SerializedName("OpenCaseAndExecuteTransaction")
    @Expose
    private boolean openCaseAndExecuteTransaction;
    @SerializedName("OpenConditionalCase")
    @Expose
    private boolean openConditionalCase;
    @SerializedName("OpenCaseAndNotExecute")
    @Expose
    private boolean openCaseAndNotExecute;
    @SerializedName("DisableNonStp")
    @Expose
    private boolean disableNonStp;
    @SerializedName("IsSourceFutureDated")
    @Expose
    private boolean isSourceFutureDated;
    @SerializedName("SelectedApprovalRule")
    @Expose
    private int selectedApprovalRule;
    @SerializedName("SelectedApprovalSubRule")
    @Expose
    private int selectedApprovalSubRule;
    @SerializedName("TwoFactorAuthentication")
    @Expose
    private TwoFactorAuthentication twoFactorAuthentication;
    @SerializedName("ApprovalHtml")
    @Expose
    private String approvalHtml;
    @SerializedName("StateValue")
    @Expose
    private String stateValue;
    @SerializedName("CRMReferenceNumber")
    @Expose
    private String cRMReferenceNumber;
    @SerializedName("Fees")
    @Expose
    private Amount fees;

    public boolean isOpenCaseAndExecuteTransaction() {
        return openCaseAndExecuteTransaction;
    }

    public void setOpenCaseAndExecuteTransaction(boolean openCaseAndExecuteTransaction) {
        this.openCaseAndExecuteTransaction = openCaseAndExecuteTransaction;
    }

    public boolean isOpenConditionalCase() {
        return openConditionalCase;
    }

    public void setOpenConditionalCase(boolean openConditionalCase) {
        this.openConditionalCase = openConditionalCase;
    }

    public boolean isOpenCaseAndNotExecute() {
        return openCaseAndNotExecute;
    }

    public void setOpenCaseAndNotExecute(boolean openCaseAndNotExecute) {
        this.openCaseAndNotExecute = openCaseAndNotExecute;
    }

    public boolean isDisableNonStp() {
        return disableNonStp;
    }

    public void setDisableNonStp(boolean disableNonStp) {
        this.disableNonStp = disableNonStp;
    }

    public boolean isIsSourceFutureDated() {
        return isSourceFutureDated;
    }

    public void setIsSourceFutureDated(boolean isSourceFutureDated) {
        this.isSourceFutureDated = isSourceFutureDated;
    }

    public int getSelectedApprovalRule() {
        return selectedApprovalRule;
    }

    public void setSelectedApprovalRule(int selectedApprovalRule) {
        this.selectedApprovalRule = selectedApprovalRule;
    }

    public int getSelectedApprovalSubRule() {
        return selectedApprovalSubRule;
    }

    public void setSelectedApprovalSubRule(int selectedApprovalSubRule) {
        this.selectedApprovalSubRule = selectedApprovalSubRule;
    }

    public TwoFactorAuthentication getTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(TwoFactorAuthentication twoFactorAuthentication) {
        this.twoFactorAuthentication = twoFactorAuthentication;
    }

    public String getApprovalHtml() {
        return approvalHtml;
    }

    public void setApprovalHtml(String approvalHtml) {
        this.approvalHtml = approvalHtml;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getcRMReferenceNumber() {
        return cRMReferenceNumber;
    }

    public void setcRMReferenceNumber(String cRMReferenceNumber) {
        this.cRMReferenceNumber = cRMReferenceNumber;
    }

    public Amount getFees() {
        return fees;
    }

    public void setFees(Amount fees) {
        this.fees = fees;
    }
}
