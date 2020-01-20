package com.itf.phatbooskiandroid.models.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zahmed on 4/7/2018.
 * Veripark Bahrain
 * http://www.veripark.com
 */

public enum MigratedSystemEnum {
    Undefined(0),
    IB(1),
    MB(2);

    private int value;
    private static Map map = new HashMap<>();

    private MigratedSystemEnum(int value) {
        this.value = value;
    }

    static {
        for (MigratedSystemEnum myenum : MigratedSystemEnum.values()) {
            map.put(myenum.value, myenum);
        }
    }

    public static MigratedSystemEnum valueOf(int pageType) {
        return (MigratedSystemEnum) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
