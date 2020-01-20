
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ControlList {

    @SerializedName("ControlID")
    @Expose
    private String controlID;
    @SerializedName("ValidatorList")
    @Expose
    private List<ValidatorList> validatorList = null;

    public String getControlID() {
        return controlID;
    }

    public void setControlID(String controlID) {
        this.controlID = controlID;
    }

    public List<ValidatorList> getValidatorList() {
        return validatorList;
    }

    public void setValidatorList(List<ValidatorList> validatorList) {
        this.validatorList = validatorList;
    }

}
