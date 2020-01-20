package com.itf.phatbooskiandroid.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public enum AppStatus {

    @SerializedName("undefined")
    UNDEFINED("undefined"),

    @SerializedName("launched")
    LAUNCHED("launched"),

    @SerializedName("prelogin")
    PRELOGIN("prelogin"),

    @SerializedName("postlogin")
    POSTLOGIN("postlogin");

    private String string;

    AppStatus(String string) {
        this.string = string;
    }

    public static AppStatus fromString(String string) {
        if (string != null) {
            for (AppStatus appStatus : AppStatus.values()) {
                if (string.equalsIgnoreCase(appStatus.string)) return appStatus;
            }
        }
        return null;
    }
}
