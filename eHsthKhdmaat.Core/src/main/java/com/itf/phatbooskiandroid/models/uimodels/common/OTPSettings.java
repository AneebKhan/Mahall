package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class OTPSettings {

    @SerializedName("OTPType")
    @Expose
    private String oTPType;

    public String getOTPType() {
        return oTPType;
    }

    public void setOTPType(String oTPType) {
        this.oTPType = oTPType;
    }

}
