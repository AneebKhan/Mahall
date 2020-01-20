package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zahmed on 3/22/2018.
 */

public class Currency implements Serializable {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ID")
    @Expose
    private int iD;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("DecimalDigits")
    @Expose
    private int decimalDigits;
    @SerializedName("DecimalDigitsSpecified")
    @Expose
    private boolean decimalDigitsSpecified;
    @SerializedName("IsDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("CategoryCode")
    @Expose
    private String categoryCode;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("ModifiedOn")
    @Expose
    private String modifiedOn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public boolean isDecimalDigitsSpecified() {
        return decimalDigitsSpecified;
    }

    public void setDecimalDigitsSpecified(boolean decimalDigitsSpecified) {
        this.decimalDigitsSpecified = decimalDigitsSpecified;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
