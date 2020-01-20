
package com.itf.phatbooskiandroid.models.uimodels.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionHeader {

    @SerializedName("SelectedRuleSet")
    @Expose
    private int selectedRuleSet;
    @SerializedName("Customer")
    @Expose
    private Customer customer;
    @SerializedName("TransactionDefinition")
    @Expose
    private TransactionDefinition transactionDefinition;
    @SerializedName("ExecutingTransactionName")
    @Expose
    private String executingTransactionName;
    @SerializedName("Channel")
    @Expose
    private Channel channel;
    @SerializedName("UniqueKey")
    @Expose
    private String uniqueKey;

    public int getSelectedRuleSet() {
        return selectedRuleSet;
    }

    public void setSelectedRuleSet(int selectedRuleSet) {
        this.selectedRuleSet = selectedRuleSet;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TransactionDefinition getTransactionDefinition() {
        return transactionDefinition;
    }

    public void setTransactionDefinition(TransactionDefinition transactionDefinition) {
        this.transactionDefinition = transactionDefinition;
    }

    public String getExecutingTransactionName() {
        return executingTransactionName;
    }

    public void setExecutingTransactionName(String executingTransactionName) {
        this.executingTransactionName = executingTransactionName;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

}
