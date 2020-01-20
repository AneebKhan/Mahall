package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/4/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum RetailRoleEnum {
    FullAuthorized(0);

    private int value;
    private static Map map = new HashMap<>();

    private RetailRoleEnum(int value) {
        this.value = value;
    }

    static {
        for (RetailRoleEnum myenum : RetailRoleEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static RetailRoleEnum valueOf(int pageType) {
        return (RetailRoleEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
