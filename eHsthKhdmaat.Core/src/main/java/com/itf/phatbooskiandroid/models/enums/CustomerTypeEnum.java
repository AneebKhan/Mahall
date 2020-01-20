package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CustomerTypeEnum {
    Undefined(0),
    Retail(1),
    Corporate(2),
    FIB(3),
    MicroEnterprice(4),
    WithCreditCard(5),
    CustomerList(6),
    Individual(7);

    private int value;
    private static Map map = new HashMap<>();

    private CustomerTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (CustomerTypeEnum myenum : CustomerTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CustomerTypeEnum valueOf(int pageType) {
        return (CustomerTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
