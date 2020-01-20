package com.itf.phatbooskiandroid.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public enum BuildType {
    @SerializedName("release")
    RELEASE("release"),

    @SerializedName("debug")
    DEBUG("debug"),

    @SerializedName("offline")
    OFFLINE("offline"),

    @SerializedName("demo")
    DEMO("demo");


    private String string;

    BuildType(String string) {
        this.string = string;
    }

    public static BuildType fromString(String string) {
        if (string != null) {
            for (BuildType buildType : BuildType.values()) {
                if (string.equalsIgnoreCase(buildType.string)) return buildType;
            }
        }
        return null;
    }
}