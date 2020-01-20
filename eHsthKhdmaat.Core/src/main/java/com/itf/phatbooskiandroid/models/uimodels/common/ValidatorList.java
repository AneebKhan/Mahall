
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidatorList {

    @SerializedName("Parameters")
    @Expose
    private List<Parameter> parameters = null;
    @SerializedName("DisableDynamicValidation")
    @Expose
    private boolean disableDynamicValidation;
    @SerializedName("PropertyName")
    @Expose
    private String propertyName;
    @SerializedName("EnabledExpression")
    @Expose
    private String enabledExpression;
    @SerializedName("ControlID")
    @Expose
    private String controlID;
    @SerializedName("StepName")
    @Expose
    private String stepName;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("TypeSpecified")
    @Expose
    private boolean typeSpecified;
    @SerializedName("ResourceKey")
    @Expose
    private String resourceKey;
    @SerializedName("ResourceType")
    @Expose
    private String resourceType;
    @SerializedName("ResourceTypeSpecified")
    @Expose
    private boolean resourceTypeSpecified;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("MaxLengthSpecified")
    @Expose
    private boolean maxLengthSpecified;
    @SerializedName("MinLengthSpecified")
    @Expose
    private boolean minLengthSpecified;
    @SerializedName("ValidateEmptyString")
    @Expose
    private boolean validateEmptyString;
    @SerializedName("ValidateEmptyStringSpecified")
    @Expose
    private boolean validateEmptyStringSpecified;
    @SerializedName("ValidationExpression")
    @Expose
    private String validationExpression;
    @SerializedName("ClientSideMethodName")
    @Expose
    private String clientSideMethodName;
    @SerializedName("ServerSideMethodName")
    @Expose
    private String serverSideMethodName;
    @SerializedName("SetFocusOnError")
    @Expose
    private boolean setFocusOnError;
    @SerializedName("SetFocusOnErrorSpecified")
    @Expose
    private boolean setFocusOnErrorSpecified;
    @SerializedName("ConstErrorMessage")
    @Expose
    private String constErrorMessage;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public boolean isDisableDynamicValidation() {
        return disableDynamicValidation;
    }

    public void setDisableDynamicValidation(boolean disableDynamicValidation) {
        this.disableDynamicValidation = disableDynamicValidation;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getEnabledExpression() {
        return enabledExpression;
    }

    public void setEnabledExpression(String enabledExpression) {
        this.enabledExpression = enabledExpression;
    }

    public String getControlID() {
        return controlID;
    }

    public void setControlID(String controlID) {
        this.controlID = controlID;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTypeSpecified() {
        return typeSpecified;
    }

    public void setTypeSpecified(boolean typeSpecified) {
        this.typeSpecified = typeSpecified;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public boolean isResourceTypeSpecified() {
        return resourceTypeSpecified;
    }

    public void setResourceTypeSpecified(boolean resourceTypeSpecified) {
        this.resourceTypeSpecified = resourceTypeSpecified;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isMaxLengthSpecified() {
        return maxLengthSpecified;
    }

    public void setMaxLengthSpecified(boolean maxLengthSpecified) {
        this.maxLengthSpecified = maxLengthSpecified;
    }

    public boolean isMinLengthSpecified() {
        return minLengthSpecified;
    }

    public void setMinLengthSpecified(boolean minLengthSpecified) {
        this.minLengthSpecified = minLengthSpecified;
    }

    public boolean isValidateEmptyString() {
        return validateEmptyString;
    }

    public void setValidateEmptyString(boolean validateEmptyString) {
        this.validateEmptyString = validateEmptyString;
    }

    public boolean isValidateEmptyStringSpecified() {
        return validateEmptyStringSpecified;
    }

    public void setValidateEmptyStringSpecified(boolean validateEmptyStringSpecified) {
        this.validateEmptyStringSpecified = validateEmptyStringSpecified;
    }

    public String getValidationExpression() {
        return validationExpression;
    }

    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }

    public String getClientSideMethodName() {
        return clientSideMethodName;
    }

    public void setClientSideMethodName(String clientSideMethodName) {
        this.clientSideMethodName = clientSideMethodName;
    }

    public String getServerSideMethodName() {
        return serverSideMethodName;
    }

    public void setServerSideMethodName(String serverSideMethodName) {
        this.serverSideMethodName = serverSideMethodName;
    }

    public boolean isSetFocusOnError() {
        return setFocusOnError;
    }

    public void setSetFocusOnError(boolean setFocusOnError) {
        this.setFocusOnError = setFocusOnError;
    }

    public boolean isSetFocusOnErrorSpecified() {
        return setFocusOnErrorSpecified;
    }

    public void setSetFocusOnErrorSpecified(boolean setFocusOnErrorSpecified) {
        this.setFocusOnErrorSpecified = setFocusOnErrorSpecified;
    }

    public String getConstErrorMessage() {
        return constErrorMessage;
    }

    public void setConstErrorMessage(String constErrorMessage) {
        this.constErrorMessage = constErrorMessage;
    }

}
