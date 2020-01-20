package models.Bundles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.itf.phatbooskiandroid.enums.TransactionStates;

import java.io.Serializable;

public class SnackBarBundle implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("isSuccess")
    @Expose
    private boolean isSuccess;

    @SerializedName("action")
    @Expose
    private TransactionStates action;

    @SerializedName("navigateTo")
    @Expose
    private String navigateTo;

    @SerializedName("navigationText")
    @Expose
    private String navigationText;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public TransactionStates getAction() {
        return action;
    }

    public void setAction(TransactionStates action) {
        this.action = action;
    }

    public String getNavigateTo() {
        return navigateTo;
    }

    public void setNavigateTo(String navigateTo) {
        this.navigateTo = navigateTo;
    }

    public String getNavigationText() {
        return navigationText;
    }

    public void setNavigationText(String navigationText) {
        this.navigationText = navigationText;
    }
}


