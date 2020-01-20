package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class PageDirectionInfo {

    @SerializedName("PageDirection")
    @Expose
    private String pageDirection;
    @SerializedName("RedirectionParameter")
    @Expose
    private String redirectionParameter;
    @SerializedName("RedirectionStep")
    @Expose
    private StepConfig redirectionStep;

    public String getPageDirection() {
        return pageDirection;
    }

    public void setPageDirection(String pageDirection) {
        this.pageDirection = pageDirection;
    }

    public String getRedirectionParameter() {
        return redirectionParameter;
    }

    public void setRedirectionParameter(String redirectionParameter) {
        this.redirectionParameter = redirectionParameter;
    }

    public StepConfig getRedirectionStep() {
        return redirectionStep;
    }

    public void setRedirectionStep(StepConfig redirectionStep) {
        this.redirectionStep = redirectionStep;
    }

}

