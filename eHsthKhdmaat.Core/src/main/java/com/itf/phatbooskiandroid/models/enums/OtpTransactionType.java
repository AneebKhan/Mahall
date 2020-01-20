package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 3/6/2018.
 */

public enum OtpTransactionType {
    TransactionOtp(1),
    LoginOtp(2),
    CallCenterQuestionOtp(3);

    private int value;
    private static Map map = new HashMap<>();

    private OtpTransactionType(int value) {
        this.value = value;
    }

    static {
        for (OtpTransactionType pageType : OtpTransactionType.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static OtpTransactionType valueOf(int pageType) {
        return (OtpTransactionType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }

}

    /*
    Using Enum Sample
    --Getting Enum from Integer--
    To get the enum for a given integer, we simply have to call the valueOf method, like below.

    OtpTransactionType otpTransactionType = OtpTransactionType.valueOf(2); // otpTransactionType = OtpTransactionType.LoginOtp


    --Getting Integer from Enum--
    On the other hand, to get the integer value from an enum, one can do as follows, by using the getValue method.

    OtpTransactionType otpTransactionType = OtpTransactionType.LoginOtp;
    int otpTransactionTypeId = otpTransactionType.getValue(); // otpTransactionType = 2
    */