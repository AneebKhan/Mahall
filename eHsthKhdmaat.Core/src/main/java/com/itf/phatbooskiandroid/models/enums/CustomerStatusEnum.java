package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CustomerStatusEnum {
    Undefined(0),
    Standard(1),
    Private(2),
    Personnel(3),
    GroupPersonnel(4),
    Salary(5);

    private int value;
    private static Map map = new HashMap<>();

    private CustomerStatusEnum(int value) {
        this.value = value;
    }

    static {
        for (CustomerStatusEnum myenum : CustomerStatusEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CustomerStatusEnum valueOf(int pageType) {
        return (CustomerStatusEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
