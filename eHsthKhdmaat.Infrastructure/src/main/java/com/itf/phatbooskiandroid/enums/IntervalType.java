package com.itf.phatbooskiandroid.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 12/11/2018.
 * ITF
 */

public enum IntervalType {
    @SerializedName("today")
    Today("today"),

    @SerializedName("yestarday")
    YesterDay("yesterday"),

    @SerializedName("currentweek")
    CurrentWeek("currentweek"),

    @SerializedName("lastweek")
    LastWeek("lastweek"),

    @SerializedName("currentmonth")
    CurrentMonth("currentmonth"),

    @SerializedName("lastmonth")
    LastMonth("lastmonth"),

    @SerializedName("last3month")
    Last3Month("last3month"),

    @SerializedName("last6month")
    Last6Month("last6month"),

    @SerializedName("currentyear")
    CurrentYear("currentyear"),

    @SerializedName("lastyear")
    LastYear("lastyear");

    private String string;

    IntervalType(String string) {
        this.string = string;
    }

    public static IntervalType fromString(String string) {
        if (string != null) {
            for (IntervalType intervalType : IntervalType.values()) {
                if (string.equalsIgnoreCase(intervalType.string)) return intervalType;
            }
        }
        return null;
    }
}
