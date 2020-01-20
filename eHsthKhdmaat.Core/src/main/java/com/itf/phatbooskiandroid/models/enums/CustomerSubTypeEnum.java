package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CustomerSubTypeEnum {
    Undefined(0),
    Default(1),
    Thuraya(2),
    Gold(3),
    CorporateDefault(4),
    CorporateThuraya(5),
    CorporateGold(6);

    private int value;
    private static Map map = new HashMap<>();

    private CustomerSubTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (CustomerSubTypeEnum myenum : CustomerSubTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CustomerSubTypeEnum valueOf(int pageType) {
        return (CustomerSubTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
