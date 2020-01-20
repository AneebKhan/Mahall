package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by zahmed on 3/4/2018.
 */

public class TransactionFee {

    @SerializedName("TransactionFeeEnabled")
    @Expose
    private boolean transactionFeeEnabled;
    @SerializedName("TransactionFee")
    @Expose
    private double transactionFee;

    public boolean isTransactionFeeEnabled() {
        return transactionFeeEnabled;
    }

    public void setTransactionFeeEnabled(boolean transactionFeeEnabled) {
        this.transactionFeeEnabled = transactionFeeEnabled;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

}
