package com.itf.phatbooskiandroid.enums;

import com.google.gson.annotations.SerializedName;

public enum TransactionStates {

    @SerializedName("Added")
    ADDED("Added"),

    @SerializedName("Updated")
    UPDATED("Updated"),

    @SerializedName("Warning")
    WARNING("Warning"),

    @SerializedName("Deleted")
    DELETED("Deleted"),

    @SerializedName("Error")
    ERROR("Error");

    private String string;

    TransactionStates(String string) {
        this.string = string;
    }

    public String getState() {
        return string;
    }

}
