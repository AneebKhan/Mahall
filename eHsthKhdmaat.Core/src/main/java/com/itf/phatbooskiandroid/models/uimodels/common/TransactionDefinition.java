
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionDefinition {

    @SerializedName("TransactionName")
    @Expose
    private String transactionName;
    @SerializedName("TransactionType")
    @Expose
    private String transactionType;
    @SerializedName("GroupTypeName")
    @Expose
    private String groupTypeName;
    @SerializedName("KeepChangeSet")
    @Expose
    private boolean keepChangeSet;

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getGroupTypeName() {
        return groupTypeName;
    }

    public void setGroupTypeName(String groupTypeName) {
        this.groupTypeName = groupTypeName;
    }

    public boolean isKeepChangeSet() {
        return keepChangeSet;
    }

    public void setKeepChangeSet(boolean keepChangeSet) {
        this.keepChangeSet = keepChangeSet;
    }

}
