package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum LandingPageEnum {
    Dashboard(0),
    AccountList(1),
    CardList(2);

    private int value;
    private static Map map = new HashMap<>();

    private LandingPageEnum(int value) {
        this.value = value;
    }

    static {
        for (LandingPageEnum myenum : LandingPageEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static LandingPageEnum valueOf(int pageType) {
        return (LandingPageEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
