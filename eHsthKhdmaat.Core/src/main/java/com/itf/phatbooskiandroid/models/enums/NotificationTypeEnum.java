package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum NotificationTypeEnum {
    SMS(0),
    Email(1),
    SecureMail(2),
    OTP(3),
    UserName(4),
    Password(5),
    Both(6),
    None(7);

    private int value;
    private static Map map = new HashMap<>();

    private NotificationTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (NotificationTypeEnum myenum : NotificationTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static NotificationTypeEnum valueOf(int pageType) {
        return (NotificationTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
