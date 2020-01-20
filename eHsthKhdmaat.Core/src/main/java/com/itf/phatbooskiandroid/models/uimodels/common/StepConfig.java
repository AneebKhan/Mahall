
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StepConfig {

    @SerializedName("FlowName")
    @Expose
    private String flowName;
    @SerializedName("ModuleName")
    @Expose
    private String moduleName;
    @SerializedName("ControllerName")
    @Expose
    private String controllerName;
    @SerializedName("StepName")
    @Expose
    private String stepName;
    @SerializedName("EnableNext")
    @Expose
    private boolean enableNext;
    @SerializedName("EnablePrevious")
    @Expose
    private boolean enablePrevious;
    @SerializedName("HeaderText")
    @Expose
    private String headerText;
    @SerializedName("StepText")
    @Expose
    private String stepText;
    @SerializedName("HeaderMessage")
    @Expose
    private String headerMessage;
    @SerializedName("ValidationConfig")
    @Expose
    private ValidationConfig validationConfig;
    @SerializedName("ShowReceiptButton")
    @Expose
    private boolean showReceiptButton;
    @SerializedName("ShowRepeatTransactionButton")
    @Expose
    private boolean showRepeatTransactionButton;
    @SerializedName("MasterUIView")
    @Expose
    private MasterUIView masterUIView;
    @SerializedName("ChildUIViews")
    @Expose
    private List<ChildUIView> childUIViews = null;
    @SerializedName("DisableDynamicValidation")
    @Expose
    private boolean disableDynamicValidation;
    @SerializedName("Parkable")
    @Expose
    private boolean parkable;

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public boolean isEnableNext() {
        return enableNext;
    }

    public void setEnableNext(boolean enableNext) {
        this.enableNext = enableNext;
    }

    public boolean isEnablePrevious() {
        return enablePrevious;
    }

    public void setEnablePrevious(boolean enablePrevious) {
        this.enablePrevious = enablePrevious;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getStepText() {
        return stepText;
    }

    public void setStepText(String stepText) {
        this.stepText = stepText;
    }

    public String getHeaderMessage() {
        return headerMessage;
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    public ValidationConfig getValidationConfig() {
        return validationConfig;
    }

    public void setValidationConfig(ValidationConfig validationConfig) {
        this.validationConfig = validationConfig;
    }

    public boolean isShowReceiptButton() {
        return showReceiptButton;
    }

    public void setShowReceiptButton(boolean showReceiptButton) {
        this.showReceiptButton = showReceiptButton;
    }

    public boolean isShowRepeatTransactionButton() {
        return showRepeatTransactionButton;
    }

    public void setShowRepeatTransactionButton(boolean showRepeatTransactionButton) {
        this.showRepeatTransactionButton = showRepeatTransactionButton;
    }

    public MasterUIView getMasterUIView() {
        return masterUIView;
    }

    public void setMasterUIView(MasterUIView masterUIView) {
        this.masterUIView = masterUIView;
    }

    public List<ChildUIView> getChildUIViews() {
        return childUIViews;
    }

    public void setChildUIViews(List<ChildUIView> childUIViews) {
        this.childUIViews = childUIViews;
    }

    public boolean isDisableDynamicValidation() {
        return disableDynamicValidation;
    }

    public void setDisableDynamicValidation(boolean disableDynamicValidation) {
        this.disableDynamicValidation = disableDynamicValidation;
    }

    public boolean isParkable() {
        return parkable;
    }

    public void setParkable(boolean parkable) {
        this.parkable = parkable;
    }

}
