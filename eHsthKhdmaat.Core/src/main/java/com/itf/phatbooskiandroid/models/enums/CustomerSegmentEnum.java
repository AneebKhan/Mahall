package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/4/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CustomerSegmentEnum {
    Default(0),
    Thuraya(1),
    Gold(2);

    private int value;
    private static Map map = new HashMap<>();

    private CustomerSegmentEnum(int value) {
        this.value = value;
    }

    static {
        for (CustomerSegmentEnum myenum : CustomerSegmentEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CustomerSegmentEnum valueOf(int pageType) {
        return (CustomerSegmentEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
