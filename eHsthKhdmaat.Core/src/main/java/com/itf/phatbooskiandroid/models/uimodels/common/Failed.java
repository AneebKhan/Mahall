package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/4/2018.
 */

public class Failed {

    @SerializedName("LastLoginIP")
    @Expose
    private String lastLoginIP;
    @SerializedName("LastLoginTime")
    @Expose
    private String lastLoginTime;
    @SerializedName("LastLoginResult")
    @Expose
    private String lastLoginResult;

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginResult() {
        return lastLoginResult;
    }

    public void setLastLoginResult(String lastLoginResult) {
        this.lastLoginResult = lastLoginResult;
    }

}