package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CustomerActivationTypeEnum {
    Auto(0),
    Manual(1);

    private int value;
    private static Map map = new HashMap<>();

    private CustomerActivationTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (CustomerActivationTypeEnum myenum : CustomerActivationTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CustomerActivationTypeEnum valueOf(int pageType) {
        return (CustomerActivationTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
