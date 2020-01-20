package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum CurrencyCategoryEnum {
    Other(0),
    GCC(1);

    private int value;
    private static Map map = new HashMap<>();

    private CurrencyCategoryEnum(int value) {
        this.value = value;
    }

    static {
        for (CurrencyCategoryEnum myenum : CurrencyCategoryEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static CurrencyCategoryEnum valueOf(int pageType) {
        return (CurrencyCategoryEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
