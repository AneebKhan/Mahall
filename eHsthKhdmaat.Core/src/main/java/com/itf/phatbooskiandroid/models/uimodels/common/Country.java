package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class Country {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ID")
    @Expose
    private int iD;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("PhoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("CountryWithPhoneCode")
    @Expose
    private String countryWithPhoneCode;
    @SerializedName("IsGccMember")
    @Expose
    private boolean isGccMember;
    @SerializedName("IsIBANMandatory")
    @Expose
    private boolean isIBANMandatory;
    @SerializedName("ISOCode")
    @Expose
    private String iSOCode;
    @SerializedName("IsDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;
    @SerializedName("ModifyDate")
    @Expose
    private String modifyDate;

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

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getCountryWithPhoneCode() {
        return countryWithPhoneCode;
    }

    public void setCountryWithPhoneCode(String countryWithPhoneCode) {
        this.countryWithPhoneCode = countryWithPhoneCode;
    }

    public boolean isIsGccMember() {
        return isGccMember;
    }

    public void setIsGccMember(boolean isGccMember) {
        this.isGccMember = isGccMember;
    }

    public boolean isIsIBANMandatory() {
        return isIBANMandatory;
    }

    public void setIsIBANMandatory(boolean isIBANMandatory) {
        this.isIBANMandatory = isIBANMandatory;
    }

    public String getISOCode() {
        return iSOCode;
    }

    public void setISOCode(String iSOCode) {
        this.iSOCode = iSOCode;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

}