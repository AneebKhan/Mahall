
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("IsSuccess")
    @Expose
    private boolean isSuccess;
    @SerializedName("HasWarning")
    @Expose
    private boolean hasWarning;
    @SerializedName("HasSuccess")
    @Expose
    private boolean hasSuccess;
    @SerializedName("HasInformation")
    @Expose
    private boolean hasInformation;
    @SerializedName("HasAnyResultElement")
    @Expose
    private boolean hasAnyResultElement;
    @SerializedName("IsArrangedBefore")
    @Expose
    private boolean isArrangedBefore;
    @SerializedName("ValidationResult")
    @Expose
    private ValidationResult validationResult;
    @SerializedName("Error")
    @Expose
    private Error error;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("PageDirectionInfo")
    @Expose
    private PageDirectionInfo pageDirectionInfo;
    @SerializedName("TwoFactorAuthentication")
    @Expose
    private TwoFactorAuthentication twoFactorAuthentication;
    @SerializedName("TransactionFee")
    @Expose
    private TransactionFee transactionFee;

    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isHasWarning() {
        return hasWarning;
    }

    public void setHasWarning(boolean hasWarning) {
        this.hasWarning = hasWarning;
    }

    public boolean isHasSuccess() {
        return hasSuccess;
    }

    public void setHasSuccess(boolean hasSuccess) {
        this.hasSuccess = hasSuccess;
    }

    public boolean isHasInformation() {
        return hasInformation;
    }

    public void setHasInformation(boolean hasInformation) {
        this.hasInformation = hasInformation;
    }

    public boolean isHasAnyResultElement() {
        return hasAnyResultElement;
    }

    public void setHasAnyResultElement(boolean hasAnyResultElement) {
        this.hasAnyResultElement = hasAnyResultElement;
    }

    public boolean isIsArrangedBefore() {
        return isArrangedBefore;
    }

    public void setIsArrangedBefore(boolean isArrangedBefore) {
        this.isArrangedBefore = isArrangedBefore;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PageDirectionInfo getPageDirectionInfo() {
        return pageDirectionInfo;
    }

    public void setPageDirectionInfo(PageDirectionInfo pageDirectionInfo) {
        this.pageDirectionInfo = pageDirectionInfo;
    }

    public TwoFactorAuthentication getTwoFactorAuthentication() {
        return twoFactorAuthentication;
    }

    public void setTwoFactorAuthentication(TwoFactorAuthentication twoFactorAuthentication) {
        this.twoFactorAuthentication = twoFactorAuthentication;
    }

    public TransactionFee getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(TransactionFee transactionFee) {
        this.transactionFee = transactionFee;
    }

}
