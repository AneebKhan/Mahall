
package com.itf.phatbooskiandroid.models.menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/2/2018.
 */

public class ResourceItemListTitle {

    @SerializedName("ID")
    @Expose
    private int iD;
    @SerializedName("Value")
    @Expose
    private String value;
    @SerializedName("Culture")
    @Expose
    private String culture;
    @SerializedName("ShowInCurrentCulture")
    @Expose
    private boolean showInCurrentCulture;

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public boolean isShowInCurrentCulture() {
        return showInCurrentCulture;
    }

    public void setShowInCurrentCulture(boolean showInCurrentCulture) {
        this.showInCurrentCulture = showInCurrentCulture;
    }

}
