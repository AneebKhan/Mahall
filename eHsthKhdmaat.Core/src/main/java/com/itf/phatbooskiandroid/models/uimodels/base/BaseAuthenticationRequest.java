package com.itf.phatbooskiandroid.models.uimodels.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zahmed on 3/22/2018.
 */

public class BaseAuthenticationRequest extends RequestTransactionData {
    @SerializedName("IsNotLoginTransaction")
    @Expose
    private boolean isNotLoginTransaction;

    public boolean isIsNotLoginTransaction() {
        return isNotLoginTransaction;
    }

    public void setIsNotLoginTransaction(boolean isNotLoginTransaction) {
        this.isNotLoginTransaction = isNotLoginTransaction;
    }
}
