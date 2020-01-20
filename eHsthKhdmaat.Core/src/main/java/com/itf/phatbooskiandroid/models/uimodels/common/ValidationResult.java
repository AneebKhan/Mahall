package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahmed on 3/6/2018.
 */

public class ValidationResult {

    @SerializedName("HasValidation")
    @Expose
    private boolean hasValidation;
    @SerializedName("ValidatorList")
    @Expose
    private List<ValidatorList> validatorList = null;

    public boolean isHasValidation() {
        return hasValidation;
    }

    public void setHasValidation(boolean hasValidation) {
        this.hasValidation = hasValidation;
    }

    public List<ValidatorList> getValidatorList() {
        return validatorList;
    }

    public void setValidatorList(List<ValidatorList> validatorList) {
        this.validatorList = validatorList;
    }

}