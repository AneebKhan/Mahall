package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class TwoFactorAuthentication {

    @SerializedName("IsOTPRequired")
    @Expose
    private boolean isOTPRequired;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("AuthenticationType")
    @Expose
    private String authenticationType;
    @SerializedName("IsSoftTokenRequired")
    @Expose
    private boolean isSoftTokenRequired;
    @SerializedName("IsSecretQuestionRequired")
    @Expose
    private boolean isSecretQuestionRequired;

    public boolean isIsOTPRequired() {
        return isOTPRequired;
    }

    public void setIsOTPRequired(boolean isOTPRequired) {
        this.isOTPRequired = isOTPRequired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public boolean isIsSoftTokenRequired() {
        return isSoftTokenRequired;
    }

    public void setIsSoftTokenRequired(boolean isSoftTokenRequired) {
        this.isSoftTokenRequired = isSoftTokenRequired;
    }

    public boolean isIsSecretQuestionRequired() {
        return isSecretQuestionRequired;
    }

    public void setIsSecretQuestionRequired(boolean isSecretQuestionRequired) {
        this.isSecretQuestionRequired = isSecretQuestionRequired;
    }

}