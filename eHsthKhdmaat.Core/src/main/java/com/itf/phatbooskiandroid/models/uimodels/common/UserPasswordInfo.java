package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/5/2018.
 */

public class UserPasswordInfo {

    @SerializedName("LocalDate")
    @Expose
    private String localDate;
    @SerializedName("PasswordExpiryDate")
    @Expose
    private String passwordExpiryDate;
    @SerializedName("PasswordExpiryIn")
    @Expose
    private int passwordExpiryIn;
    @SerializedName("LastPasswordChange")
    @Expose
    private String lastPasswordChange;

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    public void setPasswordExpiryDate(String passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    public int getPasswordExpiryIn() {
        return passwordExpiryIn;
    }

    public void setPasswordExpiryIn(int passwordExpiryIn) {
        this.passwordExpiryIn = passwordExpiryIn;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

}
