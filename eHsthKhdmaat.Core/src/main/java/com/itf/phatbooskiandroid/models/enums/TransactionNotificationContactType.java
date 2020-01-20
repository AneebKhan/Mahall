package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum TransactionNotificationContactType {
    All(0),
    SMSNotification(1),
    EmailNotification(2),
    Undefined(3);

    private int value;
    private static Map map = new HashMap<>();

    private TransactionNotificationContactType(int value) {
        this.value = value;
    }

    static {
        for (TransactionNotificationContactType myenum : TransactionNotificationContactType.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static TransactionNotificationContactType valueOf(int pageType) {
        return (TransactionNotificationContactType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
