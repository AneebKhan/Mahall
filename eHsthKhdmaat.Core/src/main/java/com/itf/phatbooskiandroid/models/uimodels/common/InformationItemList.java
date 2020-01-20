package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class InformationItemList {

    @SerializedName("HasTitle")
    @Expose
    private boolean hasTitle;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("HasIndent")
    @Expose
    private boolean hasIndent;
    @SerializedName("InformationItemType")
    @Expose
    private String informationItemType;
    @SerializedName("InformationType")
    @Expose
    private String informationType;

    public boolean isHasTitle() {
        return hasTitle;
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isHasIndent() {
        return hasIndent;
    }

    public void setHasIndent(boolean hasIndent) {
        this.hasIndent = hasIndent;
    }

    public String getInformationItemType() {
        return informationItemType;
    }

    public void setInformationItemType(String informationItemType) {
        this.informationItemType = informationItemType;
    }

    public String getInformationType() {
        return informationType;
    }

    public void setInformationType(String informationType) {
        this.informationType = informationType;
    }

}
