package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class Error {

    @SerializedName("DisplayMessage")
    @Expose
    private String displayMessage;
    @SerializedName("ExceptionMessage")
    @Expose
    private String exceptionMessage;

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}