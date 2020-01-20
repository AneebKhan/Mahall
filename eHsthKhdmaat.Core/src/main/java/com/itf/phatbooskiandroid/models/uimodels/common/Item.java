
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item<REQ,RES> {

    @SerializedName("Request")
    @Expose
    private REQ request;
    @SerializedName("Response")
    @Expose
    private RES response;
    @SerializedName("TransactionHeader")
    @Expose
    private TransactionHeader transactionHeader;
    @SerializedName("CommonPageContent")
    @Expose
    private CommonPageContent commonPageContent;
    @SerializedName("StepConfig")
    @Expose
    private StepConfig stepConfig;
    @SerializedName("StepResources")
    @Expose
    private List<StepResource> stepResources = null;
    @SerializedName("InitialCallResponses")
    @Expose
    private List<Object> initialCallResponses = null;
    @SerializedName("SystemDateTime")
    @Expose
    private String systemDateTime;

    public REQ getRequest() {
        return request;
    }

    public void setRequest(REQ request) {
        this.request = request;
    }

    public RES getResponse() {
        return response;
    }

    public void setResponse(RES response) {
        this.response = response;
    }

    public TransactionHeader getTransactionHeader() {
        return transactionHeader;
    }

    public void setTransactionHeader(TransactionHeader transactionHeader) {
        this.transactionHeader = transactionHeader;
    }

    public CommonPageContent getCommonPageContent() {
        return commonPageContent;
    }

    public void setCommonPageContent(CommonPageContent commonPageContent) {
        this.commonPageContent = commonPageContent;
    }

    public StepConfig getStepConfig() {
        return stepConfig;
    }

    public void setStepConfig(StepConfig stepConfig) {
        this.stepConfig = stepConfig;
    }

    public List<StepResource> getStepResources() {
        return stepResources;
    }

    public void setStepResources(List<StepResource> stepResources) {
        this.stepResources = stepResources;
    }

    public List<Object> getInitialCallResponses() {
        return initialCallResponses;
    }

    public void setInitialCallResponses(List<Object> initialCallResponses) {
        this.initialCallResponses = initialCallResponses;
    }

    public String getSystemDateTime() {
        return systemDateTime;
    }

    public void setSystemDateTime(String systemDateTime) {
        this.systemDateTime = systemDateTime;
    }

}
