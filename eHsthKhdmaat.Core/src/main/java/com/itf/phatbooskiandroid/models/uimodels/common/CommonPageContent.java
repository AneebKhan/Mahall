package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zahmed on 3/4/2018.
 */

public class CommonPageContent {

    @SerializedName("InformationMessageText")
    @Expose
    private String informationMessageText;
    @SerializedName("InformationContainers")
    @Expose
    private List<InformationContainer> informationContainers = null;

    public String getInformationMessageText() {
        return informationMessageText;
    }

    public void setInformationMessageText(String informationMessageText) {
        this.informationMessageText = informationMessageText;
    }

    public List<InformationContainer> getInformationContainers() {
        return informationContainers;
    }

    public void setInformationContainers(List<InformationContainer> informationContainers) {
        this.informationContainers = informationContainers;
    }

}