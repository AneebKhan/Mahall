
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidationConfig {

    @SerializedName("ControlList")
    @Expose
    private List<ControlList> controlList = null;

    public List<ControlList> getControlList() {
        return controlList;
    }

    public void setControlList(List<ControlList> controlList) {
        this.controlList = controlList;
    }

}
