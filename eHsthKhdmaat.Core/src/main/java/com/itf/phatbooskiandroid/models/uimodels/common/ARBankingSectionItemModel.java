package com.itf.phatbooskiandroid.models.uimodels.common;

public class ARBankingSectionItemModel {

    public int iconResId;
    public int titleResId;
    public int descriptionResId;
    public int backgroundResId;

    public ARBankingSectionItemModel(int backgroundResId, int iconResId, int titleResId, int descriptionResId) {
        this.iconResId = iconResId;
        this.titleResId = titleResId;
        this.descriptionResId = descriptionResId;
        this.backgroundResId = backgroundResId;
    }
}
