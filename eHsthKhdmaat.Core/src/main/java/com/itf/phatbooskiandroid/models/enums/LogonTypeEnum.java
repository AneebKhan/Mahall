package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum LogonTypeEnum {
    Undefined(0),
    IdentificationNo(1),
    Username(2);

    private int value;
    private static Map map = new HashMap<>();

    private LogonTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (LogonTypeEnum myenum : LogonTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static LogonTypeEnum valueOf(int pageType) {
        return (LogonTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
