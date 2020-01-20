package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CustomerAddressTypeEnum {
    Undefined(0),
    HomeAddress(1),
    WorkAddress(2);

    private int value;
    private static Map map = new HashMap<>();

    private CustomerAddressTypeEnum(int value) {
        this.value = value;
    }

    static {
        for (CustomerAddressTypeEnum myenum : CustomerAddressTypeEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CustomerAddressTypeEnum valueOf(int pageType) {
        return (CustomerAddressTypeEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
