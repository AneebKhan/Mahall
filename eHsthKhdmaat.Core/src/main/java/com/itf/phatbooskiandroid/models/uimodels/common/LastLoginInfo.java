package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahmed on 3/4/2018.
 */

public class LastLoginInfo {

    @SerializedName("Succesful")
    @Expose
    private List<Succesful> succesful = null;
    @SerializedName("Failed")
    @Expose
    private List<Failed> failed = null;

    public List<Succesful> getSuccesful() {
        return succesful;
    }

    public void setSuccesful(List<Succesful> succesful) {
        this.succesful = succesful;
    }

    public List<Failed> getFailed() {
        return failed;
    }

    public void setFailed(List<Failed> failed) {
        this.failed = failed;
    }

}