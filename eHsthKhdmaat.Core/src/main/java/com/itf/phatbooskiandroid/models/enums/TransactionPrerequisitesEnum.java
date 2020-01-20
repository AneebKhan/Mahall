package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum TransactionPrerequisitesEnum {
        Undefined(0),
    RequireCurrentAccount(1),
    RequireSavingsAccount(2),
    RequireLoanAccount(3),
    RequireFDAccount(4),
    RequireCreditCard(5),
    RequireDebitCard(6),
    RequireTejoori(7),
    RequireIQRA(8),
    RequirePaypal(9),
    RequireCASA(10),
    RequireCreditCardForPayment(11);

    private int value;
    private static Map map = new HashMap<>();

    private TransactionPrerequisitesEnum(int value) {
        this.value = value;
    }

    static {
        for (TransactionPrerequisitesEnum myenum : TransactionPrerequisitesEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static TransactionPrerequisitesEnum valueOf(int pageType) {
        return (TransactionPrerequisitesEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
